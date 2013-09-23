import sbtappengine.Plugin.{AppengineKeys => gae}

name := "google-doc-proxy"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "7.6.8.v20121106" % "container",
  "net.liftweb" % "lift-json_2.10" % "2.5.1"
)

appengineSettings
