package bootstrap.liftweb

import net.liftweb._

import common._
import http._
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._
import net.liftmodules.widgets.autocomplete.AutoComplete


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    // Build SiteMap
    val entries = List(
      Menu.i("Home") / "index", // the simple way to declare a menu

      Menu.i("Plain Form Processing") / "plain",
      Menu.i("Ajax Form Processing") / "ajaxform",

      Menu.i("JSON Form Processing") / "jsonform",
      Menu.i("JSON Form Extended") / "jsonformextended",

      Menu.i("Jquery Date Picker") / "jqdatepicker",
      Menu.i("HTML5 Date Picker") / "datepicker",

      Menu.i("Autocomplete") / "autocomplete",

      Menu.i("Disable Checkbox") / "disablecheckbox",

      Menu.i("Multiple Selection") / "multiselect",
      Menu.i("Multiple Selection with State") / "multiselectstateful",
      Menu.i("Type Safe Multiple Selection") / "multiselecttypesafe",
      Menu.i("Multiple Selection Enum") / "multiselectenum",

      Menu.i("File Upload") / "fileupload",
      Menu.i("Form Group") / "formgroup",


      // more complex because this menu allows anything in the
      // /static path to be visible
      Menu(Loc("Static", Link(List("static"), true, "/static/index"),
        "Static Content")))

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries:_*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    LiftRules.jsArtifacts = JQueryArtifacts
    JQueryModule.InitParam.JQuery=JQueryModule.JQuery172
    JQueryModule.init()


    AutoComplete.init()


    // File upload settings ------------------------------------------------

    // "InMemory" is the default, but we can change to a "DiskFile" version:
    /*
    LiftRules.handleMimeFile = OnDiskFileParamHolder.apply
    println( "Temporary directory is: "+System.getProperty("java.io.tmpdir") )
    */

    // If you want to see upload progress...
    /*
    def progressPrinter(bytesRead: Long, contentLength: Long, fieldIndex: Int) : Unit = {
      println("Read %d of %d for %d" format (bytesRead, contentLength, fieldIndex))
    }
    LiftRules.progressListener = progressPrinter
    */


    // Settings for file upload size limits:
    LiftRules.maxMimeFileSize = 110 * 1024 * 1024
    LiftRules.maxMimeSize =  LiftRules.maxMimeFileSize

    // To simulate file upload max size failure:
    /*
    LiftRules.maxMimeFileSize = 100

    // For this to work you need servlet API as a dependency in build.sbt (which it is for this project)
    import org.apache.commons.fileupload.FileUploadBase.FileUploadIOException
    LiftRules.exceptionHandler.prepend {
      case (_, _, x : FileUploadIOException) => ResponseWithReason(BadResponse(), "Unable to process file. Too large?")
    }
   */


  }
}
