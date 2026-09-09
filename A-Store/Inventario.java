import java.util.*;

public class Inventario {

    private int id;

    // Producto -> Variante -> Cantidad
    private final Map<Producto, Map<Map<String, Object>, Integer>> productos;


    public Inventario() {
        productos = new HashMap<>();
    }


    public static Inventario crear_inventario() {
        return new Inventario();
    }


    // GETTERS

    public int get_id() {
        return id;
    }


    public Map<Producto, Map<Map<String, Object>, Integer>> get_productos() {
        return productos;
    }

    // PRODUCTOS

    public Map<String, Object> agregar_producto(Producto producto) {
        Map<String, Object> result = new HashMap<>();

        if (productos.containsKey(producto)) {
            result.put("exito", false);
            result.put("mensaje", "El producto ya existe en el inventario.");
            return result;
        }

        productos.put(producto, new HashMap<>());

        result.put("exito", true);
        result.put("producto", producto);

        return result;
    }


    public Map<String, Object> buscar_producto(Producto producto) {

        Map<String, Object> result = new HashMap<>();

        if (productos.containsKey(producto)) {
            result.put("exito", true);
            result.put("mensaje", "Producto encontrado.");
            result.put("producto", producto);
        } else {
            result.put("exito", false);
            result.put("mensaje", "Producto no encontrado.");
            result.put("producto", null);
        }

        return result;
    }

    // EXISTENCIAS

    public Map<String, Object> agregar_existencias(Producto producto, Map<String, Object> variante, int cantidad) {

        Map<String, Object> result = new HashMap<>();  
        result.put("exito", false);
        result.put("existencias", null); 

        Map<String, Object> result_buscar_producto = buscar_producto(producto);

        if (!(boolean) result_buscar_producto.get("exito")) {
            result.put("mensaje", result_buscar_producto.get("mensaje"));
            return result;
        }

        if (!producto.get_variantes().contains(variante)) {
            result.put("mensaje", "La variante no existe para este producto.");
            return result;
        }

        if (cantidad < 0) {
            result.put("mensaje", "La cantidad no es válida.");
            return result;
        }

        Map<Map<String, Object>, Integer> variantes_producto = productos.get(producto);

        variantes_producto.putIfAbsent(variante, 0);
        int existencias_actuales = variantes_producto.getOrDefault(variante, 0);

        variantes_producto.put(variante, existencias_actuales + cantidad);

        result.put("exito", true);
        result.put("mensaje", "Se agregaron existencias exitosamente.");
        result.put("existencias", existencias_actuales + cantidad);

        return result;
    }

    public Map<String, Object> retirar_existencias(Producto producto, Map<String, Object> variante, int cantidad) {

        Map<String, Object> result = new HashMap<>();  
        result.put("exito", false);
        result.put("existencias", null); 

        Map<String, Object> result_buscar_producto = buscar_producto(producto);

        if (!(boolean) result_buscar_producto.get("exito")) {
            result.put("mensaje", result_buscar_producto.get("mensaje"));
            return result;
        }

        if (!producto.get_variantes().contains(variante)) {
            result.put("mensaje", "La variante no existe para este producto.");
            return result;
        }

        if (cantidad < 0) {
            result.put("mensaje", "La cantidad no es válida.");
            return result;
        }

        Map<Map<String, Object>, Integer> variantes_producto = productos.get(producto);

        variantes_producto.putIfAbsent(variante, 0);
        int existencias_actuales = variantes_producto.getOrDefault(variante, 0);

        if (cantidad > existencias_actuales) {
            result.put("mensaje", "No hay suficientes existencias.");
            return result;
        }

        variantes_producto.put(variante, existencias_actuales - cantidad);

        result.put("exito", true);
        result.put("mensaje", "Se retiraron existencias exitosamente.");
        result.put("existencias", existencias_actuales - cantidad);

        return result;
    }

    public int obtener_existencias(Producto producto, Map<String, Object> variante) {

        Map<String, Object> result = buscar_producto(producto);

        if (!(boolean) result.get("exito")) {
            return 0;
        }

        Map<Map<String, Object>, Integer> variantes_producto = productos.get(producto);

        if (!variantes_producto.containsKey(variante)) {
            return 0;
        }

        return variantes_producto.get(variante);
    }

    }