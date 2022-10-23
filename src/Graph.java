import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@NoArgsConstructor
public class Graph {
    @Getter @Setter
    private int[][] connections;
    @Getter @Setter
    private ArrayList<Vertex> vertices;

    public void resetAllVerticesWasVisited(){
        for (Vertex vertex : vertices){
            vertex.setWasVisited(false);
        }
    }

}
