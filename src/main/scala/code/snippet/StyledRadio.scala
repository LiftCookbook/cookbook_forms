package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.common.{Empty, Box, Loggable}
import net.liftweb.http.SHtml.ChoiceHolder
import net.liftweb.http.SHtml

object StyledRadio extends Loggable {

  import scala.xml.NodeSeq
  import net.liftweb.http.SHtml.ChoiceItem

  object LabelStyle {
    def htmlize[T](item: ChoiceItem[T]) : NodeSeq =
        <label class="radio">{item.xhtml} {item.key.toString}</label>

    def toForm[T](holder: ChoiceHolder[T]) : NodeSeq =
     holder.items.flatMap(htmlize)
  }


  import BirthGender._

  val options : Seq[BirthGender] = BirthGender.values.toSeq

  val default : Box[BirthGender] = Empty

  val radio : ChoiceHolder[BirthGender] =
    SHtml.radioElem(options, default) { selected =>
      logger.info("Choice: "+selected)
    }

  def render = ".options" #> LabelStyle.toForm(radio)

}
