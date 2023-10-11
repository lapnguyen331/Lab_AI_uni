package k20;

public class Test {
    public static void main(String[] args) {
        Node nodeS = new Node("S"); Node nodeA = new Node("A"); Node nodeB = new Node("B"); Node nodeC = new Node("C"); Node nodeD = new Node("D"); Node nodeE = new Node("E"); Node nodeF = new Node("F"); Node nodeG = new Node("G"); Node nodeH = new Node("H");
        nodeS.addEdge(nodeA, 5);
        nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4);
        nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4);
        nodeB.addEdge(nodeG, 6);
        nodeC.addEdge(nodeF, 2);
        nodeD.addEdge(nodeH, 7);
        nodeE.addEdge(nodeG, 6);
        nodeF.addEdge(nodeG, 1);

        ISearchAlgo algo1 = new BFSALgo();
        ISearchAlgo algo2 = new DFSAlgo();

        Node result = algo1.executeRoot(nodeS, "G"); //result = sbg ,
        Node result1 = algo1.executeStartNode(nodeS,"A","G"); //result: a,e,f weight:10.0

//        Node result3 = algo2.executeRoot(nodeS, "G"); //result = sbg ,
        Node result4 = algo2.executeStartNode(nodeS,"A","G");
//        System.out.println("BFS Test");
//        System.out.println("tìm node từ s dến g"+ "dường đi"+NodeUtils.printPath(result)+" " +"weight: "+ result.getPathCost());
//        System.out.println("tìm node từ start A dến g"+ "dường đi"+NodeUtils.printPath(result1)+" " +"weight: "+ result1.getPathCost());
        System.out.println("DFS Test");
//        System.out.println("tìm node từ s dến g"+ "dường đi"+NodeUtils.printPath(result3)+" " +"weight: "+ result3.getPathCost());
        System.out.println("tìm node từ start dến g"+ "dường đi"+NodeUtils.printPath(result4)+" " +"weight: "+ result4.getPathCost());



    }
}
