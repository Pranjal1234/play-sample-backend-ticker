package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

class StockWidgetControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  

  "StockWidgetController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new StockWidgetController(stubMessagesControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Stock Watchlist")
    }

     "render the index page from the application" in {
      val controller = inject[StockWidgetController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Stock Watchlist")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Stock Watchlist")
    }
  }

  "StockWidgetController GET list of all widgets" should {
    
    "render the watch list page from a new instance of controller" in {
      val controller = new StockWidgetController(stubMessagesControllerComponents())
      val home = controller.listWidgets.apply(FakeRequest(GET, "/watchlist").withCSRFToken)

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Stock Watchlist")
      contentAsString(home) must include ("form")
    }

     "render the watch list page from the application" in {
      val controller = inject[StockWidgetController]
      val home = controller.listWidgets.apply(FakeRequest(GET, "/watchlist").withCSRFToken)

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Stock Watchlist")
      contentAsString(home) must include ("form")
    }

    "render the watch list page from the router" in {
      val request = FakeRequest(GET, "/watchlist").withCSRFToken
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Stock Watchlist")
      contentAsString(home) must include ("form")
    }
  }
}
