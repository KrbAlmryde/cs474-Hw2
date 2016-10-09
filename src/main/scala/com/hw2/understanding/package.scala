package com.hw2

import java.io.File

import org.jgrapht._
import org.jgrapht.alg._
import org.jgrapht.graph._
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


    /**
      * Performs a Inspects the input Graph and Subgraph for an Isomorphic-case, printing the results, if
      * such a case exists (Up to 50 elements or the end of the list, whichever comes first)
      * @param alpha Version 1 Graph, this will behave as the parent graph
      * @param beta Version
      */
    def InspectSubGraph(alpha: SimpleDirectedGraph[String, DefaultEdge], beta:SimpleDirectedGraph[String, DefaultEdge]): Unit = {
        val inspector:VF2SubgraphIsomorphismInspector[String, DefaultEdge] = new VF2SubgraphIsomorphismInspector[String, DefaultEdge](alpha, beta)
        if (inspector.isomorphismExists()){
            println("Results: Isomorphism Exists")
            println("------------------------------------------------------")
            val iterator = inspector.getMappings.asInstanceOf[java.util.Iterator[GraphMapping[String, DefaultEdge]]]
            var counter = 0
            while(iterator.hasNext || counter < 50) {
                val node = iterator.next;
                println("\t"+node.toString)
                counter+=1
            }
        } else {
            println("Results: Isomorphism does NOT Exist")
        }

        println("\n===========================================================")
        println("Performing The Transistive Closure Operations on Subgraph")

        println("Before: " + beta.edgeSet().size())
        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(beta)
        println("After " + beta.edgeSet().size())
        println("------------------------------------------------------------\nResults:")
        var counter = 50
        if (beta.edgeSet().size() < 50)
            counter = beta.edgeSet().size()

        val edges = beta.edgeSet().toArray

        for ( i <- 0 to counter) {
            println("\t"+edges(i))
        }


    }
}
