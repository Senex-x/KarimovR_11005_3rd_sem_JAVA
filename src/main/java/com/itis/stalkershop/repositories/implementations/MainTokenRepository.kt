package com.itis.stalkershop.repositories.implementations

import com.itis.stalkershop.models.Token
import com.itis.stalkershop.repositories.interfaces.TokenRepository
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import javax.sql.DataSource

private const val SQL_ADD =
    "insert into user_tokens(user_email, token) values (?, ?)"
private const val SQL_GET =
    "select * from user_tokens where user_email = ?"
private const val SQL_GET_BY_TOKEN =
    "select * from user_tokens where token = ?"
private const val SQL_UPDATE =
    "update user_tokens set token = ? where user_email = ?"
private const val SQL_DELETE =
    "delete from user_tokens where user_email = ?"
private const val SQL_DELETE_BY_TOKEN =
    "delete from user_tokens where token = ?"

private val tokenRowMapper = RowMapper { resultSet: ResultSet, _ ->
    Token(
        resultSet.getString("user_email"),
        resultSet.getString("token")
    )
}

class MainTokenRepository(
    dataSource: DataSource
) : TokenRepository {
    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun add(item: Token) {
        jdbcTemplate.update {
            it.prepareStatement(SQL_ADD).apply {
                setString(1, item.userEmail)
                setString(2, item.token)
            }
        }
    }

    override fun get(primaryKey: String): Token? =
        try {
            jdbcTemplate.queryForObject(
                SQL_GET,
                tokenRowMapper,
                primaryKey
            )
        } catch (exception: DataAccessException) {
            null
        }

    override fun getByToken(token: String): Token? =
        try {
            jdbcTemplate.queryForObject(
                SQL_GET_BY_TOKEN,
                tokenRowMapper,
                token
            )
        } catch (exception: DataAccessException) {
            null
        }



    override fun update(item: Token) {
        jdbcTemplate.update {
            it.prepareStatement(SQL_UPDATE).apply {
                setString(1, item.userEmail)
                setString(2, item.token)
            }
        }
    }

    override fun delete(primaryKey: String) {
        jdbcTemplate.update(
            SQL_DELETE,
            primaryKey
        )
    }


    override fun deleteByToken(token: String) {
        jdbcTemplate.update(
            SQL_DELETE_BY_TOKEN,
            token
        )
    }

    override fun getAll(): List<Token> {
        TODO("Not yet implemented")
    }
}
