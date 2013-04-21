package code.snippet

import java.util.Date
import java.text.SimpleDateFormat

import net.liftweb.util.Helpers._
import net.liftweb.http.{S, SHtml}
import net.liftweb.common.Loggable
import net.liftweb.http.js.JsCmds.Run

class DatePicker extends Loggable {

  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

  val default = (dateFormat format now)

  def logDate(s: String) : Unit = {
    val date : Date = tryo(dateFormat parse s) getOrElse now
    logger.info("Birthday: "+date)
  }

  def render = {
    S.appendJs(enhance)
    "#birthday" #> SHtml.text(default, logDate, ("type"->"date"))
  }

  val enhance = Run(
    """
      |if (!Modernizr.inputtypes.date) {
      | $('input[type=date]').datepicker({dateFormat: 'yy-mm-dd'});
      |}
    """.stripMargin)

}