
public class Node<K extends Comparable<K>, V> implements Comparable<K>{
    private K key;
    private V value;

    public Node(){
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {return key;}
    public V getValue() {return value;}

    public void setKey(K key) {this.key = key;}
    public void setValue(V value) {this.value = value;}

    @Override
    public int compareTo(K otherKey) {
        if (this.key instanceof String && otherKey instanceof String) {
            return ((String)this.key).compareToIgnoreCase((String)otherKey);
        }
        return this.key.compareTo(otherKey);
    }
}