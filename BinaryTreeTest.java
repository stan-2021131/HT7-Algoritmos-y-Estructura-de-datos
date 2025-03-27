import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {
    private BinaryTree<String, Producto> arbolPorSKU;
    private BinaryTree<String, Producto> arbolPorNombre;
    private Producto producto1, producto2, producto3;
    
    @Before
    public void setUp() {
        arbolPorSKU = new BinaryTree<>();
        arbolPorNombre = new BinaryTree<>();
        
        // Crear productos de prueba
        producto1 = new Producto("SKU001", "Camiseta", "Camiseta de algodón");
        producto2 = new Producto("SKU002", "Pantalón", "Pantalón de mezclilla");
        producto3 = new Producto("SKU003", "Zapatos", "Zapatos deportivos");
    }
    
    @Test
    public void testInsertarUnProducto() throws Exception {
        arbolPorSKU.insert(producto1.getSKU(), producto1);
        arbolPorNombre.insert(producto1.getNombre(), producto1);
        
        assertNotNull(arbolPorSKU.getParent());
        assertNotNull(arbolPorNombre.getParent());
        assertEquals(producto1, arbolPorSKU.getParent().getValue());
        assertEquals(producto1, arbolPorNombre.getParent().getValue());
    }
    
    @Test
    public void testInsertarMultiplesProductos() throws Exception {
        // Insertar productos en ambos árboles
        arbolPorSKU.insert(producto1.getSKU(), producto1);
        arbolPorSKU.insert(producto2.getSKU(), producto2);
        arbolPorSKU.insert(producto3.getSKU(), producto3);
        
        arbolPorNombre.insert(producto1.getNombre(), producto1);
        arbolPorNombre.insert(producto2.getNombre(), producto2);
        arbolPorNombre.insert(producto3.getNombre(), producto3);
        
        // Verificar que todos los productos fueron insertados correctamente
        assertNotNull(arbolPorSKU.search(producto1.getSKU()));
        assertNotNull(arbolPorSKU.search(producto2.getSKU()));
        assertNotNull(arbolPorSKU.search(producto3.getSKU()));
        
        assertNotNull(arbolPorNombre.search(producto1.getNombre()));
        assertNotNull(arbolPorNombre.search(producto2.getNombre()));
        assertNotNull(arbolPorNombre.search(producto3.getNombre()));
    }
    
    @Test
    public void testBuscarProductoExistente() throws Exception {
        arbolPorSKU.insert(producto1.getSKU(), producto1);
        arbolPorSKU.insert(producto2.getSKU(), producto2);
        
        BinaryTree<String, Producto> resultado = arbolPorSKU.search(producto1.getSKU());
        assertNotNull(resultado);
        assertEquals(producto1, resultado.getParent().getValue());
    }
}