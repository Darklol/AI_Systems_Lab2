import lombok.Getter;
import lombok.Setter;

public class Vertex implements Comparable<Vertex>{
    @Getter @Setter
    private String cityName;
    @Getter @Setter
    private int index;
    @Getter @Setter
    private boolean wasVisited;
    @Getter @Setter
    private int optimalPath;
    @Getter @Setter
    private int heuristicFunction;

    @Getter @Setter private int predecessor;

    Vertex(int index, String cityName, int heuristicFunction){
        this.cityName = cityName;
        this.index = index;
        this.heuristicFunction = heuristicFunction;
        this.optimalPath = heuristicFunction;
        wasVisited = false;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.getOptimalPath() - o.getOptimalPath();
    }
}
