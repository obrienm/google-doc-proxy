package com.gu.servlet

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import java.net.URL
import java.io.ByteArrayInputStream
import java.util.Date
import net.liftweb.json._
import scala.text.Document

class Proxy extends HttpServlet {

  override def doGet(req: HttpServletRequest, res: HttpServletResponse) {
    res.setContentType("text/html")
    res.setHeader("Content-Type", "application/json; charset=utf-8")
    val out = res.getWriter()

    val googleDocUrl = "https://docs.google.com/spreadsheet/pub?key=XXXXXXXX&output=csv"

    try {
      val url = new URL(googleDocUrl)

      val connection = url.openConnection()
      val stream = connection.getContent.asInstanceOf[ByteArrayInputStream]
      val doc = scala.io.Source.fromInputStream(stream).getLines().mkString("\n")

      import net.liftweb.json.JsonDSL._
      val json: JsonAST.JObject = ("data" -> doc) ~ ("date" -> new Date().getTime.toString)
      val rendered: Document = render(json)
      val compacted: String = compact(rendered)

      out.println(req.getParameter("callback") + "(" + compacted + ")")

      // cache on google's edge servers
      res.setHeader("Cache-Control", "public, max-age=61")
      res.setStatus(200)
    } catch {
      case e: Exception => {
        System.out.print(e.getMessage)
        res.setStatus(500)
      }
    }

    out.flush()
    out.close()
  }

}
