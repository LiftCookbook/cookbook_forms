package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.common.Loggable

class MultiSelectTypeSafe extends Loggable {

  case class Item(id: String, name: String)

  val inventory =
    Item("a", "Coffee") ::
    Item("b", "Milk") ::
    Item("c", "Sugar") :: Nil

  val options : List[(Item,String)] =
    inventory.map(i => (i -> i.name))

  val default = inventory.head :: Nil

  def render = {

    def selection(items: List[Item]) : Unit = {
      logger.info("Selected: "+items)
    }

    "#opts *" #>
      SHtml.multiSelectObj(options, default, selection)
  }

}



