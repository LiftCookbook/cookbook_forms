package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.common.{Full,Empty, Loggable}

import net.liftweb.http._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JsCmds.Run
import net.liftweb.http.js.JE.JsVar
import net.liftweb.json.JsonAST._
import net.liftweb.json.DefaultFormats

class ProgrammingLanguagesTypeAhead extends Loggable {

  val languages = List(
    "C", "C++", "Clojure", "CoffeeScript",
    "Java", "JavaScript",
    "POP-11", "Prolog", "Python", "Processing",
    "Scala", "Scheme", "Smalltalk", "SuperCollider"
  )

  def render = {

    implicit val formats = DefaultFormats

    def suggest(value: JValue) : JValue = {
      logger.info("Making suggestion for: "+value)

      val matches = for {
        q <- value.extractOpt[String].map(_.toLowerCase).toList
        lang <- languages.filter(_.toLowerCase startsWith q)
      } yield JString(lang)

      JArray(matches)
    }

    val callbackContext = new JsonContext(Full("callback"),Empty)

    val runSuggestion = SHtml.jsonCall(JsVar("query"), callbackContext, suggest _ )

    S.appendJs(Run(
      """
        |$('#autocomplete').typeahead({
        |  source: askServer
        |});
      """.stripMargin))

    "#js *" #> Function("askServer", "query" :: "callback" :: Nil, Run(runSuggestion.toJsCmd)) &
    "#autocomplete" #> SHtml.text("", s => logger.info("Submitted: "+s))

  }

}

