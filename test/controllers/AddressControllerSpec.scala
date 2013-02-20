package controllers

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class AddressControllerSpec extends Specification {
  import models._

  def dateIs(date: java.util.Date, str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str

  "AddressController" should {
    "redirect to address list on /addresses" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        val result = controllers.AddressController.list(FakeRequest())
        status(result) must equalTo(OK)
        redirectLocation(result) must beSome.which(_ == "/addresses")
      }
    }

    "redirect to edit address page on /addresses/:id" in {
      val result = controllers.AddressController.edit(123)(FakeRequest())
      status(result) must equalTo(OK)
      redirectLocation(result) must beSome.which(_ == "/addresses/:id")
    }

    "redirect to create address page on /addresses/new" in {
      val result = controllers.AddressController.create(FakeRequest())
      status(result) must equalTo(OK)
      redirectLocation(result) must beSome.which(_ == "/addresses/new")
    }

    "redirect to address list on save" in {
      val result = controllers.AddressController.save("Add", -1)(FakeRequest())
      status(result) must equalTo(OK)
      redirectLocation(result) must beSome.which(_ == "/addresses")
    }

    "redirect to address list on delete" in {
      val result = controllers.AddressController.delete(-1)(FakeRequest())
      status(result) must equalTo(OK)
      redirectLocation(result) must beSome.which(_ == "/addresses")
    }

  }
}