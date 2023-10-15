package k20;

import java.util.Comparator;

public  class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getLabel().compareTo(o2.getLabel()) ;
    }
//    Node a;
//    Node b;
//    int pathCost =0;
//    public NodeComparator(){
//
//    }


}
