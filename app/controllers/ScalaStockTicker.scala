package controllers

import akka.stream.scaladsl.Source
import yahoofinance.YahooFinance

import scala.concurrent.duration._

trait ScalaStockTicker {

  def stringStockSource(symbol: String): Source[String, _] = {
      val tickStockSource = Source.tick(0.millis, 1000.millis, "TICK")
      val s = tickStockSource.map(_ => getStockPrice(symbol).getOrElse("0"))
      s
  }

  def getStockPrice(symbol: String): Option[String] = {
    val stock = YahooFinance.get(symbol)
    val price = stock.getQuote(true).getPrice()
    if(price == null) {
        None
    } else {
        Some("$" + price.toString())
    }
  }
}
