import java.util.*;

public class Tienda{

    private List<Empleado> empleados;
    private Inventario inventario;
    private AdministradorFinanciero administrador_financiero;
    private List<Proveedor> proveedores;
    private List<Venta> ventas;

    // Guarda las ID's usadas por cada clase, para evitar duplicidad
    private Map<String, List<Integer>> ids_globales;

    public Tienda() {
        empleados = new ArrayList<>();
        inventario = new Inventario();
        administrador_financiero = new AdministradorFinanciero();
        proveedores = new ArrayList<>();
        ventas = new ArrayList<>();
        ids_globales = new HashMap<>();
    }

    public static Tienda crear_tienda() {
        return new Tienda();
    }

    //public void guardar_datos() {}

    //public static Tienda cargar_datos() {}

    public int generador_id(String class_name) {

    ids_globales.putIfAbsent(class_name, new ArrayList<>());

    List<Integer> ids_usados = ids_globales.get(class_name);

    int nuevo_id;

    if (ids_usados.isEmpty()) {
        nuevo_id = 1;
    } else {
        nuevo_id = ids_usados.get(ids_usados.size() - 1) + 1;
    }

    ids_usados.add(nuevo_id);

    return nuevo_id;
}

    //GETTERS

    public List<Empleado> get_empleados() {
        return empleados;
    }


    public Inventario get_inventario() {
        return inventario;
    }


    public AdministradorFinanciero get_administrador_financiero() {
        return administrador_financiero;
    }


    public List<Proveedor> get_proveedores() {
        return proveedores;
    }


    public List<Venta> get_ventas() {
        return ventas;
    }

    //PRODUCTO

    //public Map<String, Object> agregar_producto(args) {}


    //public Map<String, Object> buscar_producto(args) {}


    //public Map<String, Object> modificar_producto(args) {}


    //public Map<String, Object> ocultar_producto(args) {}

    //PROVEEDOR

    //public Map<String, Object> agregar_proveedor(args) {}


    //public Map<String, Object> buscar_proveedor(args) {}


    //public Map<String, Object> modificar_proveedor(args) {}


    //public Map<String, Object> ocultar_proveedor(args) {}


    //EMPLEADOS

    //public Map<String, Object> agregar_empleado(args) {}


    //public Map<String, Object> buscar_empleado(args) {}


    //public Map<String, Object> modificar_empleado(args) {}


    //public Map<String, Object> ocultar_empleado(args) {}


    //VENTAS

    //public Map<String, Object> agregar_venta(args) {}


    //public Map<String, Object> buscar_venta(args) {}


    //public Map<String, Object> modificar_venta(args) {}


    //public Map<String, Object> ocultar_venta(args) {}