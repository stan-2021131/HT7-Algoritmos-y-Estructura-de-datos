
import java.util.ArrayList;

public class Producto {
    private String SKU;
    private String nombre;
    private String descripcion;
    private ArrayList<String> tallas;
    private ArrayList<Integer> cantidadXtalla;

    public Producto() {
        this.tallas = new ArrayList<>();
        this.cantidadXtalla = new ArrayList<>();
    }

    public Producto(String SKU, String nombre, String descripcion) {
        this.SKU = SKU;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tallas = new ArrayList<>();
        this.cantidadXtalla = new ArrayList<>();
    }

    public String getSKU() {
        return SKU;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<String> getTallas() {
        return tallas;
    }

    public ArrayList<Integer> getCantidadXtalla() {
        return cantidadXtalla;
    }

    public void setSKU(String sKU) {
        SKU = sKU;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTallas(ArrayList<String> tallas) {
        this.tallas = tallas;
    }

    public void setCantidadXtalla(ArrayList<Integer> cantidadXtalla) {
        this.cantidadXtalla = cantidadXtalla;
    }

    @Override
    public String toString() {
        String tallasStr = "";
        for(int i = 0; i < this.tallas.size(); i++){
            tallasStr += this.tallas.get(i) + ":" + this.cantidadXtalla.get(i) + "|";
        }
        return "SKU: " + SKU + "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nTallas: " + tallasStr;
    }

    public String ruta(){
        String ruta = "SKU: " + this.SKU + " Nombre: " + this.nombre;
        return ruta;
    }
}
