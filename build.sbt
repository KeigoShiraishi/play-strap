name := """play-strap"""

version := "0.0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  filters,
  "mysql" % "mysql-connector-java" % "5.1.40",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

CoffeeScriptKeys.sourceMap := false

LessKeys.compress in Assets := true

includeFilter in (Assets, LessKeys.less) := "*.less"

includeFilter in gzip := "*.js"

pipelineStages := Seq(gzip)
