
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Clase responsable de cargar y guardar datos de productos desde/hacia archivos.
 * Proporciona métodos para convertir entre formatos de archivo y objetos Producto.
 */
public class CargaDatos {
        /**
     * Carga datos de productos desde un archivo CSV y los organiza en un árbol binario.
     * 
     * @param fileName Nombre del archivo CSV a cargar
     * @param orderBy  Criterio de ordenamiento ("SKU" o "Nombre")
     * @return Árbol binario con los productos ordenados según el criterio especificado
     */
    public BinaryTree<String, Producto> cargarDatos(String fileName, String orderBy){
        BinaryTree<String, Producto> allData = new BinaryTree<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            br.readLine();
            String[] dataRoot = br.readLine().split(",");
            Producto root = strToProducto(dataRoot);
            String key = (orderBy.equals("SKU") ? dataRoot[0]: (orderBy.equals("Nombre")) ? dataRoot[1].trim() : null);
            allData.insert(key, root);
            while((line = br.readLine()) != null){
                String[] productData = line.split(",");
                key = (orderBy.equals("SKU") ? productData[0]: (orderBy.equals("Nombre")) ? productData[1].trim() : null);
                if(key == null) continue;
                key = key.trim();
                Producto newProducto = strToProducto(productData);
                allData.insert(key, newProducto);
            }
        } catch (Exception e) {
            System.out.println("Error leyendo el archivo " + e.getMessage());
        }
        return allData;
    }

    /**
     * Convierte un array de strings (línea de CSV) a un objeto Producto.
     * 
     * @param data Array de strings con los datos del producto en formato CSV
     * @return Objeto Producto creado a partir de los datos
     */
    public Producto strToProducto(String[] data){
        Producto newPr = new Producto();
        newPr.setSKU(data[0]);
        newPr.setNombre(data[1]);
        newPr.setDescripcion(data[2]);
        String[] tallaData = data[3].split("\\|");
        for(String t : tallaData){
            String[] dt = t.split(":");
            newPr.getTallas().add(dt[0]);
            newPr.getCantidadXtalla().add(Integer.valueOf(dt[1]));
        }
        return newPr;
    }

    /**
     * Convierte un objeto Producto a su representación en formato CSV.
     * 
     * @param pr Producto a convertir
     * @return String con los datos del producto en formato CSV
     */
    public String productoToStr(Producto pr) {
        StringBuilder tallasStr = new StringBuilder();
        for (int i = 0; i < pr.getTallas().size(); i++) {
            if (i > 0) tallasStr.append("|");
            tallasStr.append(pr.getTallas().get(i))
                    .append(":")
                    .append(pr.getCantidadXtalla().get(i));
        }
        return String.join(",",
            pr.getSKU(),
            pr.getNombre(),
            pr.getDescripcion(),
            tallasStr.toString()
        );
    }

    /**
     * Guarda los datos de productos en un archivo CSV.
     * 
     * @param filename Nombre del archivo donde se guardarán los datos
     * @param data     Lista de strings con los datos de los productos en formato CSV
     */
    public void guardarDatos(String filename, ArrayList<String> data){
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename))){
            String encabezado = "SKU,Nombre,Descripción,Cantidad por talla";
            br.write(encabezado);
            br.newLine();
            for(String dat: data){
                br.write(dat);
                br.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error guardando los archivos");
        }
    }
}
