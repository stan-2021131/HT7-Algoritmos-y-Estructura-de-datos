
import java.util.ArrayList;


/**
 * Implementación de un árbol binario de búsqueda genérico.
 * 
 * @param <K> Tipo de la clave, debe implementar Comparable
 * @param <V> Tipo del valor asociado a la clave
 */
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


    /**
     * Inserta un nuevo par clave-valor en el árbol.
     * 
     * @param key   Clave del nuevo nodo
     * @param value Valor asociado a la clave
     * @throws Exception Si la clave ya existe en el árbol
     */
    public void insert(K key, V value) throws Exception{
        if(parent == null){
            parent = new Node<>(key, value); return;}
        int result = this.parent.compareTo(key);
        if(result < 0) {
            if(this.right == null)this.right = new BinaryTree<>(new Node<>(key, value));
            else this.right.insert(key, value);
        } 
        else if(result > 0) {
            if(this.left == null) this.left = new BinaryTree<>(new Node<>(key, value));
            else this.left.insert(key, value);
        }
        else throw new Exception("La clave '" + key + "' ya existe dentro del árbol");
    }

    /**
     * Busca un nodo en el árbol por su clave.
     * 
     * @param key Clave a buscar
     * @return Árbol cuya raíz contiene la clave buscada, o null si no se encuentra
     * @throws Exception Si ocurre un error durante la búsqueda
     */
    public BinaryTree<K, V> search(K key) throws Exception{
        if(this.parent == null) return null;
        int result = this.parent.getKey().compareTo(key);
        if (result == 0) return this;
        else if (result < 0) return this.right != null ? this.right.search(key) : null;
        else return this.left != null ? this.left.search(key) : null;
    }

    /**
     * Obtiene una lista de valores en orden (in-order traversal).
     * 
     * @param node   Nodo raíz del subárbol a recorrer
     * @param result Lista donde se almacenarán los resultados
     * @return Lista de valores en orden
     */
    public ArrayList<V> getInOrder(BinaryTree<K, V> node, ArrayList<V> result){
        if(node != null && node.getParent() != null){
            getInOrder(node.getLeft(), result);
            result.add(node.getParent().getValue());
            getInOrder(node.getRight(), result);
        }
        return result;
    }

    /**
     * Verifica si el árbol contiene una clave específica.
     * 
     * @param key Clave a buscar
     * @return true si la clave existe en el árbol, false en caso contrario
     * @throws Exception Si ocurre un error durante la búsqueda
     */
    public boolean contains(K key) throws Exception{
        return search(key) != null;
    }
}