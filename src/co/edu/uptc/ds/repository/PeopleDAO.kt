package co.edu.uptc.ds.repository

import co.edu.uptc.ds.models.Person

class PeopleDAO : BaseDAO<PeopleSQL>() {
  override val sql: PeopleSQL by lazy { PeopleSQL() }
  
  fun addPerson(person: Person): Boolean =
    insert(sql.insertUser(person.doc, person.type, person.name, person.surname, person.birth))
  
  fun deletePerson(person: Person): Boolean = updateExecutor(sql.deleteWithDoc(person.doc))
  
  fun deletePerson(doc: String): Boolean = updateExecutor(sql.deleteWithDoc(doc))
}
