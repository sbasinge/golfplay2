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
import org.squeryl.KeyedEntity

object CourseController extends Controller {

  val phoneRegex = "1?\\s*\\W?\\s*([2-9][0-8][0-9])\\s*\\W?\\s*([2-9][0-9]{2})\\s*\\W?\\s*([0-9]{4})(\\se?x?t?(\\d*))?"

  /**
   * This result directly redirect to the application home.
   */
  val Home = Redirect(routes.CourseController.list())

  def list = Action {
    Logger.info("Called course controller .list")
    val allCourses = Course.all
    Logger.info(allCourses.size + " courses found")
    Ok(html.courses.list("Your new application is ready.", allCourses))
  }

  def courseForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "phone" -> text.verifying(pattern(phoneRegex.r, "constraint.phone", "error.phone")),
      "addressId" -> of[Long])(Course.apply)(Course.unapply))

  def create = Action {
    Ok(html.courses.form(courseForm, "Add", -1))
  }

  /**
   * Handle the 'new course form' submission.
   */
  def save(messageType: String, id: Long) = Action { implicit request =>
    courseForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.courses.form(formWithErrors, messageType, id)),
      course =>
        {
          inTransaction {
            if (messageType == "Add") {
              Course.add(course)
              Home.flashing("success" -> "course %s has been created".format(course.name))
            } else {
              Logger.info(" About to update course %s".format(course))
              AppDB.course.update(n =>
                where(n.id === id)
                  set (
                    n.name := course.name,
                    n.phone := course.phone))
              Home.flashing("success" -> "course %s has been updated".format(course.name))
            }
          }
        })
  }

  def edit(id: Long) = Action {
    inTransaction {
      val existingCourse = AppDB.course.get(id)
      Ok(html.courses.form(courseForm.fill(existingCourse), "Edit", existingCourse.id))
    }
  }

    def delete(id: Long) = Action {
      Course.delete(id)
      Home.flashing("success" -> "course %s has been deleted".format(id))
    }
}