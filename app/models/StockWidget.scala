package models

import play.api.libs.json.Json
import scala.collection.mutable.ArrayBuffer

case class StockWidget(
    symbol: String, 
    name: String, 
    price: BigDecimal,
    priceAvg50: BigDecimal,
    priceAvg200: BigDecimal,
    dividend: BigDecimal,
    lowYear: BigDecimal,
    highYear: BigDecimal
)


