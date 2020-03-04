import java.util.ArrayList;

// AVL树
public class AVLTree <K extends Comparable<K>,V >{
    // 节点
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height; // 节点高度

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 判断该二叉树是否是二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        midOrder(root,keys);
        for (int i = 1; i < keys.size(); i++) {
            // 判读是否升序，中序遍历性质
            if(keys.get(i-1).compareTo(keys.get(i))>0 ){
                return false;
            }
        }
        return true;
    }

    private void midOrder(Node node,ArrayList<K> keys){
        if(node == null) return;
        midOrder(node.left,keys);
        keys.add(node.key);
        midOrder(node.right,keys);
    }

    // 是否是平衡二叉树
    public boolean isBalance(){
        return isBalance(root);
    }

    // 判断是否是平衡二叉树
    private boolean isBalance(Node node){
        if(node == null) return true;   //

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalance(node.left) && isBalance(node.right);

    }

    private int getHeight(Node node){
        if(node == null) return 0;
        return node.height;
    }

    public void add(K key,V value){
        root = add(root,key,value);
    }

    // 计算平衡因子
    private int getBalanceFactor( Node node){
        if(node ==null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = T3;

        // 更新height,先更新y，左右子树的最大值+1
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 以Node为根的二分搜索树中插入元素
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node , K key,V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }

        // 递归
        if(key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else{
            node.value = value; // 相等则更新值
        }

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        int balanceFactor = getBalanceFactor(node);
//        if(Math.abs(balanceFactor) > 1){
//            System.out.println("不是平衡二叉树");
//        }

        // 平衡维护
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){   // 当前节点不平衡，不平衡结构为 left left
            return rightRotate(node);
        }

        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){   //
            return leftRotate(node);
        }

        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node ;
    }

    public static void main(String[] args) {
        AVLTree avl = new AVLTree<>();
        avl.add(3,2);
        avl.add(2,2);
        avl.add(1,3);
        System.out.println(avl.isBST());
        System.out.println(avl.isBalance());
    }


}
