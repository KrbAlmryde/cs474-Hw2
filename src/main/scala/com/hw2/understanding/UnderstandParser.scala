package com.hw2.understanding

import com.scitools.understand._
import com.hw2.understanding.GraphTypes._
import org.jgrapht.graph.{DefaultEdge, SimpleDirectedGraph}


/**
  * Created by krbalmryde on 10/7/16.
  * This class represents an Understand parsing Object which generates a dependency and call graph.
  *
  * @param inputFile is a String representing the file path an existing Understand Database file.
  *
  */
class UnderstandParser(inputFile:String) {
    val dbName = inputFile.split("""/""").last
    // The jGrapht Dependency Graph object.
    var dependencyGraph: SimpleDirectedGraph[String, DefaultEdge] = new SimpleDirectedGraph[String, DefaultEdge](classOf[DefaultEdge])

    // The jGrapht Call Graph object.
    var callGraph: SimpleDirectedGraph[String, DefaultEdge] = new SimpleDirectedGraph[String, DefaultEdge](classOf[DefaultEdge])

    // The Understand Database Resource file.
    val dataBase:Database = Understand.open( inputFile )   // "/Users/krbalmryde/Dropbox/Class-projects/cs474/hw2/src/main/resources/HW2_SMSSync.udb"

    /**
      * Dynamic getter that returns the type of graph requested
      * @param graphTypes:Value
      * @return SimpleDirectedGraph[String, DefaultEdge]
      */
    def getGraph(graphTypes:Value): SimpleDirectedGraph[String, DefaultEdge] = {
        graphTypes match {
            case Call => this.callGraph
            case Deps => this.dependencyGraph
        }
    }


    def generateGraphs(graphTypes: Value): Unit ={
        // First, get a list of all classes, interfaces, or packages
        val types:String = "method ~unknown ~unresolved"
        // Generate the dependency graph using all methods defined internally
        val pFiles:Array[Entity] = dataBase.ents(types)

        graphTypes match {
            case Call => {
                println ("Generating Call Graph now for " + dbName + ". Please wait....")
                pFiles.foreach( fileEntity =>
                    graphEntity(callGraph, null, fileEntity, "call", " ")
                )
            }
            case Deps => {
                println ("Generating Dependency Graph now for" + dbName + ". Please wait....")
                pFiles.foreach( fileEntity =>
                    graphEntity(dependencyGraph, null, fileEntity, "callby", " ")
                )
            }
        }
    }

    /**
      * A recursive function, extracts the references found within the provided entity based on the kindstring
      * @param graph a SimpleDirectedGraph[String, DefaultEdge]) object
      * @param parent an Entity object, can be null. Used to ensure we link the generated edges correctly
      * @param entity an Entity object, The entity we are performing queries on
      * @param kindString a String object, contains the search pattern we want to extract
      * @param indent a String object, used for debugging purposes only in order better understand the relationship
      */
    //
    def graphEntity(graph:SimpleDirectedGraph[String, DefaultEdge], parent:Entity, entity:Entity, kindString:String, indent:String): Unit ={

        val typedRefs = entity.refs(kindString, "~unknown ~unresolved", true)
        val entityName = entity.name

        if (graph.containsVertex(entityName)) return
        else graph.addVertex(entityName)

        if (parent != null) {
            val parentName = parent.name
            graph.addEdge(parentName, entityName)
        }

//        println(indent+entity.kind.name+"++"+entityName)

        typedRefs.foreach( ref => {
            graphEntity(graph, entity, ref.ent, kindString, "  "+indent)
        })

    }

}
