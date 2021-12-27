package controllers

object StockWidgetForm {
  import play.api.data.Forms._
  import play.api.data.Form

  case class Data(symbol: String)

  val form = Form(
    mapping(
      "symbol" -> nonEmptyText,
    )(Data.apply)(Data.unapply)
  )
}