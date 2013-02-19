package models

import org.squeryl.dsl.ManyToOne
import org.squeryl.PrimitiveTypeMode._

case class Course(name: String, phone: String, addressId: Long) extends BaseEntity {
  lazy val address: ManyToOne[Address] = AppDB.addressToCourses.right(this)

}

object Course {
  def all: List[Course] = inTransaction(from(AppDB.course) { s => select(s) }.toList)

  def add(name:String, phone: String, addressId: Long) {
    inTransaction {
      AppDB.course.insert(new Course(name, phone, addressId))
    }
  }

    def add(newCourse: Course) {
    inTransaction {
      AppDB.course.insert(newCourse)
    }
  }

  def delete(id: Long) {
    inTransaction {
      AppDB.course.delete(id)
    }
    
  }
  
}
