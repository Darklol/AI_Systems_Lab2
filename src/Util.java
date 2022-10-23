import java.util.ArrayList;

public class Util {

    private int counter = 0;
    public ArrayList<Vertex> verticesInit() {



        ArrayList<Vertex> vertices = new ArrayList<>();
        String data = "0 Таллинн 1493\n" +
                "1 Вильнюс 1235\n" +
                "2 Витебск 969\n" +
                "3 Воронеж 842\n" +
                "4 Волгоград 1063\n" +
                "5 Калининград 1165\n" +
                "6 Каунас 1051\n" +
                "7 Киев 442\n" +
                "8 Житомир 446\n" +
                "9 Брест 804\n" +
                "10 Ниж.Новгород 1422\n" +
                "11 Даугавпилс 1085\n" +
                "12 Донецк 560\n" +
                "13 Кишинев 157\n" +
                "14 С.Петербург 1497\n" +
                "15 Москва 1137\n" +
                "16 Казань 1640\n" +
                "17 Минск 854\n" +
                "18 Рига 1252\n" +
                "19 Мурманск 2503\n" +
                "20 Орел 817\n" +
                "21 Одесса 0\n" +
                "22 Харьков 564\n" +
                "23 Симферополь 312\n" +
                "24 Ярославль 1386\n" +
                "25 Уфа 1989\n" +
                "26 Самара 1580";
        for (String line : data.split("\n")){
            int index = Integer.parseInt(line.split(" ")[0]);
            String cityName = line.split(" ")[1];
            int heuristicFunction = Integer.parseInt(line.split(" ")[2]);
            vertices.add(new Vertex(index, cityName, heuristicFunction));
        }
        return vertices;
    }

    public Graph graphInit(){
        Graph graph = new Graph();
        ArrayList<Vertex> vertices = verticesInit();
        int[][] connections = new int[27][27];
        String data = "Вильнюс Брест 531\n" +
                "Витебск Брест 638\n" +
                "Витебск Вильнюс 360\n" +
                "Воронеж Витебск 869\n" +
                "Воронеж Волгоград 581\n" +
                "Волгоград Витебск 1455\n" +
                "Витебск Ниж.Новгород 911\n" +
                "Вильнюс Даугавпилс 211\n" +
                "Калининград Брест 699\n" +
                "Калининград Вильнюс 333\n" +
                "Каунас Вильнюс 102\n" +
                "Киев Вильнюс 734\n" +
                "Киев Житомир 131\n" +
                "Житомир Донецк 863\n" +
                "Житомир Волгоград 1493\n" +
                "Кишинев Киев 467\n" +
                "Кишинев Донецк 812\n" +
                "С.Петербург Витебск 602\n" +
                "С.Петербург Калининград 739\n" +
                "С.Петербург Рига 641\n" +
                "Москва Казань 815\n" +
                "Москва Ниж.Новгород 411\n" +
                "Москва Минск 690\n" +
                "Москва Донецк 1084\n" +
                "Москва С.Петербург 664\n" +
                "Мурманск С.Петербург 1412\n" +
                "Мурманск Минск 2238\n" +
                "Орел Витебск 522\n" +
                "Орел Донецк 709\n" +
                "Орел Москва 368\n" +
                "Одесса Киев 487\n" +
                "Рига Каунас 267\n" +
                "Таллинн Рига 308\n" +
                "Харьков Киев 471\n" +
                "Харьков Симферополь 639\n" +
                "Ярославль Воронеж 739\n" +
                "Ярославль Минск 940\n" +
                "Уфа Казань 525\n" +
                "Уфа Самара 461";
        for (String line : data.split("\n")){
            int index1 = getVertexIndexByCityName(line.split(" ")[0]);
            int index2 = getVertexIndexByCityName(line.split(" ")[1]);
            int value = Integer.parseInt(line.split(" ")[2]);

            connections[index1][index2] = value;
            connections[index2][index1] = value;
        }
        graph.setVertices(vertices);
        graph.setConnections(connections);
        return graph;
    }

    public int getVertexIndexByCityName(String cityName){
        ArrayList<Vertex> vertices = verticesInit();
        for (int i = 0; i < 27; i++){
            if (vertices.get(i).getCityName().equals(cityName)) return i;
        }
        return -1;
    }

    public String getVertexCityNameByIndex(int index){
        ArrayList<Vertex> vertices = verticesInit();
        for (int i = 0; i < 27; i++){
            if (vertices.get(i).getIndex() == index) return vertices.get(i).getCityName();
        }
        return "Invalid Result";
    }

    public void coolTable() {
        System.out.printf("%13s", " ");
        for (int i = 0; i < graphInit().getConnections().length; i++) {
            System.out.printf("%13s", verticesInit().get(i).getCityName());
        }
        System.out.println();
        for (int i = 0; i < graphInit().getConnections().length; i++) {
            System.out.printf("%13s", verticesInit().get(i).getCityName());
            for (int j = 0; j < graphInit().getConnections()[i].length; j++) {
                if (i == j) {
                    System.out.printf("%13s", "X");
                    continue;
                }
                System.out.printf("%13s", graphInit().getConnections()[i][j]);
            }
            System.out.println();
        }
    }

    public ArrayList<Integer> getNeighbours(int vertex) {
        Graph graph = graphInit();
        ArrayList<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < graph.getConnections().length; i++) {
            if (graph.getConnections()[vertex][i] != 0) neighbours.add(i);
        }
        return neighbours;
    }

    public void printAllCities(){
        Graph graph = graphInit();
        for (Vertex vertex : graph.getVertices()){
            System.out.print(vertex.getCityName() + "-");
        }
        System.out.println();
    }

    public void counterReset(){
        this.counter = 0;
    }

    public void counterIncrement(){
        this.counter++;
    }

    public void finalCounter(){
        System.out.println("\nКоличество шагов затраченное на поиск: " + counter);
    }

    public int getCounter(){return counter;}

    public boolean finishFlag (Graph graph, int finishNode){
        return graph.getVertices().get(finishNode).isWasVisited();
    }

}
