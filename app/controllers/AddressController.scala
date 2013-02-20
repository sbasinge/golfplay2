package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints._

import org.squeryl.PrimitiveTypeMode._
import views._
import models._

object AddressController extends Controller {

    val Home = Redirect(routes.AddressController.list())

  def list = Action {
    val allAddresses = Address.all
    Logger.info(allAddresses.size + " addresses found")
    Ok(html.addresses.list("", allAddresses))
      
//    Ok(html.courses.list("", Address.all))
  }

  def create = Action {
    Ok("Your new application is ready.")
  }

  def save(messageType: String, id: Long) = Action {
    Ok("Your new application is ready.")
  }

  def edit(id: Long) = Action {
    Ok("Your new application is ready.")
  }

  def delete(id: Long) = Action {
    Ok("Your new application is ready.")
  }

}