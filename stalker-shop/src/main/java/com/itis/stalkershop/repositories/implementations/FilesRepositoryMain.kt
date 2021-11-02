package com.itis.stalkershop.repositories.implementations

import com.itis.stalkershop.models.Image
import com.itis.stalkershop.models.ImageDto
import com.itis.stalkershop.repositories.interfaces.FilesRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import java.sql.Connection
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

private const val SQL_INSERT =
    "insert into files(storage_name, original_name, type, size) values (?, ?, ?, ?)"
private const val SQL_UPDATE =
    "update files set storage_name = ?, original_name = ?, type = ?, size = ? where id = ?"
private const val SQL_SELECT_BY_ID =
    "select * from files where id = ?"

private val imageRowMapper =
    RowMapper { row: ResultSet, _ ->
        row.run {
            Image(
                getLong("id"),
                getString("storage_name"),
                getString("original_name"),
                getString("type"),
                getLong("size")
            )
        }
    }

class FilesRepositoryMain(dataSource: DataSource) : FilesRepository {
    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)

    // TODO: handle save or update cases
    override fun save(item: ImageDto): Image {
        return if (true) { // if not present
            val keyHolder = GeneratedKeyHolder()
            jdbcTemplate.update({ connection: Connection ->
                connection.prepareStatement(SQL_INSERT, arrayOf("id")).apply {
                    setString(1, item.storageName)
                    setString(2, item.originalName)
                    setString(3, item.type)
                    setLong(4, item.size)
                }
            }, keyHolder)

            item.toImage(keyHolder.key!!.toLong())
        } else { // if present (add get id from database logic)
            /*
            item.apply {
                jdbcTemplate.update(
                    SQL_UPDATE,
                    storageFileName,
                    originalFileName,
                    type,
                    size,
                    id
                )
            }.toUploadedFile(id)*/
            TODO("Not yet implemented")
        }
    }

    override fun update(primaryKey: Long, item: ImageDto) {
        TODO("Not yet implemented")
    }


    override fun findByPrimaryKey(id: Long): Optional<Image> {
        return try {
            Optional.ofNullable(
                jdbcTemplate.queryForObject(
                    SQL_SELECT_BY_ID,
                    imageRowMapper,
                    id
                )
            )
        } catch (e: EmptyResultDataAccessException) {
            Optional.empty()
        }
    }

    // TODO: Реализовать
    override fun findAll(): List<Image> {
        return emptyList()
    }

    // TODO: Реализовать
    override fun delete(primaryKey: Long) {}
}