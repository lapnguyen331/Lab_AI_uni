package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class AStar implements IInformedSearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() { //so sánh theo H
            @Override
            public int compare(Node o1, Node o2) {
                Double h1 =  o1.getG();
                Double h2 =  o2.getG();
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
                        current.setG(current.getG() + chil.getWeight());
                        end.setParent(current);
                        end.setG(current.getG() + chil.getWeight());
                        frontier.add(end);

                    }
                    if (end.getG() > (chil.getBegin().getG()+chil.getWeight())) {
                                end.setG(chil.getBegin().getG() + chil.getWeight());
                                end.setParent(chil.getBegin());
                            }
                    System.out.println(current +" "+frontier + end.getG());

                }
                }
            }
        return null;
    }
//    @Override
//    public Node executeH(Node root, String goal, Node e) {
//        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() { //so sánh theo H
//            @Override
//            public int compare(Node o1, Node o2) {
//                Double h1 = o1.getG();
//                Double h2 = o2.getG();
//                int result = h2.compareTo(h1);
//                if(result == 0){
//                    return o1.getLabel().compareTo(o2.getLabel());
//                }
//                else {
//                    return result;
//                }
//            }
//        }); // ---> cần sắp xếp lại danh sách theo path cost để pop lấy thèn đầu
//        frontier.add(root);
//        List<Node> expandList = new ArrayList<>();
//        boolean flag = false;
//
//        while(!frontier.isEmpty()){
//            Node current  = frontier.poll(); //get first element
//            if(current.getLabel().equals(goal) && flag){
//                return current;
//            }
//            else {
//                expandList.add(current);
//                List<Edge> childList = current.getChildren();
//                for (Edge chil : childList) {
//                    Node end = chil.getEnd();
//                    if (!expandList.contains(end) && !frontier.contains(end)) {
//                        frontier.add(end);
//                        current.setG(current.getG() + chil.getWeight());
//                        end.setParent(current);
//                    }
//                    else if(frontier.contains(end)) {
//                        if (end.getG() > (current.getG()+chil.getWeight())) {
//                            end.setG(chil.getBegin().getG() + chil.getWeight());
//                            end.setParent(chil.getBegin());
//                        }
////                        end.setH(current.getH() + chil.getWeight());
//                    }
//                }
//            }
//        }
//        return null;
//    }
    public boolean isAdmissibleH(Node tree, String goal){

        return false;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        return null;
    }
}
