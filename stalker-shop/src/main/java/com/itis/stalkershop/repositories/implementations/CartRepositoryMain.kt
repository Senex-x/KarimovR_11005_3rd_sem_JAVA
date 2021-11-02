package com.itis.stalkershop.repositories.implementations

import com.itis.stalkershop.models.Cart
import com.itis.stalkershop.repositories.interfaces.CartRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

private const val SQL_SELECT_BY_ID =
    "select * from carts where user_email = ?"
private const val SQL_INSERT =
    "insert into carts(user_email, item_names_list_json) values (?, ?)"

private val fileRowMapper =
    RowMapper { row: ResultSet, _ ->
        Cart(
            row.getString("user_email"),
            row.getString("item_names_list_json"),
        )
    }

class CartRepositoryMain(dataSource: DataSource) : CartRepository {
    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)

    override fun findByPrimaryKey(primaryKey: String): Optional<Cart> {
        TODO("Not yet implemented")
    }

    override fun save(item: Cart): Cart {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Cart> {
        TODO("Not yet implemented")
    }

    override fun update(primaryKey: String, item: Cart) {
        TODO("Not yet implemented")
    }

    override fun delete(primaryKey: String) {
        TODO("Not yet implemented")
    }
}