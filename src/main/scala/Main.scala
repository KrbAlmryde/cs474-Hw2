import scala.io.StdIn
import java.io.File

import com.hw2.understanding._
import com.hw2.understanding.GraphTypes._



/**
  * Created by krbalmryde on 10/6/16.
  */

object Main extends App {


    /**
      * Handy function which prompts the user with the supplied String and reads their input.
      * Up to me to make it pretty.
      *
      * This
      * http://stackoverflow.com/questions/5055349/how-to-take-an-input-from-a-user-on-scala
      */
    var fileNames = Array("./src/main/resources/SMSSync-2.7.udb", "./src/main/resources/SMSSync-3.0.5.udb")
    // convert these to false at deployment
    var isfileNames = Array(true, true)

    println("**------------------------------------------**")
    // Start by prompting the User for a VALID file name for each input
    val parsers = List(0, 1).map( i => {
        while(!isfileNames(i)) {
            try {
                fileNames(i) = StdIn.readLine("Please enter File#"+ (i+1) +": > ")
                isfileNames(i) = new File(fileNames(i)).exists
                if (!isfileNames(i)) println("File not Found! Try again...")
            } catch {
                case e: NullPointerException => {
                    println("Thanks for playing!")
                }
            }
        }
        fileNames(i)  // This returns the filenames in an List that we can then map to
    }).map(file => new UnderstandParser(file) )

    List(Deps, Call).foreach(pattern => {
        parsers(0).generateGraphs(pattern)
        parsers(1).generateGraphs(pattern)
        println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        println("Computing IsoMorphic case for "+ pattern + " Graphs")
        InspectSubGraph( parsers(0).getGraph(pattern), parsers(1).getGraph(pattern) )
    })

//    val inputs:Array[UnderstandParser] = fileNames.map( i => {
//        println("Is the supplied input a Path?: " +  i, if (new File(i).exists) " YES!" else " Shit, No" )
//        new UnderstandParser(i)
//    })
//
//    inputs.foreach(app => {
//        app.generateGraphs(Deps)
//        app.generateGraphs(Call)
//    })

//    InspectSubGraph(inputAppV1.getGraph(Deps), inputAppV2.getGraph(Deps))
//
//    println("\n**------------------------------------------**\n")
//
//    inputAppV1.generateGraphs(Call); inputAppV2.generateGraphs(Call)
//    println("computing IsoMorphic case for Call Graphs")
//    InspectSubGraph(inputAppV1.getGraph(Call), inputAppV2.getGraph(Call))

}