package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.{S, OnDiskFileParamHolder, FileParamHolder}
import net.liftweb.common.{Full, Empty, Box}
import java.io.InputStream


class FileUploadSnippet {

  def render = {

    var upload : Box[FileParamHolder] = Empty

    def processForm() = upload match {

      case Full(content : OnDiskFileParamHolder) =>
        println("File is "+content.localFile.getAbsolutePath)
        val in: InputStream = content.fileStream
        // Work with stream here
        val wasDeleted_? = content.localFile.delete()

      case Full(FileParamHolder(_, mimeType, fileName, file)) =>
        println(" %s of type %s is %d bytes long" format (fileName, mimeType, file.length))

      case _ => println("No file?")

    }

    "#file" #> fileUpload(f => upload = Full(f)) &
      "type=submit" #> onSubmitUnit(processForm)

  }


}
