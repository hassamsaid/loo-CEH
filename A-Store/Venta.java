import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Venta extends Entidad {

    private String fecha;
    private String hora;
    private String metodo_pago;
    private float subtotal;
    private float total;
    private final List<DetalleVenta> productos_venta;
    private final Map<String, Float> descuentos;
    private final Map<String, Float> impuestos;
    public Venta(int id) {
        super(id);
        this.productos_venta = new ArrayList<>();
        this.descuentos = new HashMap<>();
        this.impuestos = new HashMap<>();
        this.subtotal = 0.0f;
        this.total = 0.0f;
    }
    public void agregar_productos(DetalleVenta producto) {
        this.productos_venta.add(producto);
    }
    public void eliminar_producto(DetalleVenta producto) {
        this.productos_venta.remove(producto);
    }
    public void agregar_descuento(String nombre, Float valor) {
        this.descuentos.put(nombre, valor);
    }
    public void eliminar_descuento(String nombre) {
        this.descuentos.remove(nombre);
    }
    public void agregar_impuesto(String nombre, Float valor) {
        this.impuestos.put(nombre, valor);
    }
    public void eliminar_impuesto(String nombre) {
        this.impuestos.remove(nombre);
    }
    public void calcular_subtotal() {
        this.subtotal = 0.0f;
    }
    public void calcular_total() {
        float suma_impuestos = 0.0f;
        float suma_descuentos = 0.0f;
        for (Float valor_impuesto : this.impuestos.values()) {
            suma_impuestos += valor_impuesto;
        }
        for (Float valor_descuento : this.descuentos.values()) {
            suma_descuentos += valor_descuento;
        }
        this.total = this.subtotal + suma_impuestos - suma_descuentos;
    }
    public List<DetalleVenta> get_productos_venta() {
        return this.productos_venta;
    }

    public String get_fecha() {
        return this.fecha;
    }

    public void set_fecha(String fecha) {
        this.fecha = fecha;
    }

    public String get_hora() {
        return this.hora;
    }

    public void set_hora(String hora) {
        this.hora = hora;
    }

    public Map<String, Float> get_descuentos() {
        return this.descuentos;
    }

    public Map<String, Float> get_impuestos() {
        return this.impuestos;
    }

    public float get_subtotal() {
        return this.subtotal;
    }

    public float get_total() {
        return this.total;
    }
    public String get_metodo_pago() {
        return this.metodo_pago;
    }
    public void set_metodo_pago(String nuevo_metodo) {
        this.metodo_pago = nuevo_metodo;
    }
}