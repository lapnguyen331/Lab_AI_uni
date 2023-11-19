import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
    public static final int POP_SIZE = 100;//Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000000;
    List<Node> population = new ArrayList<Node>();
    Random rd = new Random();


    // initialize the individuals of the population
    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }

    //Genetic-Algorithm
    public Node execute() {
        initPopulation();
        int iteration = 0;
        while (iteration < MAX_ITERATIONS) {
            List<Node> newPopulation = new ArrayList<Node>();
            for (int i = 0; i < POP_SIZE; i++) {
                Node x = getParentByRandomSelection();
                Node y = getParentByRandomSelection();
                Node child = reproduce(x, y);
                if (Math.random() < MUTATION_RATE) {
                    mutate(child);
                }
                newPopulation.add(child);
            }
            population = newPopulation;
            iteration++;
        }
        Collections.sort(population);
        return population.get(0) ;
    }


    // Mutate an individual by selecting a random Queen and
    // move it to a random row.
    public void mutate(Node node) {
        int index = rd.nextInt(Node.N);
        int row = rd.nextInt(Node.N);
        node.setRow(index, row);
    }

    // Crossover x and y to reproduce a child
    public Node reproduce(Node x, Node y) {
        int c = rd.nextInt(Node.N);
        Node re = new Node();
        re.generateBoard();
        for (int i = 0; i < c; i++) {
            re.setRow(i, x.getRow(i));
        }
        for (int i = c; i < Node.N; i++) {
            re.setRow(i, y.getRow(i));
        }
        return re;
    }

    // Select K individuals from the population at random and
    // select the best out of these to become a parent.
    public Node getParentByTournamentSelection() {
        Random rc = new Random();
        for (int i = 0; i < Node.N; i++) {
            rc.nextInt(Node.N);

        }



        return null;
    }

    //Select a random parent from the population
    public Node getParentByRandomSelection() {
        rd = new Random();
        int index = rd.nextInt(POP_SIZE);
        return population.get(index);
    }
}
