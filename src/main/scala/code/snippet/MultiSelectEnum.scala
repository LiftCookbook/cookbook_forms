package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.common.Loggable

class MultiSelectEnum extends Loggable {

  object Item extends Enumeration {
    type Item = Value
    val Coffee, Milk, Sugar = Value
  }

  import Item._

  val options : List[(Item,String)] =
    Item.values.toList.map(i => (i -> i.toString))

  val default = Item.Coffee :: Nil

  def render = {

    def selection(items: List[Item]) : Unit = {
      logger.info("Selected: "+items)
    }

    "#opts *" #>
      SHtml.multiSelectObj(options, default, selection)
  }

}



