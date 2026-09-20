import java.util.HashMap;
import java.util.Map;

public class DetalleVenta {
    
    private final int id;
    private final Producto producto; 
    private final Map<String, String> variante; 
    private int cantidad;
    private final float precio_unitario;
    private float subtotal_linea;

    public DetalleVenta(int id, Producto producto, Map<String, String> variante, int cantidad, float precio_unitario) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.variante = new HashMap<>(); 
        this.precio_unitario = precio_unitario;
        
        this.calcular_subtotal_linea(); 
    }

    public int get_id() {
        return this.id;
    }

    public Producto get_producto() {
        return this.producto;
    }

    public Map<String, String> get_variante() {
        return this.variante;
    }

    public int get_cantidad() {
        return this.cantidad;
    }

    public void set_cantidad(int cantidad) {
        this.cantidad = cantidad;
        this.calcular_subtotal_linea(); 
    }

    public float get_precio_unitario() {
        return this.precio_unitario;
    }

    public final float calcular_subtotal_linea() {
        this.subtotal_linea = this.cantidad * this.precio_unitario;
        return this.subtotal_linea;
    }

    public float get_subtotal_linea() {
        return this.subtotal_linea;
    }
}
