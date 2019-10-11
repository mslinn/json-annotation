import scala.sys.process._

bintrayOrganization := Some("micronautics")
bintrayRepository := "scala"
bintrayPackage := name.value

/** Publish a new version of this library to BinTray.
  * Be sure to update the version string at the end of this file before running this task. */
lazy val publishAndTag =
  taskKey[Unit]("Invokes commitAndPublish, then creates a git tag for the version string defined in build.sbt")

publishAndTag := {
  publish.value
  println(s"Creating git tag for v${ version.value }")
  s"""git tag -a ${ version.value } -m ${ version.value }""".!!
  s"""git push origin --tags""".!!
  ()
}

version := "0.6.0"
