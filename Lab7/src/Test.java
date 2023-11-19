public class Test {
    public static void main(String[] args) {
        System.out.println("hehe");
        Node em1 = new Node();
        GA_NQueenAlgo em = new GA_NQueenAlgo();
        em.execute();

        em1.displayBoard();
        System.out.println(em1.getH());


    }
}
