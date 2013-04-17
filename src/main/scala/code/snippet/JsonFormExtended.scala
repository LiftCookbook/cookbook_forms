package code.snippet

import scala.xml.{Text, NodeSeq}

import net.liftweb.util.Helpers._
import net.liftweb.util.JsonCmd
import net.liftweb.http.SHtml.jsonForm
import net.liftweb.http.JsonHandler
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.{Function, SetHtml, SetValById, Script}
import net.liftweb.http.js.JE.{JsRaw, JsVar}


class JsonFormExtended {

  def render =
    "#jsonForm" #> ((ns:NodeSeq) => jsonForm(MottoServer, ns)) &
    "#jsonScript" #> Script(
      MottoServer.jsCmd &
        Function("changeCase", List("direction"),
          MottoServer.call("processCase", JsVar("direction"), JsRaw("$('#motto').val()"))
        )
    )

  object MottoServer extends JsonHandler {

    def apply(in: Any): JsCmd = in match {

      case JsonCmd("processForm", target, params: Map[String, String], all) =>
        val name = params.getOrElse("name", "No Name")
        val motto = params.getOrElse("motto", "No Motto")
        SetHtml("result",
          Text("The motto of %s is %s".format(name,motto)) )

      case JsonCmd("processCase", direction, motto: String, all) =>
        val update = if (direction == "upper") motto.toUpperCase else motto.toLowerCase
        SetValById("motto", update)

    }

  }
}