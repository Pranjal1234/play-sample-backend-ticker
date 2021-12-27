package controllers

import javax.inject.{Inject, Singleton}

import play.api.http.ContentTypes
import play.api.libs.EventSource
import play.api.mvc._
import yahoofinance.YahooFinance
import models.StockWidget
import play.api.libs.json.JsPath
import play.api.libs.json.Json

@Singleton
class StockSourceController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with ScalaStockTicker {

  def index(symbol: String) = Action {
    val stock = new StockWidget(symbol, 
        YahooFinance.get(symbol).getName(), 
        YahooFinance.get(symbol).getQuote().getPrice(),
        YahooFinance.get(symbol).getQuote().getPriceAvg50(),
        YahooFinance.get(symbol).getQuote().getPriceAvg200(),
        YahooFinance.get(symbol).getDividend.getAnnualYield(),
        YahooFinance.get(symbol).getQuote().getYearLow(),
        YahooFinance.get(symbol).getQuote().getYearHigh()
    )
    Ok(views.html.stockdetail(stock))
  }

  def streamStockTicker(symbol: String) = Action {
    Ok.chunked(stringStockSource(symbol) via EventSource.flow).as(ContentTypes.EVENT_STREAM)
  }
}
