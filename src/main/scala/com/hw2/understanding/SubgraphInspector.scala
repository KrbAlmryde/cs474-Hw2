package com.hw2.understanding

import com.scitools.understand._
import org.jgrapht.GraphMapping
import org.jgrapht.alg.TransitiveClosure
import org.jgrapht.graph.{DefaultEdge, SimpleDirectedGraph, SimpleGraph}
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector

/**
  * Created by krbalmryde on 10/9/16.
  */
class SubgraphInspector(alpha: SimpleDirectedGraph[String, DefaultEdge], beta:SimpleDirectedGraph[String, DefaultEdge]) {
//    new VF2SubgraphIsomorphismInspector( inputAppV1.getGraph(Call), inputAppV2.getGraph(Call) )


    def doMappings(): Unit = {

        val inspector:VF2SubgraphIsomorphismInspector[String, DefaultEdge] = new VF2SubgraphIsomorphismInspector(alpha, beta)
        if (inspector.isomorphismExists())
            println("ITS TRUE! isomorphism Exists!!")
        else
            println("None exists, damn")
        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(alpha)

        val iterator = inspector.getMappings
        while(iterator.hasNext) {
            val value = iterator.next
            println(value.toString)

        }
    }


}
