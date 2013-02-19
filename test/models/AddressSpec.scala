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
//    "be creatable" in {
//    	
//      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
//        inTransaction {
//          "" | "line1" | "line2" | "city" | "state" | "zip" |
//            1 ! "foo1" ! "foo" ! "foo" ! State.OH ! "foo" |
//            2 ! "foo2" ! "foo" ! "foo" ! State.NC ! "foo" |
//            3 ! "foo3" ! "foo" ! "foo" ! State.VA ! "foo" |> {
//              (numAddresses, line1, line2, city, state, zip) =>
//
//                val address = AppDB.addressTable insert Address(line1, line2, city, state, zip)
//                println(address)
//                address.id must be not equalTo(0)
//            }
//        }
//      }
//    }
    
   "be creatable individually" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        inTransaction {
	        val address = AppDB.address insert Address("address line1","line2","city",State.OH,"43004")
	        address.id must be not equalTo(0)
        }
      }
    }

  }
}
