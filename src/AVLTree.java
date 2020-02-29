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

    private int getHeight(Node node){
        if(node == null) return 0;
        return node.height;
    }

    public void add(K key,V value){
        root = add(root,key,value);
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

        return node ;
    }



}
