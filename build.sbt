lazy val root = (project in file(".")).enablePlugins(PlayScala)

name := """play-sample-backend-ticker"""
organization := "PranjalPatel"
version := "1.0-SNAPSHOT"
scalaVersion := "2.13.7"

scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-Xfatal-warnings"
)
libraryDependencies ++= Seq(
    guice,
    ws % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
    "com.yahoofinance-api"    % "YahooFinanceAPI" % "3.15.0"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "PranjalPatel.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "PranjalPatel.binders._"
