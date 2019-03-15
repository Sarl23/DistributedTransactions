package co.edu.uptc.ds.servlets

import co.edu.uptc.ds.extensions.hasContent
import co.edu.uptc.ds.managers.PeopleManager
import java.lang.Exception
import java.text.SimpleDateFormat
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "PeopleServlet", urlPatterns = ["/PeopleServlet"])
class PeopleServlet : BaseServlet() {
  
  private val peopleManager: PeopleManager by lazy { PeopleManager() }
  private val dateFormat: SimpleDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd") }
  
  override fun processRequest(
    request: HttpServletRequest,
    response: HttpServletResponse,
    type: Type
                             ) {
    if (type == Type.GET) {
      val searchDoc = try {
        request.getParameter("doc")
      } catch (e: Exception) {
        ""
      }
      
      var responseString = "{\"people\": ["
      peopleManager.load()
      
      val peopleList = if (searchDoc.hasContent()) {
        ArrayList(peopleManager.getList().filter { it.doc.contains(searchDoc, true) })
      } else peopleManager.getList()
      
      peopleList.forEachIndexed { ind, it ->
        val dateAsString = dateFormat.format(it.birth)
        responseString += "{\"doc\":\"${it.doc}\", " +
          "\"type\":\"${it.type}\", \"name\":\"${it.name}\", " +
          "\"surname\":\"${it.surname}\", \"birth\": \"$dateAsString\" }"
        if (ind < peopleList.size - 1) responseString += ", "
      }
      
      responseString += "]}"
      response.writer.write(responseString)
    } else {
      response.writer.write("{\"error\": \"Cannot use request of type: $type\"")
    }
  }
}
