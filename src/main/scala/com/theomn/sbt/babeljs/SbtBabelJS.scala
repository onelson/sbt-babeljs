package com.theomn.sbt.babeljs

import sbt._
import sbt.Keys._
import com.typesafe.sbt.web._
import com.typesafe.sbt.jse.SbtJsTask
import spray.json._


object Import {

  object BabelJSKeys {
    val babeljs = TaskKey[Seq[File]]("babeljs", "Invoke the babel compiler.")

    val sourceMaps = SettingKey[Boolean]("babeljs-sourcemaps", "Add sourcemap data to the bottom of each processed file.")
  }

}

object SbtBabelJS extends AutoPlugin {

  override def requires = SbtJsTask

  override def trigger = AllRequirements

  val autoImport = Import

  import SbtWeb.autoImport._
  import WebKeys._
  import SbtJsTask.autoImport.JsTaskKeys._
  import autoImport.BabelJSKeys._

  val babeljsUnscopedSettings = Seq(
    includeFilter := "*.es6.js" || "*.es6" || "*.jsx.js" || "*.jsx",
    jsOptions := JsObject(
      "sourceMap" -> JsString(if (sourceMaps.value) "inline" else "")
    ).toString()
  )

  override def projectSettings = Seq(
    sourceMaps := false

  ) ++ inTask(babeljs)(
    SbtJsTask.jsTaskSpecificUnscopedSettings ++
      inConfig(Assets)(babeljsUnscopedSettings) ++
      inConfig(TestAssets)(babeljsUnscopedSettings) ++
      Seq(
        moduleName := "babeljs",
        shellFile := getClass.getClassLoader.getResource("babel-shell.js"),

        taskMessage in Assets := "BabelJS compiling",
        taskMessage in TestAssets := "BabelJS test compiling"
      )
  ) ++ SbtJsTask.addJsSourceFileTasks(babeljs) ++ Seq(
    babeljs in Assets := (babeljs in Assets).dependsOn(
      nodeModules in Plugin,
      webModules in Assets
    ).value,
    babeljs in TestAssets := (babeljs in TestAssets).dependsOn(
      nodeModules in Plugin,
      webModules in TestAssets
    ).value
  )

}