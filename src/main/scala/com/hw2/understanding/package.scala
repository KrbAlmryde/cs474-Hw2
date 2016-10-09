package com.hw2

import java.io.File

import org.jgrapht._
import org.jgrapht.alg._
import org.jgrapht.graph._
import java.util.Iterator
import org.jgrapht.alg.isomorphism._

/**
  * Created by krbalmryde on 10/7/16.
  */

package object understanding {


    object GraphTypes extends Enumeration {
        val Call = Value(1)
        val Deps = Value(0)
    }
    /**
      * Convenience function which gives me the current working directory
      * @return
      */
    def pwd:String = System.getProperty("user.dir")


    /**
      * Name:
      *     ParseFilesInDir
      *
      * Description:
      *     Recursively parses Files in the local project Resources/ directory producing
      *     an array of Strings containing file paths to each of the source files
      *     found.
      *
      * Source:
      *     This function was adapted from the accepted answer of this StackOverflow question
      *     http://stackoverflow.com/questions/2637643/how-do-i-list-all-files-in-a-subdirectory-in-scala
      *
      * @param dir: a Java File object containing the source to the directory
      * @return Array[String]
      */
    def parseFilesInDir(dir: File): Array[File] = {
        val files = dir.listFiles
        val allFiles = files ++ files.filter(_.isDirectory).flatMap(parseFilesInDir)
        allFiles.filter( f => """.*\.java$""".r.findFirstIn(f.getName).isDefined)
    }



    def InspectSubGraph(alpha: SimpleDirectedGraph[String, DefaultEdge], beta:SimpleDirectedGraph[String, DefaultEdge]): Unit = {
        println("v1:" + alpha)
        println("v2:" + beta)
        println("Before" + "" + alpha.edgeSet().size(), beta.edgeSet().size())
        val inspector:VF2SubgraphIsomorphismInspector[String, DefaultEdge] = new VF2SubgraphIsomorphismInspector[String, DefaultEdge](alpha, beta)
        if (inspector.isomorphismExists()){
            println("Yep, it exists")
            val iterator = inspector.getMappings.asInstanceOf[java.util.Iterator[GraphMapping[String, DefaultEdge]]]
            while(iterator.hasNext) {
                println(iterator.next.toString)
            }
        } else {
            println("NOOOOPE")
        }

        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(alpha)
        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(beta)
        println("After" + "" + alpha.edgeSet().size(), beta.edgeSet().size())

    }
}
