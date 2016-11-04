import java.util.*;

public class BST<T extends Comparable<T>> {
    private class Node<T extends Comparable<T>> {
        T item;
        Node<T> right;
        Node<T> left;
        int height;
        int balanceFactor;

        public Node(T it){
            item = it;
            right = null;
            left = null;
            height = 0;
            balanceFactor = 0;
        }

        public String toString(){
            return "(" + item.toString() + ", H" + height + ", B" + balanceFactor + ")";
        }
    }

    Node<T> root;
    boolean balancing;

    public BST(boolean selfbalancing){
        root = null;
        balancing = selfbalancing;
    }

    private Node<T> add(T it, Node<T> rt){
        if(rt == null){
            rt = new Node<T>(it);
        }
        else {
            if(rt.item.compareTo(it)<0){
                rt.right = add(it,rt.right);
                update(rt);
                if(balancing) rt = rebalance(rt);
            }
            else {
                rt.left = add(it,rt.left);
                update(rt);
                if(balancing) rt = rebalance(rt);
            }
        }
        return rt;
    }

    public void add(T it){
//		System.out.println("Adding " + it);
        root = add(it,root);
    }

    public void add(List<T> lst){
        for (int i = 0; i < lst.size(); i++){
//			System.out.println(this);
            add(lst.get(i));
        }
    }

    public void randomAdd(List<T> lst){
        add(randomizer(lst));
    }

    private T search(T it, Node<T> rt){
        if (rt == null) return null;
        if(rt.item.equals(it)){
            return rt.item;
        }
        if (rt.item.compareTo(it)<0){
            return search(it,rt.right);
        }
        return search(it,rt.left);
    }

    public T search(T it){
        return search(it,root);
    }

    public String toString (){
        return inorder();
    }

    private T findLeast(Node<T> rt){
        if(rt.left != null) return findLeast(rt.left);
        return rt.item;
    }

    private Node<T> removeLeast(Node<T> rt){
        if(rt.left != null) {
            rt.left = removeLeast(rt.left);
            update(rt);
            if(balancing) rt = rebalance(rt);
            return rt;
        }
        return rt.right;
    }
    private Node<T> remove(T it, Node<T> rt){
        if (rt == null) return null;
        if(rt.item.equals(it)){
            // we found the node we are looking for ...
            if(rt.left == null){
                // this case handles leaf nodes too
                return rt.right;
            } else if (rt.right == null) {
                return rt.left;
            } else {
                // this node has two children so we need to promote
                // a node to be the new root of this subtree
                rt.item = findLeast(rt.right);
                rt.right = removeLeast(rt.right);
            }
        }
        if (rt.item.compareTo(it)<0){
            rt.right = remove(it,rt.right);
            update(rt);
            if(balancing) rt = rebalance(rt);
        } else {
            rt.left = remove(it,rt.left);
            update(rt);
            if(balancing) rt = rebalance(rt);
        }
        return rt;
    }

    public void remove(T it){
        root = remove(it,root);
    }

    private void update(Node<T> rt){
        rt.height = Math.max(rt.left != null ? rt.left.height : -1, rt.right != null ? rt.right.height : -1)+1;
        rt.balanceFactor = (rt.left != null ? rt.left.height : -1) - (rt.right != null ? rt.right.height : -1);
    }

    private Node<T> rotateRight(Node<T> rt){
        Node<T> temp = rt.left;
        rt.left = temp.right;
        update(rt);
        temp.right = rt;
        rt = temp;
        update(rt);
        return rt;
    }

    private Node<T> rotateLeft(Node<T> rt){
        Node<T> temp = rt.right;
        rt.right = temp.left;
        update(rt);
        temp.left = rt;
        rt = temp;
        update(rt);
        return rt;
    }

    private Node<T> rebalance(Node<T> rt){
        if(rt.balanceFactor < -1){
            // right sub tree is over balanced
            if(rt.right.balanceFactor > 0){
                // right-left case
                rt.right = rotateRight(rt.right);
            }
            // right-right case
            rt = rotateLeft(rt);
        }
        if(rt.balanceFactor > 1){
            // left sub tree is over balanced
            if(rt.left.balanceFactor < 0){
                // left-right case
                rt.left = rotateLeft(rt.left);
            }
            // left-left case
            rt = rotateRight(rt);
        }
        return rt;
    }


    // traversals from previous tree implementations
    private String preorder(Node<T> node){

        if(node == null) return "".toString();
        else {
            String left, right;
            left = preorder(node.left);
            right = preorder(node.right);
            return node + ", " + left + right;
        }
    }

    public String preorder() {
        String str = preorder(root);
        int end = Math.max(0, str.length()-2);
        return "[" + str.substring(0, end) + "]";
    }

    private String inorder(Node<T> node){

        if(node == null) return "".toString();
        else {
            String left, right;
            left = inorder(node.left);
            right = inorder(node.right);
            return left + node + ", " + right;
        }
    }

    public String inorder() {
        String str = inorder(root);
        int end = Math.max(0, str.length()-2);
        return "[" + str.substring(0, end) + "]";
    }

    private String postorder(Node<T> node){

        if(node == null) return "".toString();
        else {
            String left, right;
            left = postorder(node.left);
            right = postorder(node.right);
            return left + right + node + ", ";
        }
    }

    public String postorder() {
        String str = postorder(root);
        int end = Math.max(0, str.length()-2);
        return "[" + str.substring(0, end) + "]";
    }

    private List<T> randomizer(List<T> list){
        // create a new list to return out of the old list
        List<T> random = new ArrayList<T>();
        Random rand = new Random();

        // while there are items to be randomized
        while(list.size() > 0){
            int i = Math.abs(rand.nextInt()%list.size());

            random.add(list.remove(i));
        }
        return random;
    }
}
