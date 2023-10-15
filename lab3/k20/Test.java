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

//        ISearchAlgo algo1 = new BFSALgo();
//        ISearchAlgo algo2 = new DFSAlgo();
//        Node result = algo1.executeRoot(nodeS, "G"); //result = sbg ,
//        System.out.println("ex1-tìm node từ s dến g"+ "dường đi"+NodeUtils.printPath(result)+" " +"weight: "+ result.getPathCost());
//        System.out.println("BFS Test");
//        Node result1 = algo1.executeStartNode(nodeS,"A","G"); //result: a,e,f weight:10.0
//        System.out.println("ex1-tìm node từ start A dến g"+ "dường đi"+NodeUtils.printPath(result1)+" " +"weight: "+ result1.getPathCost());
//
//        System.out.println("DFS Test");
//        Node result3 = algo2.executeRoot(nodeS, "G"); //result = sbg ,
//        System.out.println("ex2-tìm node từ s dến g"+ "dường đi"+NodeUtils.printPath(result3)+" " +"weight: "+ result3.getPathCost());
//        Node result4 = algo2.executeStartNode(nodeS,"A","G");
//        System.out.println("ex2-tìm node từ start dến g"+ "dường đi"+NodeUtils.printPath(result4)+" " +"weight: "+ result4.getPathCost());

        System.out.println("Uniform cost search");
      ISearchAlgo algo3 = new UniformCostSearchAlgo();
      Node result5 = algo3.executeRoot(nodeS,"G");
      System.out.println("ex3-tìm từ root g đến goal + dường đi" + NodeUtils.printPath(result5) + "weight " +result5.getPathCost());
      Node result6 = algo3.executeStartNode(nodeS,"A","G");
      System.out.println("ex4-tìm từ start đến goal + dường đi" +NodeUtils.printPath(result6)+ "weight "+result6.getPathCost());

        System.out.println("Depth limit search");
        ISearchAlgo algo4 = new Depthlimitedsearch();
//        Node result7 = algo4.executeRoot(nodeS,"G",3); //error --->can not find the path with limit
//        System.out.println("ex5-tìm từ root g đến goal + dường đi" + NodeUtils.printPath(result7) + "weight " +result7.getPathCost()+"depth: " +result7.getDepth());
        Node result8 = algo4.executeRoot(nodeS,"G",4); //can find the path with limit
        System.out.println("ex5-tìm từ start đến goal + dường đi" +NodeUtils.printPath(result8 )+ "weight "+result8.getPathCost());

        Node result9 = algo4.executeStartNode(nodeS,"A","G",5);
        System.out.println("ex5-tìm từ start đến goal + dường đi" +NodeUtils.printPath(result9 )+ "weight "+result9.getPathCost());
    }
}
