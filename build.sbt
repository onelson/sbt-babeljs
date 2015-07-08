sbtPlugin := true

organization := "com.theomn.sbt"

name := "sbt-babeljs"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "com.typesafe" % "jstranspiler" % "1.0.0",
  "org.webjars"  % "mkdirp" % "0.5.0",
  "org.webjars"  % "when-node" % "3.5.2-3",
  "org.webjars"  % "babel" % "4.7.16"
)

resolvers ++= Seq(
  "Typesafe Releases Repository" at "https://repo.typesafe.com/typesafe/maven-releases/"
)

addSbtPlugin("com.typesafe.sbt" %% "sbt-js-engine" % "1.1.3")

publishMavenStyle := false

publishTo := {
  if (isSnapshot.value) Some(Classpaths.sbtPluginSnapshots)
  else Some(Classpaths.sbtPluginReleases)
}

scriptedSettings

scriptedLaunchOpts <+= version apply { v => s"-Dproject.version=$v" }
