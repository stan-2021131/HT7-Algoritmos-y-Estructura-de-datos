import java.util.ArrayList;
import java.util.Scanner;


/**
 * Clase principal que contiene el método main para ejecutar la aplicación de gestión de productos.
 * Proporciona un menú interactivo para agregar, buscar, listar y editar productos.
 */
public class Main {
    public static void main(String[] args) {
        final String FILENAME = "inventario_ropa_deportiva_30.csv"; //Cambiar si se desea usar otro archivo
        CargaDatos c = new CargaDatos();
        BinaryTree<String, Producto> allDataxSKU = c.cargarDatos(FILENAME, "SKU");
        BinaryTree<String, Producto> allDataxNombre = c.cargarDatos(FILENAME, "Nombre");
        ArrayList<Producto> prod = new ArrayList<>();
        ArrayList<String> prodstr = new ArrayList<>();
        boolean menu = true;
        boolean menu1 = true;
        
        System.out.println("--Gestión de productos--");
        try (Scanner sc = new Scanner(System.in)) {
            while(menu){
                System.out.println("Seleccione una opción: \n1. Agregar Producto\n2. Buscar producto\n3. Listar todos los productos ordenados\n4. Editar Producto\n5. Salir");
                int op = sc.nextInt();
                switch (op) {
                    case 1 -> {
                        menu1 = true;
                        ArrayList<String> newTallas = new ArrayList<>();
                        ArrayList<Integer> newCants = new ArrayList<>();
                        sc.nextLine();
                        System.out.println("Ingrese el SKU del producto: ");
                        String sku = sc.nextLine();
                        System.out.println("Ingrese el nombre del producto: ");
                        String nombre = sc.nextLine();
                        System.out.println("Ingrese la descripción del producto: ");
                        String descr = sc.nextLine();
                        while(menu1){
                            System.out.println("1. Ingresar nueva talla \n2.Listo");
                            op = sc.nextInt();
                            switch (op) {
                                case 1-> {
                                    sc.nextLine();
                                    System.out.println("Ingrese el nombre de la talla: ");
                                    String newTall = sc.nextLine();
                                    System.out.println("Ingrese la cantidad: ");
                                    int newCant = sc.nextInt();
                                    newTallas.add(newTall);
                                    newCants.add(newCant);
                                }
                                case 2 ->{
                                    if(newTallas.isEmpty()) System.out.println("El nuevo producto debe tener al menos 1 talla");
                                    menu1 = !menu1;
                                }
                                default -> System.out.println("Ingrese una opción válida");
                            }
                        }
                        Producto newPr = new Producto(sku, nombre, descr);
                        newPr.setTallas(newTallas);
                        newPr.setCantidadXtalla(newCants);
                        try {
                            allDataxNombre.insert(nombre, newPr);
                            allDataxSKU.insert(sku, newPr);
                            System.out.println("Nuevo producto agregado: \n" + newPr.toString());
                        } catch (Exception ex) {
                        }
                    }
                    case 2 -> {
                        sc.nextLine();
                        System.out.println("Ingrese el nombre o SKU del producto: ");
                        String dato = sc.nextLine();
                        try {
                            if(allDataxNombre.contains(dato)) System.out.println(allDataxNombre.search(dato).getParent().getValue().toString());
                            else if(allDataxSKU.contains(dato)) System.out.println(allDataxSKU.search(dato).getParent().getValue().toString());
                            else System.out.println("Producto no encontrado");
                        } catch (Exception ex) {
                            System.out.println("Error en la búsqueda del registro: " + ex.getMessage());
                        }
                    }
                    case 3 ->{
                        System.out.println("Ordenar por: \n1. SKU\t2.Nombre");
                        op = sc.nextInt();
                        ArrayList<Producto> inOrderList = new ArrayList<>();
                        if(op==1) inOrderList = allDataxSKU.getInOrder(allDataxSKU, inOrderList);
                        if(op==2) inOrderList = allDataxNombre.getInOrder(allDataxNombre, inOrderList);
                        for(Producto pr: inOrderList)
                            System.out.println(pr.ruta());
                    }
                    case 4 ->{
                        menu1 = true;
                        sc.nextLine();
                        System.out.println("Ingrese el nombre o SKU del producto: ");
                        String dato = sc.nextLine();
                        Producto pr = new Producto();
                        try {
                            if(allDataxNombre.contains(dato)) pr = allDataxNombre.search(dato).getParent().getValue();
                            else if(allDataxSKU.contains(dato)) pr = allDataxSKU.search(dato).getParent().getValue();
                            else{
                                System.out.println("Producto no encontrado");
                            }
                        } catch (Exception ex) {
                            System.out.println("Error en la búsqueda del registro: " + ex.getMessage());
                        }
                        System.out.println("Producto encontrado " + pr.ruta());
                        while(menu1){
                            System.out.println("Ingrese la opción que requiera: \n1. Cambiar descripción\n2. Tallas disponibles (Solo agregar)\n3. Cantidad por talla\n4 Listo");
                            op = sc.nextInt();
                            sc.nextLine();
                            switch (op) {
                                case 1 -> {
                                    System.out.println("Ingrese la nueva descripción del producto: ");
                                    String descri = sc.nextLine();
                                    pr.setDescripcion(descri);
                                }
                                case 2 -> {
                                    System.out.println("Ingrese la nueva talla: ");
                                    String talla = sc.nextLine();
                                    pr.getTallas().add(talla);
                                    pr.getCantidadXtalla().add(0);
                                }
                                case 3 -> {
                                    System.out.println("¿De cuál talla desea cambiar la cantidad?");
                                    for(int i = 0; i<pr.getTallas().size(); i++){
                                        System.out.println(i + ") " + pr.getTallas().get(i));
                                    }
                                    op = sc.nextInt();
                                    System.out.println("Ingrese la nueva cantidad: ");
                                    int newCant = sc.nextInt();
                                    pr.getCantidadXtalla().set(op, newCant);
                                }
                                case 4 -> {
                                    menu1 = !menu1;
                                }
                                default -> System.out.println("Ingrese una opción correcta");
                            }
                        }
                    }
                    case 5 -> {
                        prod = allDataxSKU.getInOrder(allDataxSKU, prod);
                        for(Producto pr: prod){
                            prodstr.add(c.productoToStr(pr));
                        }
                        c.guardarDatos(FILENAME, prodstr);
                        menu = !menu;
                    }
                    default -> throw new AssertionError();
                }
            }
        }
    }
}
