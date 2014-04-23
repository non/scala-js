package scala.scalajs.tools.io

import java.io.StringWriter

class MemVirtualTextFileWriter extends VirtualTextFileWriter {
  val contentWriter = new StringWriter

  def toVirtualFile(name: String): MemVirtualTextFile =
    addToFile(new MemVirtualTextFile(name))

  def close() = {
    contentWriter.close()
  }

  protected def addToFile(vf: MemVirtualTextFile): vf.type =
    vf.withContent(contentWriter.toString)
}

class MemVirtualJSFileWriter extends MemVirtualTextFileWriter
                                with VirtualJSFileWriter {

  private var sourceMapUsed = false
  lazy val sourceMapWriter = {
    sourceMapUsed = true
    new StringWriter
  }

  override def toVirtualFile(name: String): MemVirtualJSFile =
    addToFile(new MemVirtualJSFile(name))

  override def close() = {
    super.close()
    if (sourceMapUsed)
      sourceMapWriter.close()
  }

  protected def addToFile(vf: MemVirtualJSFile): vf.type = {
    super.addToFile(vf)
    if (sourceMapUsed)
      vf.withSourceMap(Some(sourceMapWriter.toString))
    else
      vf.withSourceMap(None)

    vf
  }
}

class MemVirtualScalaJSPackfileWriter extends MemVirtualJSFileWriter
                                         with VirtualScalaJSPackfileWriter {
  val packInfoWriter = new StringWriter

  override def toVirtualFile(name: String): MemVirtualScalaJSPackfile =
    addToFile(new MemVirtualScalaJSPackfile(name))

  protected def addToFile(vf: MemVirtualScalaJSPackfile): vf.type = {
    super.addToFile(vf)
    vf.withPackInfo(packInfoWriter.toString)
  }

  override def close() = {
    super.close()
    packInfoWriter.close()
  }

}
