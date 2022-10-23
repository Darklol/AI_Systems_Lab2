import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Search {
    public void breathFirsSearch(Util util, String startCityName, String finishCityName){
        System.out.println("Поиск в ширину\n");
        Graph graph = util.graphInit();
        util.counterReset();
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
                    if (!graph.getVertices().get(finish).isWasVisited()) util.counterIncrement();
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
        util.finalCounter();
        System.out.println("\n\n");

    }

    public void depthFirstSearch (Util util, String startCityName, String finishCityName){
        System.out.println("Поиск в глубину");
        util.counterReset();
        Graph graph = util.graphInit();
        int start = util.getVertexIndexByCityName(startCityName);
        int finish = util.getVertexIndexByCityName(finishCityName);
        Stack<Integer> stack = new Stack<>();
        DFS(util, graph, stack, start, finish);
        int[] pathPrint = new int[stack.size()];
        for (int i = pathPrint.length - 1; i >= 0; i--){
            pathPrint[i] = stack.pop();
        }
        for (int i = 0; i < pathPrint.length; i++){
            System.out.print(util.getVertexCityNameByIndex(pathPrint[i]));
            if (i != pathPrint.length - 1) System.out.print("-");
        }
        util.finalCounter();
        System.out.println("\n\n");
    }

    public void DFS (Util util, Graph graph, Stack<Integer> stack, int nodeIndex, int finishNode){
        if (graph.getVertices().get(nodeIndex).isWasVisited()) return;
        util.counterIncrement();
        stack.push(nodeIndex);
        graph.getVertices().get(nodeIndex).setWasVisited(true);
        if (finishFlag(graph, finishNode)) return;
        int unvisitedNeighboursCount = util.getNeighbours(nodeIndex).size();
        for (int neighbour :  util.getNeighbours(nodeIndex)){
            if (!finishFlag(graph, finishNode)){
            DFS(util, graph, stack, neighbour, finishNode);
            unvisitedNeighboursCount--;
            } else return;
        }
        if ((unvisitedNeighboursCount == 0) && !finishFlag(graph, finishNode)) stack.pop();
    }

    public void depthFirstSearch (Util util, String startCityName, String finishCityName, int limit){
        System.out.println("Поиск с ограничением глубины");
        util.counterReset();
        Graph graph = util.graphInit();
        int start = util.getVertexIndexByCityName(startCityName);
        int finish = util.getVertexIndexByCityName(finishCityName);
        Stack<Integer> stack = new Stack<>();
        DFS(util, graph, stack, start, finish, limit);
        if (stack.isEmpty()){
            System.out.println("Не удалось найти путь, требуется повысить ограничение глубины.");
        } else {
            int[] pathPrint = new int[stack.size()];
            for (int i = pathPrint.length - 1; i >= 0; i--) {
                pathPrint[i] = stack.pop();
            }
            for (int i = 0; i < pathPrint.length; i++) {
                System.out.print(util.getVertexCityNameByIndex(pathPrint[i]));
                if (i != pathPrint.length - 1) System.out.print("-");
            }

        }
        util.finalCounter();
        System.out.println("\n\n");
    }

    public void DFS (Util util, Graph graph, Stack<Integer> stack, int nodeIndex, int finishNode, int limit){
        if (graph.getVertices().get(nodeIndex).isWasVisited()) return;
        util.counterIncrement();
        stack.push(nodeIndex);
        graph.getVertices().get(nodeIndex).setWasVisited(true);
        if (finishFlag(graph, finishNode)) return;
        if (stack.size() == limit) {
            stack.pop();
            return;
        }
        int unvisitedNeighboursCount = util.getNeighbours(nodeIndex).size();
        for (int neighbour :  util.getNeighbours(nodeIndex)){
            if (!finishFlag(graph, finishNode)){
                DFS(util, graph, stack, neighbour, finishNode, limit);
                unvisitedNeighboursCount--;
            } else return;
        }
        if ((unvisitedNeighboursCount == 0) && !finishFlag(graph, finishNode)) stack.pop();
    }
    public boolean finishFlag (Graph graph, int finishNode){
        return graph.getVertices().get(finishNode).isWasVisited();
    }


    public void printAllCities(Util util){
        Graph graph = util.graphInit();
        for (Vertex vertex : graph.getVertices()){
            System.out.print(vertex.getCityName() + "-");
        }
        System.out.println();
    }


}
