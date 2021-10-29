package com.itis.stalkershop.repositories.implementations

import com.itis.stalkershop.models.Item
import com.itis.stalkershop.repositories.interfaces.ItemsRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

private val rowMapper = RowMapper { row: ResultSet, _ ->
    row.run {
        Item(
            getString("name"),
            getInt("cost"),
            getString("description"),
            getString("image_name")
        )
    }
}

private const val SQL_INSERT =
    "insert into items(name, cost, description, image_name) values (?, ?, ?, ?)"
private const val SQL_UPDATE =
    "update items set name = ?, cost = ?, description = ?, image_name = ? where name = ?"
private const val SQL_SELECT_BY_EMAIL =
    "select * from items where name = ?"
private const val SQL_SELECT_ALL =
    "select * from items"

class ItemsRepositoryMain(
    dataSource: DataSource
) : ItemsRepository {
    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun findByPrimaryKey(
        primaryKey: String
    ) = try {
        Optional.ofNullable(
            jdbcTemplate.queryForObject(
                SQL_SELECT_BY_EMAIL,
                rowMapper,
                primaryKey
            )
        )
    } catch (e: EmptyResultDataAccessException) {
        Optional.empty()
    }

    override fun findAll(): List<Item> =
        jdbcTemplate.query(
            SQL_SELECT_ALL,
            rowMapper
        )

    override fun save(item: Item): Item {
        TODO("Not yet implemented")
    }

    override fun update(primaryKey: String, item: Item) {
        TODO("Not yet implemented")
    }

    override fun delete(primaryKey: String) {
        TODO("Not yet implemented")
    }
}