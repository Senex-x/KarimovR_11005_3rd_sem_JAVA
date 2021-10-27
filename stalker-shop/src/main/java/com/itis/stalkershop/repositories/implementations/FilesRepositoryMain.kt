package com.itis.stalkershop.repositories.implementations

import com.itis.stalkershop.models.UploadedFile
import com.itis.stalkershop.models.UploadedFileDto
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
    "insert into file_info(storage_file_name, original_file_name, type, size) values (?, ?, ?, ?)"
private const val SQL_UPDATE =
    "update file_info set storage_file_name = ?, original_file_name = ?, type = ?, size = ? where id = ?"
private const val SQL_SELECT_BY_ID =
    "select * from file_info where id = ?"

class FilesRepositoryMain(dataSource: DataSource) : FilesRepository {
    private val jdbcTemplate: JdbcTemplate

    init {
        jdbcTemplate = JdbcTemplate(dataSource)
    }

    private val fileRowMapper =
        RowMapper { row: ResultSet, _ ->
            row.run {
                UploadedFile(
                    getLong("id"),
                    getString("original_file_name"),
                    getString("storage_file_name"),
                    getLong("size"),
                    getString("type")
                )
            }
        }


    // TODO: handle save or update cases
    override fun save(item: UploadedFileDto): UploadedFile {
        return if (true) { // if not present
            val keyHolder = GeneratedKeyHolder()
            jdbcTemplate.update({ connection: Connection ->
                connection.prepareStatement(SQL_INSERT, arrayOf("id")).apply {
                    setString(1, item.storageFileName)
                    setString(2, item.originalFileName)
                    setString(3, item.type)
                    setLong(4, item.size)
                }
            }, keyHolder)

            item.toUploadedFile(keyHolder.key!!.toLong())
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

    override fun update(primaryKey: Long, item: UploadedFileDto) {
        TODO("Not yet implemented")
    }


    override fun findByPrimaryKey(id: Long): Optional<UploadedFile> {
        return try {
            Optional.ofNullable(
                jdbcTemplate.queryForObject(
                    SQL_SELECT_BY_ID,
                    fileRowMapper,
                    id
                )
            )
        } catch (e: EmptyResultDataAccessException) {
            Optional.empty()
        }
    }

    // TODO: Реализовать
    override fun findAll(): List<UploadedFile> {
        return emptyList()
    }

    // TODO: Реализовать
    override fun delete(primaryKey: Long) {}
}