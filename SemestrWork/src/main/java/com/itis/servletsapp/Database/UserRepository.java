package com.itis.servletsapp.Database;

import com.itis.servletsapp.dao.base.CrudRepository;
import com.itis.servletsapp.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRepository implements CrudRepository<User, Long> {

    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_SELECT_ALL = "select * from users;";
    private final static String SQL_INSERT = "insert into users (username, password) VALUES (?, ?);";
    private final static String SQL_SELECT_BY_USERNAME = "select * from users where username = ?;";
    private final static String SQL_UPDATE = "update users set username=?, password=?, where id = ?;";
    private final static String SQL_DELETE_BY_ID = "delete from users where id = ?;";

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .username(row.getString("username"))
            .password(row.getString("password"))
            .build();

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<User> findById(String username) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME, userRowMapper, username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public User save(User item) {
        if(item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            System.out.println(keyHolder);
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, item.getUsername());
                statement.setString(2, item.getPassword());
                return statement;
            }, keyHolder);
            item.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    item.getUsername(),
                    item.getPassword(),
                    item.getId()
            );
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.query(SQL_DELETE_BY_ID, userRowMapper, id);
    }
}