package com.gu.servlet

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URL
import java.io.{PrintWriter, ByteArrayInputStream}
import java.util.Date
import net.liftweb.json._
import scala.text.Document

class Proxy extends HttpServlet {

  override def doGet(req: HttpServletRequest, res: HttpServletResponse) {
    res.setContentType("text/html")
    res.setHeader("Content-Type", "application/json; charset=utf-8")
    val out = res.getWriter()

    fetchGoogleDoc(req.getParameter("url"), req.getParameter("callback"), out)

    out.flush()
    out.close()

  }

  def fetchGoogleDoc(url: String, callback: String, out: PrintWriter) {
    try {
      val url = new URL("https://docs.google.com/spreadsheet/pub?key=0AhDtwXOtiI5edGZwaGFDTm5qYkUzTXBWaHlFS1hybEE&output=csv")
//      val url = new URL(url)

      val connection = url.openConnection()
      val stream = connection.getContent.asInstanceOf[ByteArrayInputStream]
      val doc = scala.io.Source.fromInputStream(stream).getLines().mkString("\n")

      import net.liftweb.json.JsonDSL._
      val json: JsonAST.JObject = ("data" -> doc) ~ ("date" -> new Date().getTime.toString)
      val rendered: Document = render(json)
      val compacted: String = compact(rendered)

      out.println(callback + "(" + compacted + ")")

    } catch {
      case e: Exception => System.out.print(e.getMessage)
    }
  }

}
