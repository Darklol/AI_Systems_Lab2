import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Search {
    public void breathFirsSearch(Util util, String startCityName, String finishCityName){
        System.out.println("Поиск в ширину");
        Graph graph = util.graphInit();
        util.counterReset();
        int start = util.getVertexIndexByCityName(startCityName);
        int finish = util.getVertexIndexByCityName(finishCityName);
        ArrayList<Integer> pathSearch = new ArrayList<>();
        for (int i = 0; i < 27; i++) pathSearch.add(null);
        BFS(util, graph, start, finish, pathSearch);
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

    public void BFS(Util util, Graph graph, int start, int finish, ArrayList<Integer> pathSearch){
        Queue<Integer> queue = new ArrayDeque<>();

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
    }

    public void depthFirstSearch(Util util, String startCityName, String finishCityName){
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
        if (util.finishFlag(graph, finishNode)) return;
        int unvisitedNeighboursCount = util.getNeighbours(nodeIndex).size();
        for (int neighbour :  util.getNeighbours(nodeIndex)){
            if (!util.finishFlag(graph, finishNode)){
            DFS(util, graph, stack, neighbour, finishNode);
            unvisitedNeighboursCount--;
            } else return;
        }
        if ((unvisitedNeighboursCount == 0) && !util.finishFlag(graph, finishNode)) stack.pop();
    }

    public void depthLimitedSearch(Util util, String startCityName, String finishCityName, int limit){
        System.out.println("Поиск с ограничением глубины");
        util.counterReset();
        Graph graph = util.graphInit();
        int start = util.getVertexIndexByCityName(startCityName);
        int finish = util.getVertexIndexByCityName(finishCityName);
        Stack<Integer> stack = new Stack<>();
        DLS(util, graph, stack, start, finish, limit);
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

    public void DLS (Util util, Graph graph, Stack<Integer> stack, int nodeIndex, int finishNode, int limit){
        if (graph.getVertices().get(nodeIndex).isWasVisited()) return;
        util.counterIncrement();
        stack.push(nodeIndex);
        graph.getVertices().get(nodeIndex).setWasVisited(true);
        if (util.finishFlag(graph, finishNode)) return;
        if (stack.size() == limit) {
            stack.pop();
            return;
        }
        int unvisitedNeighboursCount = util.getNeighbours(nodeIndex).size();
        for (int neighbour :  util.getNeighbours(nodeIndex)){
            if (!util.finishFlag(graph, finishNode)){
                DLS(util, graph, stack, neighbour, finishNode, limit);
                unvisitedNeighboursCount--;
            } else return;
        }
        if ((unvisitedNeighboursCount == 0) && !util.finishFlag(graph, finishNode)) stack.pop();
    }


    public void iterativeDeependDepthFirstSearch(Util util, String startCityName, String finishCityName){
        System.out.println("Поиск с итеративным ограничением глубины");
        int limit = 0;
        util.counterReset();
        Graph graph = util.graphInit();
        int start = util.getVertexIndexByCityName(startCityName);
        int finish = util.getVertexIndexByCityName(finishCityName);
        Stack<Integer> stack = new Stack<>();
        while (!graph.getVertices().get(finish).isWasVisited()) {
            while (!stack.isEmpty()) stack.pop();
            graph.resetAllVerticesWasVisited();
            limit++;
            DLS(util, graph, stack, start, finish, limit);
        }
        int[] pathPrint = new int[stack.size()];
        for (int i = pathPrint.length - 1; i >= 0; i--) {
            pathPrint[i] = stack.pop();
        }
        for (int i = 0; i < pathPrint.length; i++) {
            System.out.print(util.getVertexCityNameByIndex(pathPrint[i]));
            if (i != pathPrint.length - 1) System.out.print("-");
        }
        util.finalCounter();
        System.out.println("Оптимальная глубина поиска: " + limit);
        System.out.println("\n\n");
    }


    public void bidirectionalSearch(Util util, String startCityName, String finishCityName){
        System.out.println("Двунаправленный поиск");
        Graph graph = util.graphInit();
        util.counterReset();
        int start = util.getVertexIndexByCityName(startCityName);
        int finish = util.getVertexIndexByCityName(finishCityName);
        ArrayList<Integer> pathSearchFromStart = new ArrayList<>();
        for (int i = 0; i < 27; i++) pathSearchFromStart.add(null);
        ArrayList<Integer> pathSearchFromFinish = new ArrayList<>();
        for (int i = 0; i < 27; i++) pathSearchFromFinish.add(null);
        BFS(util, graph, start, finish, pathSearchFromStart);
        graph.resetAllVerticesWasVisited();
        BFS(util, graph, finish, start, pathSearchFromFinish);
        util.counterReset();
        int currentFromStart = start;
        int currentFromFinish = finish;
        ArrayList<Integer> pathFromStart = new ArrayList<>();
        pathFromStart.add(currentFromStart);
        ArrayList<Integer> pathFromFinish = new ArrayList<>();
        pathFromFinish.add(currentFromFinish);
        while (true){
            util.counterIncrement();
            currentFromFinish = pathSearchFromStart.get(currentFromFinish);
            pathFromFinish.add(currentFromFinish);
            if (currentFromFinish == currentFromStart) {
                break;
            }
            else {
                currentFromStart = pathSearchFromFinish.get(currentFromStart);
                pathFromStart.add(currentFromStart);
            }
            if (currentFromFinish == currentFromStart) {
                break;
            }

        }
        if (pathFromStart.get(pathFromStart.size()-1) == pathFromFinish.get(pathFromFinish.size() - 1)) pathFromFinish.remove(pathFromFinish.size()-1);
        for (int i = 0; i < pathFromStart.size(); i++) System.out.print(util.getVertexCityNameByIndex(pathFromStart.get(i)) + "-");
        for (int i = pathFromFinish.size() - 1; i >=0 ; i--) {
            System.out.print(util.getVertexCityNameByIndex(pathFromFinish.get(i)));
            if (i != 0) System.out.print("-");
        }
        util.finalCounter();
        System.out.println("\n\n");

    }
    
    
    







}
