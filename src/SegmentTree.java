// 线段树
public class SegmentTree <E>{
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[])(new Object[arr.length]);

        for(int i=0;i<arr.length;i++){
            data[i] = arr[i];
        }

        tree = (E[])(new Object[4 * arr.length]);   // 4倍

        buildSegmentTree(0,0,data.length-1);
    }

    // 在treeindex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex,int l,int r){
        if(l == r){
            tree[treeIndex] = data[l];  // 这个节点存储的就是自己本身
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l+(r-l)/2;    // 中间位置

        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);

        // 具体操作merge通过参数传来。
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);

    }


    public int getSize(){
        return data.length;
    }

    public E get(int index){
        return data[index];
    }

    // 元素左孩子索引
    private int leftChild(int index){
        return 2*index +1;
    }

    private int rightChild(int index){
        return 2*index +2;
    }



//    @Override
//    public String toString(){
//        StringBuilder res = new StringBuilder();
//        res.append("[");
//
//    }


    public static void main(String[] args) {
        Integer[] nums = {-2,0,3,-5,2,-1};
        SegmentTree<Integer> st = new SegmentTree<>(nums, (a,b) -> a+b );
    }
}
