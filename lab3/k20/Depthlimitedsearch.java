package k20;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Depthlimitedsearch implements ISearchAlgo{
    @Override
    public Node executeRoot(Node root, String goal) {
        return null;
    }

    @Override
    public Node executeRoot(Node root, String goal,int limit) {
        Stack<Node> frontier = new Stack<>();
        frontier.add(root);
        List<Node> expandList = new ArrayList<>();
        while(!frontier.isEmpty()){
            Node current  = frontier.pop();
            System.out.println(current.getDepth());
            if(current.getDepth() >= limit){
                return null;
            }
            if(current.getLabel().equals(goal)){
                return current;
            }

            else {
                expandList.add(current);
                List<Node> children = current.getChildrenNodes();
                for (Node child: children) {
                    if(!frontier.contains(child) && !expandList.contains(child)){
                        frontier.add(child);
                        child.setParent(current);
                        child.setPathCost(current.getPathCost() + current.getEdgeWeigth(child));
                        child.setDepth(current.getDepth()+1);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Node executeStartNode(Node root, String start, String goal) {
        return null;
    }
    @Override
    public Node executeStartNode(Node root, String start, String goal,int limit) {
        Stack<Node> frontier = new Stack<>();
        frontier.add(root);
        boolean started = false;
        List<Node> expandList = new ArrayList<>();
        while(!frontier.isEmpty()){
            Node current  = frontier.pop();
            System.out.println(current.getDepth());
            if(current.getDepth() >= limit){
                return null;
            }
            if(current.getLabel().equals(start)){
                started= true;
                frontier.clear();
                expandList.clear();
            }
            if(started && current.getDepth() <limit && current.getLabel().equals(goal)){
                return current;
            }

            else {
                expandList.add(current);
                List<Node> children = current.getChildrenNodes();
                for (Node child: children) {
                    if(!frontier.contains(child) && !expandList.contains(child)){
                        frontier.add(child);
                        if(started){
                            child.setParent(current);
                            child.setPathCost(current.getPathCost() + current.getEdgeWeigth(child));
                            child.setDepth(current.getDepth()+1);
                        }
                      else {
                            child.setParent(null);
                            child.setPathCost(current.getPathCost() + current.getEdgeWeigth(child));
                            child.setDepth(current.getDepth()+1);
                        }
                    }
                }
            }
        }
        return null;
    }
}
