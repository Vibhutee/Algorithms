/*
 * @author vvala1
 * @date 03/25/21
 * @time 11:27 AM
 */

import java.util.List;

public class Vertex {

    private String vertexName;
    private boolean visited;
    private List<Edge> adjacentList;
    private double minDistance = Double.MAX_VALUE;
    private Vertex previousVertex;

    public Vertex(String vertexName) {
        this.vertexName = vertexName;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getVertexName() {
        return vertexName;
    }

    public List<Edge> getAdjacentList() {
        return adjacentList;
    }

    public void setAdjacentList(List<Edge> adjacentList) {
        this.adjacentList = adjacentList;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }
}
