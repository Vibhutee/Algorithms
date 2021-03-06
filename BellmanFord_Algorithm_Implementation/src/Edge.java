/*
 * @author vvala1
 * @date 03/25/21
 * @time 11:27 AM
 */
public class Edge {

    private Vertex startVertex;
    private Vertex targetVertex;
    private double weight;

    public Edge(Vertex startVertex, Vertex targetVertex, double weight) {
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}