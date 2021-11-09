package com.itis.stalkershop.web.filters;

import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.services.interfaces.AuthService;
import com.itis.stalkershop.services.interfaces.PasswordService;
import com.itis.stalkershop.utils.LogKt;
import com.itis.stalkershop.utils.exceptions.ValidationException;
import org.jetbrains.annotations.NotNull;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private PasswordService passwordEncoder;
    private AuthService signInService;
    // Global due to modification inside anonymous class
    private boolean isAuthenticated;


    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        this.signInService = (AuthService) context.getAttribute(
                AuthService.class.getSimpleName()
        );
        this.passwordEncoder = (PasswordService) context.getAttribute(
                PasswordService.class.getSimpleName()
        );
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getRequestURI().startsWith("/resources")) {
            filterChain.doFilter(request, response);
            return;
        }

        // берем сессию у запроса
        // берем только существующую, если сессии не было - то вернет null
        HttpSession session = request.getSession(false);
        // флаг, аутентифицирован ли пользователь
        isAuthenticated = false;
        // существует ли сессия вообще?
        boolean sessionExists = session != null;
        // идет ли запрос на страницу входа или регистрации?
        boolean isRequestOnAuthPage =
                request.getRequestURI().equals("/sign-in") ||
                        request.getRequestURI().equals("/sign-up");

        // если сессия есть
        if (sessionExists) {
            // проверим, есть ли атрибует user?
            isAuthenticated = session.getAttribute(UserDto.class.getSimpleName()) != null;
        }

        if (!isAuthenticated) {
            signInService.auth(request, new AuthService.AuthCallback() {
                @Override
                public void onSuccess(@NotNull UserDto userDto) {
                    isAuthenticated = true;
                    LogKt.log(this, "Token authentication succeed");
                }

                @Override
                public void onFail(@NotNull ValidationException exception) {
                    LogKt.log(this, "Token authentication failed");
                }
            });
        }

        // TODO: Inspect conditions
        // если авторизован и запрашивает не открытую страницу или если не авторизован и запрашивает открытую
        if (isAuthenticated && !isRequestOnAuthPage || !isAuthenticated && isRequestOnAuthPage) {
            // отдаем ему то, что он хочет
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isRequestOnAuthPage) {
            // пользователь аутенцифицирован и запрашивает страницу входа
            // - отдаем ему профиль
            response.sendRedirect("/profile");
        } else {
            // если пользователь не аутенцицицирован и запрашивает другие страницы
            response.sendRedirect("/sign-in");
        }
    }

    @Override
    public void destroy() {
    }
}
