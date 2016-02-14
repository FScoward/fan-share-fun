import play.sbt.PlayRunHook
import sbt._

object GulpRunner {
  def apply(base: File): PlayRunHook = {
    object GulpRunnerHook extends PlayRunHook {

      override def beforeStarted() = {
        System.getProperty("os.name").toLowerCase() match {
          case x if x.contains("win") => Process("cmd /c gulp", base).run()
          case _ => Process("gulp", base).run()
        }
      }
    }
    GulpRunnerHook
  }
}