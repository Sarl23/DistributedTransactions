package co.edu.uptc.ds.repository

import co.edu.uptc.ds.extensions.hasContent
import java.sql.ResultSet

// TODO: Remove O
abstract class BaseDAO<T : BaseSQL> {
  internal val connection: DatabaseConnection = DatabaseConnection()
  internal abstract val sql: T
  
  fun query(id: Int = -1, name: String = ""): ResultSet? =
    queryExecutor(
      when {
        id >= 0 -> sql.queryById(id)
        name.hasContent() -> sql.queryByName(name)
        else -> sql.query()
      })
  
  fun queryExecutor(query: String): ResultSet? {
    return if (connection.connectToDB()) {
      try {
        val statement = connection.connection?.createStatement()
        statement?.executeQuery(query)
      } catch (e: Exception) {
        e.printStackTrace()
        null
      }
    } else null
  }
  
  fun updateExecutor(query: String): Boolean {
    return if (connection.connectToDB()) {
      try {
        val statement = connection.connection?.createStatement()
        (statement?.executeUpdate(query) ?: 0) > 0
      } catch (e: Exception) {
        e.printStackTrace()
        false
      }
    } else false
  }
  
  fun queryBooleanExecutor(query: String): Boolean = queryExecutor(query) != null
  
  fun delete(id: Int): Boolean = deleteWithId(id)
  
  fun delete(name: String): Boolean = deleteWithName(name)
  
  fun insert(query: String): Boolean = updateExecutor(query)
  
  private fun deleteWithId(id: Int): Boolean = updateExecutor(sql.deleteWithId(id))
  
  private fun deleteWithName(name: String): Boolean =
    updateExecutor(sql.deleteWithName(name))
}
