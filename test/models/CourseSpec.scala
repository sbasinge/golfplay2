package models

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import play.api.test._
import play.api.test.Helpers._
import models._
import enums._
import org.squeryl.PrimitiveTypeMode._

class CourseSpec extends FlatSpec with ShouldMatchers {
  
  "A Course" should "be creatable" in {
    
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      inTransaction {
	    AppDB.printDdl(println(_))
//	    AppDB.create
        val address = AppDB.address insert Address("address line1","line2","city",State.OH,"43004")
        address.id should not equal(0)
        val course = AppDB.course insert Course("foo","1-111-111-1111",address.id)
        course.id should not equal(0)
        course.address.last.line1 should equal("address line1")
      }
    }
  }

}
