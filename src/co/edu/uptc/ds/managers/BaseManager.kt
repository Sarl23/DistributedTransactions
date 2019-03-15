package co.edu.uptc.ds.managers

import co.edu.uptc.ds.repository.BaseDAO
import java.lang.Exception

abstract class BaseManager<DAO : BaseDAO<*>, T> {
  
  private val list: ArrayList<T> = ArrayList()
  internal abstract val dao: DAO
  
  fun addItem(item: T): Boolean {
    if (list.contains(item)) return false
    list.add(item)
    return true
  }
  
  fun removeItem(item: T): Boolean {
    if (list.contains(item)) {
      list.remove(item)
      return true
    }
    return false
  }
  
  fun removeItem(index: Int): Boolean {
    try {
      return removeItem(list[index])
    } catch (e: Exception) {
      return false
    }
  }
  
  fun getList(): ArrayList<T> = ArrayList(list)
  
  fun clearList() = list.clear()
  
  abstract fun findItem(s: String): T?
  
  abstract fun load()
  
}
