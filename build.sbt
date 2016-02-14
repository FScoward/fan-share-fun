import com.typesafe.config.ConfigFactory
import play.sbt.PlayImport.PlayKeys.playRunHooks

name := """fan-share-fun"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    slick <<= slickCodeGenTask
  )

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.20",
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "jquery" % "2.2.0",
  "org.twitter4j" % "twitter4j-core" % "4.0.1",
  "com.typesafe.slick" %% "slick" % "3.0.2",
  "com.typesafe.slick" %% "slick-codegen" % "3.0.0",
  "com.typesafe.play" %% "play-slick" % "1.0.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.0.1"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

playRunHooks <+= baseDirectory.map(base => GulpRunner(base))

includeFilter in (Assets, LessKeys.less) := "*.less"

lazy val config = ConfigFactory.parseFile(new File("./conf/application.conf"))
lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val outputDir = (dir / "slick").getPath // place generated files in sbt's managed sources folder
  val username = config.getString("slick.dbs.default.username")
  val password = config.getString("slick.dbs.default.password")
  val url = config.getString("slick.dbs.default.url") // connection info for a pre-populated throw-away, in-memory db for this demo, which is freshly initialized on every run
  val jdbcDriver = config.getString("slick.dbs.default.driver")
  val slickDriver = "slick.driver.MySQLDriver"
  val pkg = "repository"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg, username, password), s.log))
  val fname = outputDir + "/slick/Tables.scala"
  Seq(file(fname))
}
