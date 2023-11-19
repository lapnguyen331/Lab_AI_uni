package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyAlgo implements IInformedSearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() { //so sánh theo H
            @Override
            public int compare(Node o1, Node o2) {
                Double h1 = o1.getH();
                Double h2 = o2.getH();
                int result = h1.compareTo(h2);
                if(result == 0){
                    return o1.getLabel().compareTo(o2.getLabel());
                }
                else {
                    return result;
                }
            }
        }); // ---> cần sắp xếp lại danh sách theo path cost để pop lấy thèn đầu
        frontier.add(root);
        List<Node> expandList = new ArrayList<>();

        while(!frontier.isEmpty()){
            Node current  = frontier.poll(); //get first element
            if(current.getLabel().equals(goal)){
                return current;
            }
            else {
                expandList.add(current);
                List<Edge> childList = current.getChildren();
                for (Edge chil : childList) {
                    Node end = chil.getEnd();
                    if (!expandList.contains(end) && !frontier.contains(end)) {
                        frontier.add(end);
                        end.setParent(current);
                        end.setG(current.getG() + chil.getWeight());
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        return null;
    }
}
