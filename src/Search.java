import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Search {
    public void breathFirsSearch(Util util, String startCityName, String finishCityName){
        Graph graph = util.graphInit();
        int start = util.getVertexIndexByCityName(startCityName);
        int finish = util.getVertexIndexByCityName(finishCityName);
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> pathSearch = new ArrayList<>(27);
        for (int i = 0; i < 27; i++) pathSearch.add(null);

        queue.add(start);
        graph.getVertices().get(start).setWasVisited(true);
        while (!queue.isEmpty()){
            int current = queue.poll();
            for (int next : util.getNeighbours(current)){
                if (!graph.getVertices().get(next).isWasVisited()) {
                    graph.getVertices().get(next).setWasVisited(true);
                    queue.add(next);
                    pathSearch.set(next, current);
                }
            }
        }
        ArrayList<Integer> pathPrint = new ArrayList<>();
        for (Integer previous = finish; previous != null; previous = pathSearch.get(previous)){
            pathPrint.add(previous);
        }
        for (int i = pathPrint.size() - 1; i >= 0; i--){
            System.out.print(util.getVertexCityNameByIndex(pathPrint.get(i)));
            if (i != 0) System.out.print("-");
        }

    }
}
