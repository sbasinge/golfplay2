package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import org.squeryl.PrimitiveTypeMode._
import views._
import models._
import scala.util.matching.Regex

object FacilityController extends Controller {

  val phoneRegex = "1?\\s*\\W?\\s*([2-9][0-8][0-9])\\s*\\W?\\s*([2-9][0-9]{2})\\s*\\W?\\s*([0-9]{4})(\\se?x?t?(\\d*))?"
  val matcher:Regex = phoneRegex.r
    
  /**
   * This result directly redirect to the application home.
   */
  val Home = Redirect(routes.FacilityController.list())

  def list = Action {
    Logger.info("Called facility controller .list")
    inTransaction {
      val allFacilities = from(AppDB.facilities)(f => select(f))
      Logger.info(allFacilities.size + " facilities found")
    }

    Ok(
      views.html.facilities.list(
        "Your new application is ready."))
  }

  def facilityForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "phone" -> text.verifying (pattern(phoneRegex.r,"constraint.phone","error.phone"))
    )
    (Facility.apply)(Facility.unapply)
  )

  def create = Action {
    Ok(views.html.facilities.createForm(facilityForm))
  }

  /**
   * Handle the 'new computer form' submission.
   */
  def save = Action { implicit request =>
    facilityForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.facilities.createForm(formWithErrors)),
      facility => {
        inTransaction {
	        AppDB.facilities.insert(facility)
	        Home.flashing("success" -> "facility %s has been created".format(facility.name))
        }
      })
  }

}