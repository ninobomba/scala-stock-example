import scala.collection.immutable.Seq

scalaVersion := "2.11.12"

name := "scala-stock-example"
organization := "ch.epfl.scala"
version := "1.0"

val sparkVersion = "2.4.8"

resolvers ++= Seq(
  "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven",
  "Typesafe Simple Repository" at "https://repo.typesafe.com/typesafe/simple/maven-releases",
  "MavenRepository" at "https://mvnrepository.com"
)

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",

  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,

  "com.lihaoyi" %% "requests" % "0.8.0",

  "org.apache.logging.log4j" % "log4j-api-scala_2.13" % "12.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.19.0" % Runtime,

  "com.github.philcali" %% "cronish" % "0.1.3"
)
