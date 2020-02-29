import java.util.TreeMap;

// 字典树
public class Trie {
    private class Node{
        public boolean isWord;  // 到这个节点是否是一个单词
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    // 向trie中添加一个新的单词
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 在next映射中查找,没有则在next map中添加一个
            if(cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            //
            cur = cur.next.get(c);  // 更新指向
        }
        if(!cur.isWord){
            cur.isWord = true;  // 到了单词的末尾
            size++;
        }
    }

    // 查询单词word是否包含在Trie中
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 在next映射中查找
            if(cur.next.get(c) == null){
                return  false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 前缀搜索,查询是否在trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // 在next映射中查找
            if(cur.next.get(c) == null){
                return  false;
            }
            cur = cur.next.get(c);
        }

        //
        return true;
    }


    public static void main(String[] args) {
        Trie t = new Trie();
        t.add("cat");
        t.add("panda");

        System.out.println(t.contains("panda"));
        System.out.println(t.contains("pan"));
    }

}
