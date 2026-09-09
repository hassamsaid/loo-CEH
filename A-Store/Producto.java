import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Producto {

    private int id;
    private String nombre;
    private String descripcion;
    private List<String> categorias;
    private Map<String, String> variantes;
    private float coste_dompra;
    private float precio_venta;
    private List<Proveedor> proveedores;
    private boolean oculto;


    //crear_producto()
    public Producto(int id, String nombre, String descripcion,
                    float coste_compra, float precio_venta) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste_compra = coste_compra;
        this.precio_venta = precio_venta;

        this.categorias = new ArrayList<>();
        this.variantes = new HashMap<>();
        this.proveedores = new ArrayList<>();
        this.oculto = false;
    }

    public String get_nombre() {
        return nombre;
    }

    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }

    public String get_descripcion() {
        return descripcion;
    }

    public void set_descripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    //categorias
    public List<String> get_categorias() {
        return categorias;
    }

    public void anadir_categoria(String categoria) {
        categorias.add(categoria);
    }

    public void eliminar_categoria(String categoria) {
        categorias.remove(categoria);
    }


    //variantes
    public Map<String, String> get_variantes() {
        return variantes;
    }

    public void agregar_variante(String nombre, String valor) {
        variantes.put(nombre, valor);
    }

    public void eliminar_variante(String nombre) {
        variantes.remove(nombre);
    }


    //coste compra
    public float get_coste_compra() {
        return coste_compra;
    }

    public void set_coste_compra(float coste_compra) {
        this.coste_compra = coste_compra;
    }


    //precio venta
    public float get_precio_venta() {
        return precio_venta;
    }

    public void set_precio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }


    //provedores
    public void agregar_proveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public Proveedor buscar_proveedor(String nombre) {

        for (Proveedor proveedor : proveedores) {

            if (proveedor.getNombre().equalsIgnoreCase(nombre)) {
                return proveedor;
            }

        }

        return null;
    }

    public List<Proveedor> get_proveedores() {
        return proveedores;
    }

    public void eliminar_proveedor(Proveedor proveedor) {
        proveedores.remove(proveedor);
    }


    //calcular ganancia uni
    public float calcular_ganancia_unitaria() {
        return precio_venta - coste_compra;
    }

    //oculto
    public boolean get_oculto() {
        return oculto;
    }

    public void set_oculto(boolean oculto) {
        this.oculto = oculto;
    }
}
