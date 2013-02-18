package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import org.squeryl.PrimitiveTypeMode._
import views._
import models._
import org.squeryl.KeyedEntity

object FacilityController extends Controller {

  val phoneRegex = "1?\\s*\\W?\\s*([2-9][0-8][0-9])\\s*\\W?\\s*([2-9][0-9]{2})\\s*\\W?\\s*([0-9]{4})(\\se?x?t?(\\d*))?"

  /**
   * This result directly redirect to the application home.
   */
  val Home = Redirect(routes.FacilityController.list())

  def list = Action {
    Logger.info("Called facility controller .list")
    inTransaction {
      val allFacilities = from(AppDB.facilities)(f => select(f)).seq
      Logger.info(allFacilities.size + " facilities found")
      Ok(html.facilities.list("Your new application is ready.", allFacilities))
    }

  }

  def facilityForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "phone" -> text.verifying(pattern(phoneRegex.r, "constraint.phone", "error.phone")))(Facility.apply)(Facility.unapply))

  def create = Action {
    Ok(html.facilities.form(facilityForm, "Add", -1))
  }

  /**
   * Handle the 'new facility form' submission.
   */
  def save(messageType: String, id: Long) = Action { implicit request =>
    facilityForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.facilities.form(formWithErrors, messageType, id)),
      facility =>
        {
          inTransaction {
            if (messageType == "Add") {
              AppDB.facilities.insert(facility)
              Home.flashing("success" -> "facility %s has been created".format(facility.name))
            } else {
              Logger.info(" About to update facility %s".format(facility))
              AppDB.facilities.update(n =>
                where(n.id === id)
                  set (
                    n.name := facility.name,
                    n.phone := facility.phone))
              Home.flashing("success" -> "facility %s has been updated".format(facility.name))
            }
          }
        })
  }

  def edit(id: Long) = Action {
    inTransaction {
      val existingFacility = AppDB.facilities.get(id)
      Ok(html.facilities.form(facilityForm.fill(existingFacility), "Edit", existingFacility.id))
    }
  }

    def delete(id: Long) = Action {
    inTransaction {
      val existingFacility = AppDB.facilities.delete(id)
      Home.flashing("success" -> "facility %s has been deleted".format(id))
    }
  }


}