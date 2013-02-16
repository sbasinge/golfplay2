import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import org.squeryl.PrimitiveTypeMode._
import org.specs2.matcher.DataTables

class AddressSpec extends Specification with DataTables {
	import models._
	import enums._

  //  "create an xml file in the specified output directory, handling file separators" in {
  //       "output dir" |   "spec name" |   "file path"             |>
  //       ""           !   "spec1"     !   "./spec1.xml"           |  
  //       "result"     !   "spec1"     !   "./result/spec1.xml"    |  
  //       "result/"    !   "spec1"     !   "./result/spec1.xml"    |  
  //       "result\\"   !   "spec1"     !   "./result/spec1.xml"    |  
  //       "/result"    !   "spec1"     !   "/result/spec1.xml"     |
  //       "\\result"   !   "spec1"     !   "/result/spec1.xml"     |
  //       "result/xml" !   "spec1"     !   "./result/xml/spec1.xml"| { (dir, spec, result) =>
  //           xmlRunner.outputDir = dir
  //           spec1.name = spec
  //           xmlRunner.execute
  //           xmlRunner.files must haveKey(result)
  //       }
  //    }
  
//   "Computer model" should {
//    
//    "be retrieved by id" in {
//      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
//        
//        val Some(macintosh) = Computer.findById(21)
//      
//        macintosh.name must equalTo("Macintosh")
//        macintosh.introduced must beSome.which(dateIs(_, "1984-01-24"))  
//        
//      }
//    }
//   }

  "An Address" should {
    "be creatable" in {
    	
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        inTransaction {
          AppDB.create
          val facility = AppDB.facilities insert Facility("foo", "1-740-111-1111")
          facility.id must be not equalTo(0)
          
          "" | "line1" | "line2" | "city" | "state" | "zip" |
            1 ! "foo1" ! "foo" ! "foo" ! State.OH ! "foo" |
            2 ! "foo2" ! "foo" ! "foo" ! State.NC ! "foo" |
            3 ! "foo3" ! "foo" ! "foo" ! State.VA ! "foo" |> {
              (justIgnoreMe, line1, line2, city, state, zip) =>

                val address = AppDB.addressTable insert Address(line1, line2, city, state, zip, facility.id)
                println(address)
                address.id must be not equalTo(0)
                address.facilityId must be equalTo(facility.id)
            }
        }
      }
    }
  }

  //    "An Address in Ohio should have state OH" in {
  //    
  //    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
  //      inTransaction {
  //	    AppDB.printDdl(println(_))
  //	    AppDB.create
  //        val facility = AppDB.facilities insert Facility("foo","1-740-111-1111")
  //        facility.id should not equal(0)
  //        val address = AppDB.addressTable insert Address("foo","foo","foo",State.OH,"foo",facility.id)
  //        address.state should equal(State.OH)
  //      }
  //    }
  //  }

}
