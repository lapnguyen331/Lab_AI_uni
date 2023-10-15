package k20;

import java.util.*;

public class BFSALgo implements ISearchAlgo {

    @Override
    public Node executeRoot(Node root, String goal) {
        //NOTE: theo graph
//        Node goalNode = new Node(goal);
        Queue<Node> frontier = new LinkedList<>(); //hàng đợi
        frontier.add(root);
        List<Node> expandList = new ArrayList<>();
        while(!frontier.isEmpty()){ //lặp đến khi hàng đợi emty ->break
            Node chosen = frontier.poll(); //node đầu trong frontier
            if(chosen.getLabel().equals(goal)){
                return chosen;
            }
            else {
                expandList.add(chosen);
                List <Node> childList = chosen.getChildrenNodes();
                childList.sort(new Comparator<Node>() { //so sánh sắp xếp lại danh sách child theo a->b
                    @Override
                    public int compare(Node o1, Node o2) {

                        return o1.getLabel().compareTo(o2.getLabel());
                    }
                });
                for (Node child:childList) {
                    if(!frontier.contains(child) &&!expandList.contains(child)){ //kiểm tra xem child đã có trong frontier chưa và ko trong expand
                        frontier.add(child);
                        child.setParent(chosen);
                        child.setPathCost(chosen.getPathCost() + chosen.getEdgeWeigth(child));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Node executeRoot(Node root, String goal, int limit) {
        return null;
    }

    @Override
    public Node executeStartNode(Node root, String start, String goal) {
        //NOTE: theo graph
        Queue<Node> frontier = new LinkedList<>();
        List<Node> expandList = new ArrayList<>();
        boolean started = false ;
        frontier.add(root);
        while(!frontier.isEmpty()){
            Node current  = frontier.poll();//get node đầu
            if(current.getLabel().equals(start)){
                started =true;
                frontier.clear();
                expandList.clear();
            }
            if(started == true && current.getLabel().equals(goal)){
                return current;
            }
            else {
                expandList.add(current);
                List<Node> childList = current.getChildrenNodes();
                for (Node child :childList) {
                   if(!frontier.contains(child) && !expandList.contains(child)){
                       frontier.add(child);
                           if(started){
                               child.setParent(current);
                           child.setPathCost(current.getPathCost()+ current.getEdgeWeigth(child));
                       }

                   }

                }
            }

        }
        return null;
    }

    @Override
    public Node executeStartNode(Node root, String start, String goal, int limit) {
        return null;
    }
}
