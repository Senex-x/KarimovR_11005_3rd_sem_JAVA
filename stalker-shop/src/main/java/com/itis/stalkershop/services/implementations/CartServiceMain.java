package com.itis.stalkershop.services.implementations;

import com.itis.stalkershop.models.Cart;
import com.itis.stalkershop.models.CartDto;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.CartRepository;
import com.itis.stalkershop.services.interfaces.CartService;
import org.jetbrains.annotations.NotNull;

public class CartServiceMain implements CartService {
    private final CartRepository cartRepository;

    public CartServiceMain(
            CartRepository cartRepository
    ) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto get(
            @NotNull UserDto user
    ) {
        return null;
    }

    @Override
    public CartDto get(
            @NotNull String userName
    ) {
        return null;
    }

    @Override
    public void add(
            @NotNull UserDto user,
            @NotNull Cart cart
    ) {

    }

    @Override
    public void add(
            @NotNull String userName,
            @NotNull Cart cart
    ) {

    }
}
