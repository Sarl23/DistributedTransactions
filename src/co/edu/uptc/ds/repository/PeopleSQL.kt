package co.edu.uptc.ds.repository

import java.util.Date
import java.text.SimpleDateFormat

class PeopleSQL : BaseSQL() {
  
  private val dateFormat: SimpleDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd") }
  
  fun queryByDoc(doc: String): String =
    "select * from ${tableName()} where doc = '$doc';"
  
  fun insertUser(doc: String, type: String, name: String, surname: String, birth: Date): String {
    return "insert into ${tableName()} values('$doc', '$type', '$name', '$surname', " +
      "'${dateFormat.format(birth)}');"
  }
  
  fun deleteWithDoc(doc: String): String =
    "delete from ${tableName()} where doc = '$doc'"
  
  override fun tableName(): String = "person"
}
