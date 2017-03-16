
name := """play-framework-modern-business"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

CoffeeScriptKeys.sourceMap := false

LessKeys.compress in Assets := true

includeFilter in (Assets, LessKeys.less) := "*.less"

includeFilter in gzip := "*.js"

pipelineStages := Seq(gzip)
