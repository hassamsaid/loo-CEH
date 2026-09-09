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
    private float costeCompra;
    private float precioVenta;
    private List<Proveedor> proveedores;
    private boolean oculto;


    //crear_producto()
    public Producto(int id, String nombre, String descripcion,
                    float costeCompra, float precioVenta) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costeCompra = costeCompra;
        this.precioVenta = precioVenta;

        this.categorias = new ArrayList<>();
        this.variantes = new HashMap<>();
        this.proveedores = new ArrayList<>();
        this.oculto = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    //categorias
    public List<String> getCategorias() {
        return categorias;
    }

    public void anadirCategoria(String categoria) {
        categorias.add(categoria);
    }

    public void eliminarCategoria(String categoria) {
        categorias.remove(categoria);
    }


    //variantes
    public Map<String, String> getVariantes() {
        return variantes;
    }

    public void anadirVariante(String nombre, String valor) {
        variantes.put(nombre, valor);
    }

    public void eliminarVariante(String nombre) {
        variantes.remove(nombre);
    }


    //coste compra
    public float getCosteCompra() {
        return costeCompra;
    }

    public void setCosteCompra(float costeCompra) {
        this.costeCompra = costeCompra;
    }


    //precio venta
    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }


    //provedores
    public void anadirProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public Proveedor buscarProveedor(String nombre) {

        for (Proveedor proveedor : proveedores) {

            if (proveedor.getNombre().equalsIgnoreCase(nombre)) {
                return proveedor;
            }

        }

        return null;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void eliminarProveedor(Proveedor proveedor) {
        proveedores.remove(proveedor);
    }


    //calcular ganancia uni
    public float calcularGananciaUnitaria() {
        return precioVenta - costeCompra;
    }

    //oculto
    public boolean getOculto() {
        return oculto;
    }

    public void setOculto(boolean oculto) {
        this.oculto = oculto;
    }
}
