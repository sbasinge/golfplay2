package models

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import play.api.test._
import play.api.test.Helpers._
import models._
import enums._
import org.squeryl.PrimitiveTypeMode._

class FacilitySpec extends FlatSpec with ShouldMatchers {
  
  "A Facility" should "be creatable" in {
    
    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
      inTransaction {
	    AppDB.printDdl(println(_))
//	    AppDB.create
        val facility = AppDB.facilities insert Facility("foo","1-740-111-1111")
        facility.id should not equal(0)
        val address = AppDB.addressTable insert Address("foo","foo","foo",State.OH,"foo",facility.id)
        address.id should not equal(0)
	    facility.addresses.single.line1 should equal("foo")
	    println(facility)
	    println(facility.addresses.single)
      }
    }
  }

}
