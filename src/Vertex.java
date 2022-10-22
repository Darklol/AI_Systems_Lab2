import lombok.Getter;
import lombok.Setter;

public class Vertex {
    @Getter @Setter
    private String cityName;
    @Getter @Setter
    private int index;
    @Getter @Setter
    private boolean wasVisited;
    @Getter @Setter
    private double optimalPath;
    @Getter @Setter
    private double euristicFunction;

    Vertex(int index, String cityName, double euristicFunction){
        this.cityName = cityName;
        this.index = index;
        this.optimalPath = euristicFunction;
        wasVisited = false;
    }
}
