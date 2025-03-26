
import java.io.BufferedReader;
import java.io.FileReader;

public class CargaDatos {
    public BinaryTree<String, Producto> cargarDatos(String fileName, String orderBy){
        BinaryTree<String, Producto> allData = new BinaryTree<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            br.readLine();
            String[] dataRoot = br.readLine().split(",");
            Producto root = strToProducto(dataRoot);
            String key = (orderBy.equals("SKU") ? dataRoot[0]: (orderBy.equals("Nombre")) ? dataRoot[1] : null);
            allData.insert(key, root);
            while((line = br.readLine()) != null){
                String[] productData = line.split(",");
                key = (orderBy.equals("SKU") ? productData[0]: (orderBy.equals("Nombre")) ? productData[1] : null);
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
}
