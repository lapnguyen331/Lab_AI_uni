public class Test {
    public static void main(String[] args) {
        GA_NQueenAlgo ga = new GA_NQueenAlgo();
        Node n = ga.execute();
        Node n1 = ga.reproduce(n, n);
        Node n2 = ga.getParentByRandomSelection();
        n2 = ga.reproduce(n, n);
//        n1.displayBoard();
        n.displayBoard();
        System.out.println("Best state: " + n);
        System.out.println("Best fitness: " + n.getH());
//        System.out.println("Parent: " + n2);
//        System.out.println("Child: " + n1);


    }
}
