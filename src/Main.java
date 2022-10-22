public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        util.coolTable();
        System.out.println(util.getNeighbours(util.getVerticeIndexByCityName("Москва")));
    }
}