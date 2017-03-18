import slick.{ model => m }

// mysql
lazy val databaseUrl = sys.env.getOrElse("DB_DEFAULT_URL", "dbc:mysql://127.0.0.1/test?characterEncoding=UTF8")
lazy val databaseUser = sys.env.getOrElse("DB_DEFAULT_USER", "test")
lazy val databasePassword = sys.env.getOrElse("DB_DEFAULT_PASSWORD", "test")
lazy val driver = slick.driver.MySQLDriver
lazy val jdbcDriver = "com.mysql.jdbc.Driver"
lazy val outputPackage = "models"
lazy val outputAbsoluteParentDirPath = ""

lazy val root = (project in file("."))
  .settings(slickCodegenSettings:_*)
  .settings(
    name := "slick-codegen",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-slick" % "1.1.0",
      "com.typesafe.slick" %% "slick" % "3.1.0",
      "joda-time" % "joda-time" % "2.7",
      "org.joda" % "joda-convert" % "1.7",
      "com.github.tototoshi" %% "slick-joda-mapper" % "2.1.0"
    ),
    slickCodegenDatabaseUrl := databaseUrl,
    slickCodegenDatabaseUser := databaseUser,
    slickCodegenDatabasePassword := databasePassword,
    slickCodegenDriver := driver,
    slickCodegenJdbcDriver := jdbcDriver,
    slickCodegenOutputPackage := outputPackage,
    slickCodegenExcludedTables := Seq("schema_version"),
    slickCodegenCodeGenerator := { (model:  m.Model) =>
      new slick.codegen.SourceCodeGenerator(model) {
        override def code =
          "import com.github.tototoshi.slick.MySQLJodaSupport._\n" + "import org.joda.time.DateTime\n" + super.code
        override def Table = new Table(_) {
          override def Column = new Column(_) {
            override def rawType = model.tpe match {
              case "java.sql.Timestamp" => "DateTime" // kill j.s.Timestamp
              case _ =>
                super.rawType
            }
          }
        }
      }
    },
    sourceGenerators in Compile <+= slickCodegen,
    slickCodegenOutputDir := (sourceManaged in Compile).value / "a"
//    slickCodegenOutputDir := file(outputAbsoluteParentDirPath)
  )

