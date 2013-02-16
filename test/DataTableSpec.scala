import org.specs2.matcher.DataTables
import org.specs2.Specification

class DataTableSpec extends Specification with DataTables { def is =
  "adding integers should just work in scala"  ! e1

  def e1 =
    "a"   | "b" | "c" | 
     2    !  2  !  4  | 
     1    !  1  !  2  |> {
     (a, b, c) =>  {
       println("Sum of "+a+" and "+b+" = "+c)
       a + b must_== c
     }
     
  }
}