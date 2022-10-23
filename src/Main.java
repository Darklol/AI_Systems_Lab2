public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        Search search = new Search();

        search.breathFirsSearch(util, "Мурманск","Одесса");

        search.depthFirstSearch(util, "Мурманск","Одесса");

        search.depthFirstSearch(util, "Мурманск","Одесса", 6);


//        search.printAllCities(util);
    }
}