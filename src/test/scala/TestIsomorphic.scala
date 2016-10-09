/**
  * Created by krbalmryde on 10/9/16.
  */

import org.scalatest.FunSuite
import com.hw2.understanding._
import com.hw2.understanding.GraphTypes._

class TestIsomorphic extends FunSuite{
    test("A graph should be Isomorphic if it is a subgraph of itsef") {
        val alpha = new UnderstandParser("/Users/krbalmryde/Dropbox/Class-projects/cs474/hw2/src/test/resources/BankTeller.udb");
        alpha.generateGraphs(Deps)

    }

}
