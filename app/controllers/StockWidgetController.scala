package controllers

import javax.inject.Inject

import models.StockWidget
import yahoofinance.YahooFinance
import play.api.data._
import play.api.i18n._
import play.api.mvc._

import scala.collection._

class StockWidgetController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  import StockWidgetForm._

  private val widgets = mutable.ArrayBuffer[StockWidget]()
  private val postUrl = routes.StockWidgetController.createWidget

  def index = Action {
    Ok(views.html.index())
  }

  def listWidgets = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.stockwidgetlist(widgets.toSeq, form, postUrl))
  }

  def removeWidget(stock: String) = Action { implicit request: MessagesRequest[AnyContent] =>
    widgets -= widgets.find((s) => s.symbol == stock).get
    Redirect(routes.StockWidgetController.listWidgets)
  }

  def createWidget = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Data] =>
      BadRequest(views.html.stockwidgetlist(widgets.toSeq, formWithErrors, postUrl))
    }

    val successFunction = { data: Data =>
      val stock = YahooFinance.get(data.symbol)
      val stockWidget = StockWidget(
        symbol = data.symbol, 
        name = stock.getName(),
        price = stock.getQuote().getPrice(),
        priceAvg50 = stock.getQuote().getPriceAvg50(),
        priceAvg200 = stock.getQuote().getPriceAvg200(),
        dividend = stock.getDividend().getAnnualYield(),
        lowYear = stock.getQuote().getYearLow(),
        highYear = stock.getQuote().getYearHigh()
      )
      widgets += stockWidget
      Redirect(routes.StockWidgetController.listWidgets)
    }

    val formValidationResult = form.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }
}