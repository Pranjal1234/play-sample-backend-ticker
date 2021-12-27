# Play Sample Backend Ticker using Scala - Play Framework

To follow the steps in this tutorial, you will need the correct version of Java and sbt. The template requires:

* Java Software Developer's Kit (SE) 1.8 or not higher then Java 11.
* sbt 1.3.13

To check your Java version, enter the following in a command window:

```bash
java -version
```

To check your sbt version, enter the following in a command window:

```bash
sbt sbtVersion
```

If you do not have the required versions, follow these links to obtain them:

* [Java 11 SE](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* [sbt](http://www.scala-sbt.org/download.html)

## Build and run the project

This example Play project was created from a seed template. It includes all Play components and an Akka HTTP server. The project is also configured with filters for Cross-Site Request Forgery (CSRF) protection and security headers.

To build and run the project:

1. Use a command window to change into the example project directory, for example: `cd play-sample-backend-ticker`

2. Build the project. Enter: `sbt run`. The project builds and starts the embedded HTTP server. Since this downloads libraries and dependencies, the amount of time required depends partly on your connection's speed.

3. After the message `Server started, ...` displays, enter the following URL in a browser: <http://localhost:9000>

The Play application responds: `Stock Watchlist!`

## How to use

Once on the home page, there is a link to the watch list. `Stock Watch List`

On the watchlist page, there is a text field to input Stock symbol to add to the list. Once the stock is added to the list, there is a link for each stock on the symbol and an option to delete at the end.
