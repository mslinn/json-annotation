organization := "com.github.vital-software"

name := "json-annotation"

scalaVersion := "2.13.1"

crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.1")

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

libraryDependencies ++= Seq(
  "org.scala-lang"    %  "scala-reflect" % scalaVersion.value,
  "com.typesafe.play" %% "play-json"     % "2.7.4" % Test,
  "org.specs2"        %% "specs2-core"   % "4.7.1" % Test
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

Compile / scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n >= 13 => "-Ymacro-annotations" :: Nil
    case _ => Nil
  }
}

libraryDependencies ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n >= 13 => Nil
    case _ => compilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full) :: Nil
  }
}

scalacOptions in ThisBuild ++= Seq(
  "-unchecked",
  "-deprecation"
)
