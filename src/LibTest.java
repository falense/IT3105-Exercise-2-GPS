import java.awt.Dimension;
import java.util.Properties;

import javax.swing.JFrame;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


public class LibTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Graph<V, E> where V is the type of the vertices 
		 // and E is the type of the edges
		 Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
		 // Add some vertices. From above we defined these to be type Integer.
		 g.addVertex((Integer)1);
		 g.addVertex((Integer)2);
		 g.addVertex((Integer)3); 
		 g.addVertex((Integer)34); 
		 g.addVertex((Integer)5); 
		 // Add some edges. From above we defined these to be of type String
		 // Note that the default is for undirected edges.
		 g.addEdge("adjf", 1, 2); // Note that Java 1.5 auto-boxes primitives
		 g.addEdge("asdas", 2, 3); 
		 
		 
		 
		 Layout<Integer, String> layout = new CircleLayout(g);
		 layout.setSize(new Dimension(300,300)); // sets the initial size of the space
		 // The BasicVisualizationServer<V,E> is parameterized by the edge types
		 BasicVisualizationServer<Integer,String> vv = 
		 new BasicVisualizationServer<Integer,String>(layout);
		 vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
		
		 JFrame frame = new JFrame("Simple Graph View");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().add(vv); 
		 frame.pack();
		 frame.setVisible(true); 

	}

}
