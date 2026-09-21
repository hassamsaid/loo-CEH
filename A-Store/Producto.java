import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Producto extends Entidad {

    private String nombre;
    private String descripcion;
    private final List<String> categorias;
    private final List<Map<String, Object>> variantes;
    private float coste_compra;
    private float precio_venta;
    private final List<Proveedor> proveedores;

    //crear_producto()
    public Producto(int id, String nombre, String descripcion,
                    float coste_compra, float precio_venta) {
        
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste_compra = coste_compra;
        this.precio_venta = precio_venta;

        this.categorias = new ArrayList<>();
        this.variantes = new ArrayList<>();
        this.proveedores = new ArrayList<>();
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
    public List<Map<String, Object>> get_variantes() {
        return variantes;
    }

    public void agregar_variante(Map<String, Object> variante) {
        variantes.add(variante);
    }

    public void eliminar_variante(Map<String, Object> variante) {
        variantes.remove(variante);
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

            if (proveedor.get_nombre().equalsIgnoreCase(nombre)) {
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
}
