/*
 * @author vvala1
 * @date 03/25/21
 * @time 12:29 PM
 */

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        List<Vertex> listOfVertex = new ArrayList<>();
        listOfVertex.add(new Vertex("U"));
        listOfVertex.add(new Vertex("V"));
        listOfVertex.add(new Vertex("W"));
        listOfVertex.add(new Vertex("X"));
        listOfVertex.add(new Vertex("Y"));
        listOfVertex.add(new Vertex("Z"));

        List<Edge> listOfEdges = new ArrayList<>();
        // Vertex U to V with weight of 3
        listOfEdges.add(new Edge(listOfVertex.get(0),listOfVertex.get(1),3));
        // Vertex U to X with weight of 1
        listOfEdges.add(new Edge(listOfVertex.get(0),listOfVertex.get(3),1));
        // Vertex U to W with weight of 7
        listOfEdges.add(new Edge(listOfVertex.get(0),listOfVertex.get(2),7));

        // Vertex V to U with weight of 3
        listOfEdges.add(new Edge(listOfVertex.get(1),listOfVertex.get(0),3));
        // Vertex V to W with weight of 1
        listOfEdges.add(new Edge(listOfVertex.get(1),listOfVertex.get(2),1));
        // Vertex V to X with weight of 1
        listOfEdges.add(new Edge(listOfVertex.get(1),listOfVertex.get(3),1));

        // Vertex W to U with weight of 7
        listOfEdges.add(new Edge(listOfVertex.get(2),listOfVertex.get(0),7));
        // Vertex W to V with weight of 1
        listOfEdges.add(new Edge(listOfVertex.get(2),listOfVertex.get(1),1));
        // Vertex W to X with weight of 4
        listOfEdges.add(new Edge(listOfVertex.get(2),listOfVertex.get(3),4));
        // Vertex W to Y with weight of 5
        listOfEdges.add(new Edge(listOfVertex.get(2),listOfVertex.get(4),5));
        // Vertex W to Z with weight of 6
        listOfEdges.add(new Edge(listOfVertex.get(2),listOfVertex.get(5),6));

        // Vertex X to U with weight of 1
        listOfEdges.add(new Edge(listOfVertex.get(3),listOfVertex.get(0),1));
        // Vertex X to V with weight of 1
        listOfEdges.add(new Edge(listOfVertex.get(3),listOfVertex.get(1),1));
        // Vertex X to W with weight of 4
        listOfEdges.add(new Edge(listOfVertex.get(3),listOfVertex.get(2),4));
        // Vertex X to Y with weight of 2
        listOfEdges.add(new Edge(listOfVertex.get(3),listOfVertex.get(4),2));

        // Vertex Y to W with weight of 5
        listOfEdges.add(new Edge(listOfVertex.get(4),listOfVertex.get(2),5));
        // Vertex Y to X with weight of 2
        listOfEdges.add(new Edge(listOfVertex.get(4),listOfVertex.get(3),2));
        // Vertex Y to Z with weight of 3
        listOfEdges.add(new Edge(listOfVertex.get(4),listOfVertex.get(5),3));

        BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm(listOfEdges,listOfVertex);
        bellmanFordAlgorithm.shortestPath(listOfVertex.get(0), listOfVertex.get(5));

    }
}
