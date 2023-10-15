package k20;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {
    @Override
    public Node executeRoot(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator()); // ---> cần sắp xếp lại danh sách theo path cost để pop lấy thèn đầu
        frontier.add(root);
        List<Node> expandList = new ArrayList<>();

        while(!frontier.isEmpty()){
            Node current  = frontier.poll(); //get first element
            if(current.getLabel().equals(goal)){
                return current;
            }
            else {
                expandList.add(current);
                List<Edge> childList = current.getEdgeChildren();
                expandList.add(current);
                for (Edge childedge : childList) {
                    Node end = childedge.getEnd();
                    double newpathCost = current.getPathCost() + childedge.getWeight();
                    if (!expandList.contains(end) && !frontier.contains(end)) {
                        frontier.add(end);
                        end.setParent(current);
                        end.setPathCost(current.getPathCost() + childedge.getWeight());
                    }
                    else if(end.getPathCost() > newpathCost){
                        end.setPathCost(newpathCost);
                        end.setParent(childedge.getBegin());
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
    public Node executeStartNode(Node root, String start, String goal,int limit){
        return null;
    }

    @Override
    public Node executeStartNode(Node root, String start, String goal) {
        PriorityQueue<Node> frontier  = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });
        frontier.add(root);
        List<Node> expandlist = new ArrayList<>();
        boolean started =false;
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(start)){
                started = true;
                frontier.clear();
                expandlist.clear();
            }
            if(started == true && current.getLabel().equals(goal)){
                return current;
            }
            else {
                expandlist.add(current);
                List<Edge> children = current.getEdgeChildren();
                for (Edge child :children) {
                    Node end = child.getEnd();
                    double newpathcost = current.getPathCost() + child.getWeight();
                    if(!frontier.contains(end) && !expandlist.contains(end)) {
                        frontier.add(end);
                        if(started){
                            end.setParent(current);
                            end.setPathCost(current.getPathCost() +child.getWeight());
                        }
                        if(!started) {
                            end.setParent(null);
                            end.setPathCost(current.getPathCost() +child.getWeight());
                         }

                   if ( started && end.getPathCost() > newpathcost) {
                            end.setPathCost(newpathcost);
                            end.setParent(child.getBegin());
                        }
                    }
                }
            }
        }
        return null;
    }
}
