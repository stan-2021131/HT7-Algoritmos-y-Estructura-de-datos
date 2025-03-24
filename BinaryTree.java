
import java.util.ArrayList;

public class BinaryTree<K extends Comparable<K>, V>{
    private Node<K, V> parent;
    private BinaryTree<K, V> left = null;
    private BinaryTree<K, V> right = null;

    public BinaryTree() {
    }

    public BinaryTree(Node<K, V> parent) {
        this.parent = parent;
    }

    public void setLeft(BinaryTree<K, V> left) {this.left = left;}
    public BinaryTree<K, V> getLeft() {return left;}
    
    public void setRight(BinaryTree<K, V> right) {this.right = right;}
    public BinaryTree<K, V> getRight() {return right;}

    public void setParent(Node<K, V> parent) {this.parent = parent;}
    public Node<K, V> getParent() {return parent;}


    public void insert(K key, V value) throws Exception{
        if(parent == null){
            parent = new Node<>(key, value); return;}
        int result = this.parent.compareTo(key);
        switch (result) {
            case -1 -> {
                if(this.right == null){
                    Node<K,V> newNode = new Node<>(key, value);
                    this.right = new BinaryTree<>(newNode);
                }else{
                    this.right.insert(key, value);
                }
            }
            case 1 -> {
                if(this.left == null){
                    Node<K,V> newNode = new Node<>(key, value);
                    this.left = new BinaryTree<>(newNode);
                }else{
                    this.left.insert(key, value);
                }
            }
            default -> throw new Exception("La clave ya existe dentro del árbol");
        }
    }

    public BinaryTree<K, V> search(K key, ArrayList<V> route) throws Exception{
        if(this.parent == null) return null;
        if(this.parent.getKey().compareTo(key) == 0) return this;
        else{
            if(route != null) route.add(this.parent.getValue());
            int result = this.parent.getKey().compareTo(key);
            switch (result) {
                case -1 -> {
                    if(this.right.getParent().getKey().compareTo(key)==0) return this.right;
                    else return this.right != null ? this.right.search(key, route) : null;
                }
                case 1 -> {
                    if(this.left.getParent().getKey().compareTo(key)==0) return this.left;
                    else return this.left != null ? this.left.search(key, route) : null;
                }
                default -> throw new Exception("Valor no encontrado dentro del árbol");
            }
        }
    }

    public boolean contains(K key) throws Exception{
        return search(key, null) != null;
    }
}