import java.util.ArrayDeque;
import java.util.Queue;

public class Search {
    public void breathFirsSearch(Util util, String startCityName, String finishCityName){
        Graph graph = util.graphInit();
        int[][] connections = graph.getConnections();
        int start = util.getVerticeIndexByCityName(startCityName);
        int finish = util.getVerticeIndexByCityName(finishCityName);
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(start);
        while (!queue.isEmpty()){
            queue.poll();
            for (Vertex vertex : u)
        }

    }
}
