object Functions {

    // adds two integers together
    def plus(x: Int, y:Int):Int = x+y
    
    // salutes the given name, e.g. Hello, User
    def salutation(name: String):String = "Hello, "+name

    // counts the number of characters in a given string
    def characters(name: String):Int = name.length


    // returns the base x to the power y, i.e. x^y
    def pow(x: Int, y: Int) = scala.math.pow(x,y)

    /** Salutes the user according to the time of day given in isDayTime
      * During daytime, salutes with "Hello". During nighttime, salutes with "Good night"
      */
    def dayOrNightSalutation(name:String, isDayTime: Boolean) = if(isDayTime==false) "Good night, "+name else "Hello, "+name

    // return first two names in a given list
    def firstTwo(names: List[String]) =  names.take(2)
    //var namesL= Map(1 -> names(0), 2 -> names(1))
        //for(0 <- 0 to 1) 
        //nameL = new Array[String](1)
        //var i = 0
        //for(i <- 0 to 1) nameL(i) = names(i)
        //return nameL()

    // return the last name in a given list
    def lastName(names: List[String]) = names.last

    // combines two lists
    def combine(names: List[String], letters: List[String]) = names ++ letters
    



    def main(args: Array[String]) {
        
        println(s"${Console.GREEN}Running ${tests.size} tests:")

        tests.foreach { (fixture) =>
            val name = fixture._1
            val test = fixture._2
            try {
                print(s"${Console.GREEN}\t- '$name': ")
                test()
                println("PASS!")
            } catch {
                case e:Error => {
                    println(Console.RED + e.getMessage)
                }
            }

        }
    }

    def verify(expected: Any, actual: Any) =
        assert(expected == actual, s"Expected: $expected, actual: $actual")

    val tests = List[(String, Function0[Unit])](
        ("plus", () => {
            verify(6, plus(2, 4))
            verify(4, plus(2, 2))
        }),
        ("salutation", () => {
            verify("Hello, World", salutation("World"))
            verify("Hello, Mark" , salutation("Mark"))
        }),
        ("character count", () => {
            verify(6, characters("foobar"))
            verify(22, characters("functional programming"))
        }),
        ("power", () => {
            verify(216, pow(6, 3))
            verify(32, pow(2, 5))
        }),
        ("time-of-day-aware salutation", () => {
            verify("Hello, Mark", dayOrNightSalutation("Mark", true))
            verify("Good night, Mark", dayOrNightSalutation("Mark", false))
            verify("Good night, World", dayOrNightSalutation("World", false))
        }),
        ("get first two names in list", () => {
            verify(List("Jill", "Jack"), firstTwo(participants))
        }),
        ("get last two names in list", () => {
            verify("Mary", lastName(participants))
        }),
        ("combine two lists", () => {
            verify(List("Jill", "Jack", "John", "George", "Mary", "a", "b", "c"), combine(participants, letters))
        })
    )

    val participants = List("Jill", "Jack", "John", "George", "Mary")
    val letters = List("a", "b", "c")
}