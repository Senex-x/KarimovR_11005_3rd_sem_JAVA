package com.itis.stalkershop.repositories.implementations

import com.itis.stalkershop.models.Cart
import com.itis.stalkershop.repositories.interfaces.CartRepository
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import javax.sql.DataSource

private const val SQL_INSERT =
    "insert into carts(user_email, item_names_list_json) values (?, ?)"
private const val SQL_SELECT_BY_PRIMARY_KEY =
    "select * from carts where user_email = ?"
private const val SQL_DELETE =
    "delete from carts where user_email = ?"
private const val SQL_UPDATE =
    "update carts set item_names_list_json = ? where user_email = ?"

private val cartRowMapper =
    RowMapper { resultSet: ResultSet, _ ->
        Cart(
            resultSet.getString("user_email"),
            resultSet.getString("item_names_list_json"),
        )
    }

// It was a mistake to store JSON lists in the database.
// Now it is unnecessary difficult to modify them.
class MainCartRepository(dataSource: DataSource) : CartRepository {
    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)

    override fun add(item: Cart) {
        jdbcTemplate.update { connection ->
            connection.prepareStatement(SQL_INSERT).apply {
                setString(1, item.userEmail)
                setString(2, item.itemNamesJson)
            }
        }
    }

    override fun get(primaryKey: String) =
        try {
            jdbcTemplate.queryForObject(
                SQL_SELECT_BY_PRIMARY_KEY,
                cartRowMapper,
                primaryKey
            )
        } catch (exception: DataAccessException) {
            null
        }

    override fun update(item: Cart) {
        jdbcTemplate.update {
            it.prepareStatement(SQL_UPDATE).apply {
                setString(1, item.itemNamesJson)
                setString(2, item.userEmail)
            }
        }
    }

    override fun delete(primaryKey: String) {
        jdbcTemplate.update(
            SQL_DELETE,
            primaryKey
        )
    }

    override fun getAll(): List<Cart> {
        TODO("Not yet implemented")
    }
}