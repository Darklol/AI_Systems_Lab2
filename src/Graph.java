import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;

@NoArgsConstructor
public class Graph {
    @Getter @Setter
    private int[][] connections;
    @Getter @Setter
    ArrayList<Vertex> vertices;

}
