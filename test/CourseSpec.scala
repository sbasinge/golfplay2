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
//	    AppDB.printDdl(println(_))
	    AppDB.create
        val facility = AppDB.facilities insert Facility("foo","1-740-111-1111")
        facility.id should not equal(0)
        val course = AppDB.courses insert Course("foo",facility.id)
        course.id should not equal(0)
	    facility.courses.single.name should equal("foo")
	    println(facility)
	    println(facility.courses.single)
      }
    }
  }

}
