package com.itis.stalkershop.repositories.implementations

import com.itis.stalkershop.models.Item
import com.itis.stalkershop.repositories.interfaces.ItemsRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

private const val SQL_INSERT =
    "insert into items(name, cost, description, image_name) values (?, ?, ?, ?)"
private const val SQL_UPDATE =
    "update items set cost = ?, description = ?, image_name = ? where name = ?"
private const val SQL_SELECT_BY_EMAIL =
    "select * from items where name = ?"
private const val SQL_SELECT_ALL =
    "select * from items"
private const val SQL_DELETE_ALL_CARTS =
    "delete from carts" // Maybe replace this with "truncate carts"

private val itemRowMapper = RowMapper { resultSet: ResultSet, _ ->
    resultSet.run {
        Item(
            getString("name"),
            getInt("cost"),
            getString("description"),
            getString("image_name")
        )
    }
}

class MainItemsRepository(
    dataSource: DataSource
) : ItemsRepository {
    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun save(item: Item): Item {
        jdbcTemplate.update { connection ->
            connection.prepareStatement(SQL_INSERT).apply {
                setString(1, item.name)
                setInt(2, item.cost)
                setString(3, item.description)
                setString(4, item.imageName)
            }
        }

        return item
    }

    override fun findByPrimaryKey(
        primaryKey: String
    ) = try {
        Optional.ofNullable(
            jdbcTemplate.queryForObject(
                SQL_SELECT_BY_EMAIL,
                itemRowMapper,
                primaryKey
            )
        )
    } catch (e: EmptyResultDataAccessException) {
        Optional.empty()
    }

    override fun findAll(): List<Item> =
        jdbcTemplate.query(
            SQL_SELECT_ALL,
            itemRowMapper
        )

    override fun update(primaryKey: String, item: Item) {
        jdbcTemplate.update {
            it.prepareStatement(SQL_UPDATE).apply {
                setInt(1, item.cost)
                setString(2, item.description)
                setString(3, item.imageName)
                setString(4, item.name)
            }
        }
    }

    override fun delete(primaryKey: String) {
        TODO("Not yet implemented")
    }
}