package controllers

import play.api._
import play.api.mvc._
import models.AppDB
import org.squeryl.PrimitiveTypeMode._

object Facility extends Controller {
  
	def list = Action {
	  Logger.info("Called facility controller .list")
	  inTransaction {
		  val allFacilities = from(AppDB.facilities)(f=> select(f))
		  Logger.info(allFacilities.size+" facilities found")
	  }
	  
		Ok(
		    views.html.facilities.list(
		        "Your new application is ready."
	        )
		)
	}
}