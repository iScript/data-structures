// 二分搜索树
// 值需要是可比较的
public class BST<E extends Comparable<E> >{
    // 节点
    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root,e);
    }

    // 以Node为根的二分搜索树中插入元素E
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node , E e){
        if(node == null){
            size++;
            return new Node(e);
        }

        // 递归
        // 通过比较向左边或右边插入值，只要为null就插入，若不为null，则继续递归向左或右插入。
        if(e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e) > 0){
            node.right = add(node.right,e);
        }
        return node ;
    }


    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node, E e){
        if(node == null)
            return false;

        if(e.compareTo(node.e) == 0){
            return true;
        }else if(e.compareTo(node.e) < 0 ){
            return contains(node.left,e);
        }else{
            return contains(node.right,e);
        }
    }

    // 前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null) return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历
    public void midOrder(){
        midOrder(root);
    }

    private void midOrder(Node node){
        if(node == null) return;
        midOrder(node.left);
        System.out.println(node.e);
        midOrder(node.right);
    }

    // 后序遍历
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }



//    // 以Node为根的二分搜索树中插入元素E
//    private void add(Node node , E e){
//        if(e.equals(node.e))
//            return;
//        else if(e.compareTo(node.e) < 0 && node.left == null){
//            node.left = new Node(e);
//            size++;
//            return ;
//        }else if(e.compareTo(node.e) > 0 && node.right == null){
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//        // 递归
//        if(e.compareTo(node.e) < 0){
//            add(node.left,e);
//        }else{  // 大于0的情况，等于在一开始就判断了
//            add(node.right,e);
//        }
//    }

}
