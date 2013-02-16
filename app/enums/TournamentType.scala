package enums;

object TournamentType extends Enumeration {
  type TournamentType = Value
  
  val BESTBALL = Value(1,"Best Ball")
  val GROSS_SCORE = Value(2,"Gross Score")
  val NET_SCORE = Value(3,"Net Score")
  val SCRAMBLE = Value(4,"Scramble")

}
