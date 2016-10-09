/**
  * Created by krbalmryde on 10/9/16.
  */

import org.scalatest.FunSuite
import com.hw2.understanding._
import com.hw2.understanding.GraphTypes._
import com.scitools.understand.UnderstandException
import org.jgrapht.alg.TransitiveClosure
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector
import org.jgrapht.graph.DefaultEdge

class TestComponents extends FunSuite{

    val alpha = new UnderstandParser("/Users/krbalmryde/Dropbox/Class-projects/cs474/hw2/src/test/resources/BankTeller.udb");
        alpha.generateGraphs(Deps)
    val g1 = alpha.getGraph(Deps)

    test("A graph should be Isomorphic if it is a subgraph of itsef") {
        val inspector:VF2SubgraphIsomorphismInspector[String, DefaultEdge] = new VF2SubgraphIsomorphismInspector[String, DefaultEdge](g1, g1)
        assert(inspector.isomorphismExists)
    }

    test("We should get an Exception if Understand is Unable to open the DB") {
        assertThrows[UnderstandException] {
            new UnderstandParser("I Do not exist")
        }
    }

    test("Before Transitive Closure, we should have 24 elements") {
        assert(g1.edgeSet().size() == 24)
    }

    test("AFTER Transitive Closure, we should NOT have 24 elements") {
        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(g1)
        assert(g1.edgeSet().size() != 24)
    }

    test("In fact, there should be 35 elements") {
        assert(g1.edgeSet().size() == 35)
    }


    test("When examining the dependencies at the method level, we should expect ") {

    }
}
