package co.edu.uptc.ds.repository

import co.edu.uptc.ds.extensions.ignore
import java.sql.Connection
import java.sql.DriverManager

class DatabaseConnection {
  
  var connection: Connection? = null
    private set
  
  fun connectToDB(): Boolean {
    var connected = false
    ignore {
      Class.forName(DRIVER).newInstance()
      connection = DriverManager.getConnection(URL, USER, PASSWORD)
      connected = true
    }
    return connected
  }
  
  fun close() {
    ignore { connection?.close() }
  }
  
  companion object {
    private const val REMOTE_SQL_URL = "0.tcp.ngrok.io" // "localhost"
    private const val REMOTE_SQL_PORT = "12271" // "3306"
    private const val DATABASE = "person_dist" // "distri"
    private const val USER = "Distri" // "root"
    private const val PASSWORD = "Distri-4321" // "secret"
    
    private const val DRIVER = "com.mysql.cj.jdbc.Driver"
    private const val URL =
      "jdbc:mysql://$REMOTE_SQL_URL:$REMOTE_SQL_PORT/$DATABASE?useUnicode=true&useJDBCCompliant" +
        "TimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
  }
}
