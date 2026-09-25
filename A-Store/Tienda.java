import java.util.*;

public class Tienda {

    private final List<Empleado> empleados;
    private final Inventario inventario;
    private final AdministradorFinanciero administrador_financiero;
    private final List<Proveedor> proveedores;
    private final List<Venta> ventas;

    // Guarda las ID's usadas por cada clase, para evitar duplicidad
    private final Map<String, List<Integer>> ids_globales;

    public Tienda() {
        empleados = new ArrayList<>();
        inventario = new Inventario(generador_id("Inventario"));
        administrador_financiero = new AdministradorFinanciero(generador_id("AdministradorFinanciero"), 0, 0, 0);
        proveedores = new ArrayList<>();
        ventas = new ArrayList<>();
        ids_globales = new HashMap<>();
    }

    //public void guardar_datos() {}

    //public static Tienda cargar_datos() {}

    public final int generador_id(String class_name) {

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
   public Map<String, Object> agregar_producto(String nombre, String descripcion, float coste_compra, float precio_venta) {
        int id = generador_id("Producto");
        Producto nuevo_producto = new Producto(id, nombre, descripcion, coste_compra, precio_venta);


    //public Map<String, Object> buscar_producto(args) {}
    return inventario.agregar_producto(nuevo_producto);
    }

    public Map<String, Object> buscar_producto(int id) {
        Map<String, Object> result = new HashMap<>();


    //public Map<String, Object> modificar_producto(args) {}
   for (Producto p : inventario.get_productos().keySet()) {
            if (p.get_id() == id) {
                result.put("exito", true);
                result.put("producto", p);
                return result;
            }
        }
        
        result.put("exito", false);
        result.put("mensaje", "Producto no encontrado.");
        return result;
    }

    public Map<String, Object> modificar_producto(int id, String nombre, String descripcion, float coste_compra, float precio_venta) {
        Map<String, Object> result = buscar_producto(id);
        
        if ((boolean) result.get("exito")) {
            Producto p = (Producto) result.get("producto");
            p.set_nombre(nombre);
            p.set_descripcion(descripcion);
            p.set_coste_compra(coste_compra);
            p.set_precio_venta(precio_venta);
            result.put("mensaje", "Producto modificado exitosamente.");
        }
        return result;
    }

    //public Map<String, Object> ocultar_producto(args) {}
    public Map<String, Object> ocultar_producto(int id) {
        Map<String, Object> result = buscar_producto(id);
        
        if ((boolean) result.get("exito")) {
            Producto p = (Producto) result.get("producto");
            p.set_oculto(true); // Método heredado de Entidad
            result.put("mensaje", "Producto ocultado del sistema.");
        }
        return result;
    }

    //PROVEEDOR

    //public Map<String, Object> agregar_proveedor(args) {}
    public Map<String, Object> agregar_proveedor(String nombre, String contacto, String ubicacion) {
        Map<String, Object> result = new HashMap<>();
        
        int id = generador_id("Proveedor");
        Proveedor nuevo_proveedor = new Proveedor(id, nombre, contacto, ubicacion);
        proveedores.add(nuevo_proveedor);
        
        result.put("exito", true);
        result.put("mensaje", "Proveedor agregado exitosamente.");
        result.put("proveedor", nuevo_proveedor);
        return result;
    }

    //public Map<String, Object> buscar_proveedor(args) {}
    public Map<String, Object> buscar_proveedor(int id) {
        Map<String, Object> result = new HashMap<>();
        
        for (Proveedor prov : proveedores) {
            if (prov.get_id() == id) {
                result.put("exito", true);
                result.put("proveedor", prov);
                return result;
            }
        }
        
        result.put("exito", false);
        result.put("mensaje", "Proveedor no encontrado.");
        return result;
    }

    //public Map<String, Object> modificar_proveedor(args) {}
    public Map<String, Object> modificar_proveedor(int id, String nombre, String contacto, String ubicacion) {
        Map<String, Object> result = buscar_proveedor(id);
        
        if ((boolean) result.get("exito")) {
            Proveedor prov = (Proveedor) result.get("proveedor");
            prov.set_nombre(nombre);
            prov.set_contacto(contacto);
            prov.set_ubicacion(ubicacion);
            result.put("mensaje", "Proveedor modificado exitosamente.");
        }
        return result;
    }

    //public Map<String, Object> ocultar_proveedor(args) {}
    public Map<String, Object> ocultar_proveedor(int id) {
        Map<String, Object> result = buscar_proveedor(id);
        
        if ((boolean) result.get("exito")) {
            Proveedor prov = (Proveedor) result.get("proveedor");
            prov.set_oculto(true);
            result.put("mensaje", "Proveedor ocultado del sistema.");
        }
        return result;
    }

    //EMPLEADOS

    //public Map<String, Object> agregar_empleado(args) {}
public Map<String, Object> agregar_empleado(String nombre, String tipo, String cargo, float salario, String horario) {
        Map<String, Object> result = new HashMap<>();
        
        int id = generador_id("Empleado");
        Empleado nuevo_empleado = new Empleado(id, nombre, tipo, cargo, salario, horario);
        empleados.add(nuevo_empleado);
        
        result.put("exito", true);
        result.put("mensaje", "Empleado agregado exitosamente.");
        result.put("empleado", nuevo_empleado);
        return result;
    }

    //public Map<String, Object> buscar_empleado(args) {}
public Map<String, Object> buscar_empleado(int id) {
        Map<String, Object> result = new HashMap<>();
        
        for (Empleado emp : empleados) {
            if (emp.get_id() == id) {
                result.put("exito", true);
                result.put("empleado", emp);
                return result;
            }
        }

        result.put("exito", false);
        result.put("mensaje", "Empleado no encontrado.");
        return result;
    }

    //public Map<String, Object> modificar_empleado(args) {}
    public Map<String, Object> modificar_empleado(int id, String nombre, String tipo, String cargo, float salario, String horario) {
        Map<String, Object> result = buscar_empleado(id);
        
        if ((boolean) result.get("exito")) {
            Empleado emp = (Empleado) result.get("empleado");
            emp.set_nombre(nombre);
            emp.set_tipo(tipo);
            emp.set_cargo(cargo);
            emp.set_salario(salario);
            emp.set_horario(horario);
            result.put("mensaje", "Empleado modificado exitosamente.");
        }
        return result;
    }

    //public Map<String, Object> ocultar_empleado(args) {}
    public Map<String, Object> ocultar_empleado(int id) {
        Map<String, Object> result = buscar_empleado(id);
        
        if ((boolean) result.get("exito")) {
            Empleado emp = (Empleado) result.get("empleado");
            emp.set_oculto(true);
            result.put("mensaje", "Empleado ocultado del sistema.");
        }
        return result;
    }

    //VENTAS

    //public Map<String, Object> agregar_venta(args) {}
    public Map<String, Object> agregar_venta(String fecha, String hora, String metodo_pago) {
        Map<String, Object> result = new HashMap<>();
        
        int id = generador_id("Venta");
        Venta nueva_venta = new Venta(id);
        nueva_venta.set_fecha(fecha);
        nueva_venta.set_hora(hora);
        nueva_venta.set_metodo_pago(metodo_pago);
        
        ventas.add(nueva_venta);
        
        result.put("exito", true);
        result.put("mensaje", "Venta creada exitosamente.");
        result.put("venta", nueva_venta);
        return result;
    }

    //public Map<String, Object> buscar_venta(args) {}
    public Map<String, Object> buscar_venta(int id) {
        Map<String, Object> result = new HashMap<>();
        
        for (Venta v : ventas) {
            if (v.get_id() == id) {
                result.put("exito", true);
                result.put("venta", v);
                return result;
            }
        }
        
        result.put("exito", false);
        result.put("mensaje", "Venta no encontrada.");
        return result;
    }

    //public Map<String, Object> modificar_venta(args) {}
    public Map<String, Object> modificar_venta(int id, String fecha, String hora, String metodo_pago) {
        Map<String, Object> result = buscar_venta(id);
        
        if ((boolean) result.get("exito")) {
            Venta v = (Venta) result.get("venta");
            v.set_fecha(fecha);
            v.set_hora(hora);
            v.set_metodo_pago(metodo_pago);
            result.put("mensaje", "Datos de la venta modificados exitosamente.");
        }
        return result;
    }

    //public Map<String, Object> ocultar_venta(args) {}
    public Map<String, Object> ocultar_venta(int id) {
        Map<String, Object> result = buscar_venta(id);
        
        if ((boolean) result.get("exito")) {
            Venta v = (Venta) result.get("venta");
            v.set_oculto(true);
            result.put("mensaje", "Venta ocultada del sistema.");
        }
        return result;
    }
}