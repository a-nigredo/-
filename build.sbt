lazy val commonSettings = Seq(
  organization := "dev.nigredo",
  version := "0.1",
  scalaVersion := "2.12.3",
  libraryDependencies += "org.specs2" %% "specs2-core" % "3.9.5" % "test",
  libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.0-MF",
  libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0",
  libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
  scalacOptions in Test ++= Seq("-Yrangepos"),
  crossPaths := false
)

lazy val core = (project in file("core")).settings(commonSettings, name := "core")

lazy val repositoryMongo =
  (project in file("repository-mongo")).settings(commonSettings, name := "repository-mongo").dependsOn(core)

lazy val validator =
  (project in file("validator")).settings(commonSettings, name := "validator").dependsOn(core)

lazy val service =
  (project in file("service")).settings(commonSettings, name := "service").dependsOn(core)

lazy val root = (project in file("."))
  .settings(commonSettings, name := "blog")
  .aggregate(core, repositoryMongo, validator, service)
  .dependsOn(core, repositoryMongo, validator, service)