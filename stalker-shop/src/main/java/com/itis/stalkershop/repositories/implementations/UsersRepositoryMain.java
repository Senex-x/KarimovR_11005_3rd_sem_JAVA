package com.itis.stalkershop.repositories.implementations;


import com.itis.stalkershop.models.User;
import com.itis.stalkershop.models.UserDto;
import com.itis.stalkershop.repositories.interfaces.UsersRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryMain implements UsersRepository {
    private static final String SQL_INSERT =
            "insert into users(email, name, password_hash, avatar_id) values (?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "update users set email = ?, name = ?, avatar_id = ? where email = ?";
    private static final String SQL_SELECT_BY_EMAIL =
            "select * from users where email = ?";
    private static final String SQL_SELECT_ALL =
            "select * from users";

    private final RowMapper<User> rowMapper = (row, rowNumber) ->
            new User(
                    row.getString("email"),
                    row.getString("name"),
                    row.getString("password_hash"),
                    row.getLong("avatar_id")
            );

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryMain(
            DataSource dataSource
    ) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @NotNull
    @Override
    public Optional<User> findByEmail(@NotNull String email) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_SELECT_BY_EMAIL,
                            rowMapper,
                            email
                    ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByPrimaryKey(String id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_SELECT_BY_EMAIL,
                            rowMapper,
                            id
                    ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    // TODO: Handle save or update
    //  according to user's data presence in database
    @Override
    public User save(UserDto item) {
        if (true) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection
                        .prepareStatement(SQL_INSERT, new String[]{"email"});
                statement.setString(1, item.getEmail());
                statement.setString(2, item.getName());
                statement.setString(3, item.getPasswordHash());
                if (item.getAvatarId() != null) {
                    statement.setLong(4, item.getAvatarId());
                } else {
                    statement.setNull(4, Types.NULL);
                }
                return statement;
            }, keyHolder);

            return item.toUser();
        } else {
            // Not safe operation
            jdbcTemplate.update(SQL_UPDATE,
                    item.getEmail(),
                    item.getName(),
                    item.getAvatarId(),
                    item.getEmail()
            );
        }
        return item.toUser();
    }

    // TODO: Implement
    @Override
    public void update(String primaryKey, UserDto item) {
        jdbcTemplate.update(connection -> {
                    PreparedStatement statement = connection
                            .prepareStatement(SQL_UPDATE, new String[]{"email"});
                    statement.setString(1, item.getEmail());
                    statement.setString(2, item.getName());
                    if (item.getAvatarId() != null) {
                        statement.setLong(3, item.getAvatarId());
                    } else {
                        statement.setNull(3, Types.NULL);
                    }
                    statement.setString(4, item.getEmail());
                    return statement;
                },
                new GeneratedKeyHolder()
        );
    }

    // TODO: Implement
    @Override
    public void delete(String primaryKey) {
    }
}
