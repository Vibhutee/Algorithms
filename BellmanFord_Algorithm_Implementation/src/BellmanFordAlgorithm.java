/*
 * @author vvala1
 * @date 03/25/21
 * @time 11:47 AM
 */

import java.util.List;

public class BellmanFordAlgorithm {
    private List<Edge> listOfEdges;
    private List<Vertex> listOfVertex;

    public BellmanFordAlgorithm(List<Edge> listOfEdges, List<Vertex> listOfVertex) {
        this.listOfEdges = listOfEdges;
        this.listOfVertex = listOfVertex;
    }

    public void shortestPath(Vertex sourceVertex, Vertex targetVertex){
        sourceVertex.setMinDistance(0);

        int length = this.listOfVertex.size();
        for(int i = 0; i < length - 1; i++){
            System.out.println("Iteration :"+ (i+1));
            for(Vertex vertex : this.listOfVertex){
                System.out.println("The distance from vertex 'U' to '"+vertex.getVertexName()+"' = \t"+vertex.getMinDistance());
            }
            System.out.println();
            for(Edge edge : this.listOfEdges){
                if(edge.getStartVertex().getMinDistance() == Double.MAX_VALUE)
                    continue;
                Vertex u = edge.getStartVertex();
                Vertex v = edge.getTargetVertex();

                double newDistance = u.getMinDistance() + edge.getWeight();

                if(newDistance < v.getMinDistance()){
                    v.setMinDistance(newDistance);
                    v.setPreviousVertex(u);
                }
            }
        }

        int vertexIndex = 0;
        int[] hopCount = {0,0,0,0,0,0};
        for(Vertex vertex : this.listOfVertex){
            Vertex previousVertex = vertex.getPreviousVertex();
            while(previousVertex != null) {
                hopCount[vertexIndex]++;
                previousVertex = previousVertex.getPreviousVertex();
            }
            vertexIndex++;
        }

        System.out.println("Hopcount with respect to u:\n");
        System.out.println("****************");
        for(int i = 0; i < 6; i++){
            System.out.println("* Hopcount - " + hopCount[i]+ " *");
        }
        System.out.println("****************");
    }
}
