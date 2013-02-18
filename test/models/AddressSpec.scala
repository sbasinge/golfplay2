package models

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import org.squeryl.PrimitiveTypeMode._
import org.specs2.matcher.DataTables
import enums.State

class AddressSpec extends Specification with DataTables {
	import models._
	import enums._

  "An Address" should {
    "be creatable" in {
    	
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        inTransaction {
//          AppDB.create
          val facility = AppDB.facilities insert Facility("foo", "1-740-111-1111")
          facility.id must be not equalTo(0)
          
          "" | "line1" | "line2" | "city" | "state" | "zip" |
            1 ! "foo1" ! "foo" ! "foo" ! State.OH ! "foo" |
            2 ! "foo2" ! "foo" ! "foo" ! State.NC ! "foo" |
            3 ! "foo3" ! "foo" ! "foo" ! State.VA ! "foo" |> {
              (numAddresses, line1, line2, city, state, zip) =>

                val address = AppDB.addressTable insert Address(line1, line2, city, state, zip, facility.id)
                println(address)
                address.id must be not equalTo(0)
                address.facilityId must be equalTo(facility.id)
                facility.addresses.size must be equalTo(numAddresses)
            }
        }
      }
    }
  }
}
