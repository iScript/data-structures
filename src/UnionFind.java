public class UnionFind implements UF {

    private int[] parent;   // 第i个元素指向的节点
    private int[] rank;       // 以i为根的集合中元素层数
    public UnionFind(int size){
        parent  = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;  // 每个元素指向自己
            rank[i] = 1;
        }
    }

    // 查找元素p对应的编号
    private int find(int p){
        // 找根节点，根节点指向自己
        while(p != parent[p])
            parent[p] = parent[parent[p]];  // p指向父亲的父亲，路径压缩优化，降低树的高度
            p = parent[p];
        return p;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot){ // 已经在同一个集合
            return ;
        }

        // 性能优化，元素层数比较少的指向元素层数比较高的节点
        if(rank[pRoot]<rank[qRoot]) {
            // 2个集合合并成一个集合,p的根指向q的根
            parent[pRoot] = qRoot;
        }else if(rank[qRoot]<rank[pRoot]){
            parent[qRoot] = pRoot;
        }else{
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;   // q指向p，p+1，上面由于小的指向大的，高度不变
        }

    }
}
