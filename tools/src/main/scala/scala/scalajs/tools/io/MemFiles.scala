/*                     __                                               *\
**     ________ ___   / /  ___      __ ____  Scala.js tools             **
**    / __/ __// _ | / /  / _ | __ / // __/  (c) 2013-2014, LAMP/EPFL   **
**  __\ \/ /__/ __ |/ /__/ __ |/_// /_\ \    http://scala-js.org/       **
** /____/\___/_/ |_/____/_/ | |__/ /____/                               **
**                          |/____/                                     **
\*                                                                      */


package scala.scalajs.tools.io

/** A base class for simple in-memory mutable virtual files. */
class MemVirtualFile(val path: String) extends VirtualFile {
  private[this] var _version: Option[Any] = None

  override def version: Option[Any] = _version
  def version_=(v: Option[Any]): Unit = _version = v

  final def withVersion(v: Option[Any]): this.type = {
    version = v
    this
  }
}

/** A simple in-memory mutable virtual text file. */
class MemVirtualTextFile(p: String) extends MemVirtualFile(p)
                                       with VirtualTextFile {
  private[this] var _content: String = ""

  override def content: String = _content
  def content_=(v: String): Unit = _content = v

  final def withContent(v: String): this.type = {
    content = v
    this
  }
}

/** A simple in-memory mutable virtual JS file. */
class MemVirtualJSFile(p: String) extends MemVirtualTextFile(p)
                                     with VirtualJSFile {
  private[this] var _sourceMap: Option[String] = None

  override def sourceMap: Option[String] = _sourceMap
  def sourceMap_=(v: Option[String]): Unit = _sourceMap = v

  final def withSourceMap(v: Option[String]): this.type = {
    sourceMap = v
    this
  }
}

/** A simple in-memory mutable virtual Scala.js class file. */
class MemVirtualScalaJSClassfile(p: String) extends MemVirtualJSFile(p)
                                               with VirtualScalaJSClassfile {
  private[this] var _info: String = ""

  override def info: String = _info
  def info_=(v: String): Unit = _info = v

  final def withInfo(v: String): this.type = {
    info = v
    this
  }
}

/** A simple in-memory mutable virtual Scala.js pack file. */
class MemVirtualScalaJSPackfile(p: String) extends MemVirtualJSFile(p)
                                              with VirtualScalaJSPackfile {
  private[this] var _packInfo: String = ""

  override def packInfo: String = _packInfo
  def packInfo_=(v: String): Unit = _packInfo = v

  final def withPackInfo(v: String): this.type = {
    packInfo = v
    this
  }
}
