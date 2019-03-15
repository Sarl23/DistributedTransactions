package co.edu.uptc.ds.managers

import co.edu.uptc.ds.extensions.ignore
import co.edu.uptc.ds.models.Person
import co.edu.uptc.ds.repository.PeopleDAO
import java.text.SimpleDateFormat

class PeopleManager : BaseManager<PeopleDAO, Person>() {
  
  private val dateFormat: SimpleDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd") }
  
  override val dao: PeopleDAO by lazy { PeopleDAO() }
  
  override fun findItem(s: String): Person? =
    getList().firstOrNull { it.doc.equals(s, true) }
  
  override fun load() {
    dao.query()?.let {
      ignore {
        clearList()
        while (it.next()) {
          val doc = it.getString("doc")
          val type = it.getString("type")
          val name = it.getString("name")
          val surname = it.getString("surname")
          val birth = it.getString("birth")
          addItem(Person(doc, type, name, surname, dateFormat.parse(birth)))
        }
      }
    }
  }
  
  public fun deletePerson(person: Person): Boolean {
    if (dao.deletePerson(person)) {
      load()
      return true
    }
    return false
  }
  
  public fun deletePerson(doc: String): Boolean {
    if (dao.deletePerson(doc)) {
      load()
      return true
    }
    return false
  }
}
