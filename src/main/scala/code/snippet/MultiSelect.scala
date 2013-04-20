package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.common.Loggable

class MultiSelect extends Loggable {

  case class Item(id: String, name: String)

  val inventory =
    Item("a", "Coffee") ::
    Item("b", "Milk") ::
    Item("c", "Sugar") :: Nil

  val options : List[(String,String)] =
    inventory.map(i => (i.id -> i.name))

  val default = inventory.head.id :: Nil

  def render = {

    def selection(ids: List[String]) : Unit = {
      logger.info("Selected: "+ids)
    }

    "#opts *" #>
      SHtml.multiSelect(options, default, selection)
  }

}



