import org.jetbrains.sbtidea.Keys.*

lazy val inspections =
  project.in(file("."))
    .enablePlugins(SbtIdeaPlugin)
    .settings(
      version := "1.0.0",
      scalaVersion := "3.4.0",
<<<<<<< HEAD
      ThisBuild / intellijPluginName := "Aalto Scala inspections",
=======
      ThisBuild / intellijPluginName := "Additional Scala Inspections",
>>>>>>> review
      ThisBuild / intellijBuild      := "231.9011.34",
      ThisBuild / intellijPlatform   := IntelliJPlatform.IdeaCommunity,
      Global    / intellijAttachSources := true,
      Compile / javacOptions ++= "--release" :: "17" :: Nil,
      intellijPlugins += "com.intellij.properties".toPlugin ,
      intellijPlugins += "org.intellij.scala".toPlugin,
      Compile / unmanagedResourceDirectories += baseDirectory.value / "resources",
      Test / unmanagedResourceDirectories    += baseDirectory.value / "testResources",

    )
