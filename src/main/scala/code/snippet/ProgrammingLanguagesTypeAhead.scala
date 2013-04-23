package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.common.Loggable

import net.liftweb.http.{S, SHtml}
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JE._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JsCmds.Run
import net.liftweb.http.js.JE.JsVar
import net.liftweb.http.js.JE.Str


class ProgrammingLanguagesTypeAhead extends Loggable {

  val languages = List(
    "C", "C++", "Clojure", "CoffeeScript",
    "Java", "JavaScript",
    "POP-11", "Prolog", "Python", "Processing",
    "Scala", "Scheme", "Smalltalk", "SuperCollider"
  )

  def render = {

    def suggest(value: String) : JsCmd = {
      logger.info("Making suggestion for: "+value)
      val matches = languages.filter(_.toLowerCase.startsWith(value.toLowerCase)).map(Str)
      val js = JsArray(matches)
      Run("if (updateUiFunction) updateUiFunction("+js.toJsCmd+");")
    }

    // The Ajax command to work out suggestions for a given query:
    val runSuggestion = SHtml.ajaxCall(JsVar("query"), suggest)

    // TypeAhead arranges for the askServer function is called to get autocomplete suggestions:
    S.appendJs(Run(
      """
        |$('#autocomplete').typeahead({
        |  source: askServer
        |});
      """.stripMargin))

    // The askServer function is defined to stash the callback function as a known value (updateUiFunction) and then
    // call our server-side function to compute suggestions:
    "#js *" #> Function("askServer", "query" :: "callback" :: Nil, Run("updateUiFunction=callback;") & Run(runSuggestion.toJsCmd)) &
    "#autocomplete" #> SHtml.text("", s => logger.info("Submitted: "+s))


  }

}

