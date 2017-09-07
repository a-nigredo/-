name := "blog"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.0-MF"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.9.5" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")

crossPaths := false