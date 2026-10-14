import java.util.*;
import java.io.*;
import java.lang.reflect.Type;

//El error que no existe el paquete com es un falso negativo. Ignorar el error si el código compila.
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Tienda {

    private final List<Empleado> empleados;
    private Inventario inventario;
    private AdministradorFinanciero administrador_financiero;
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

    // DATOS

    private static final String ARCHIVO_DATOS = "datos.json";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public void guardar_datos() {

        JsonObject datos = new JsonObject();

        datos.add("AdministradorFinanciero", guardar_administrador_financiero());

        datos.add("DetalleVenta", guardar_detalle_venta());

        datos.add("Empleado", guardar_empleado());

        datos.add("Inventario", guardar_inventario());

        datos.add("Producto", guardar_producto());

        datos.add("Proveedor", guardar_proveedor());

        datos.add("Venta", guardar_venta());

        datos.add("ids_globales", GSON.toJsonTree(ids_globales));

        try (FileWriter writer = new FileWriter(ARCHIVO_DATOS)) {
            GSON.toJson(datos, writer);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    public JsonObject guardar_administrador_financiero() {

        if (administrador_financiero == null) {return new JsonObject();}

        JsonObject administrador_json = new JsonObject();

        administrador_json.addProperty(
            "id",
            administrador_financiero.get_id()
        );
        administrador_json.addProperty(
            "costos_mantenimiento",
            administrador_financiero.get_costos_mantenimiento()
        );
        administrador_json.addProperty(
            "servicios",
            administrador_financiero.get_servicios()
        );
        administrador_json.addProperty(
            "otros_gastos",
            administrador_financiero.get_otros_gastos()
        );
        administrador_json.addProperty(
            "ingreso_total",
            administrador_financiero.get_ingreso_total()
        );

        return administrador_json;
    }
    
    public JsonArray guardar_detalle_venta() {

        JsonArray detalles_json = new JsonArray();

        for (Venta venta : ventas) {

            for (DetalleVenta detalle : venta.get_productos_venta()) {

                JsonObject detalle_json = new JsonObject();

                detalle_json.addProperty(
                    "id",
                    detalle.get_id()
                );
                detalle_json.addProperty(
                    "producto_id",
                    detalle.get_producto().get_id()
                );
                detalle_json.add(
                    "variante",
                    GSON.toJsonTree(detalle.get_variante())
                );
                detalle_json.addProperty(
                    "cantidad",
                    detalle.get_cantidad()
                );
                detalle_json.addProperty(
                    "precio_unitario",
                    detalle.get_precio_unitario()
                );
                detalle_json.addProperty(
                    "subtotal_linea",
                    detalle.get_subtotal_linea()
                );

                detalles_json.add(detalle_json);
            }
        }

        return detalles_json;
    }

    public JsonArray guardar_empleado() {

        JsonArray empleados_json = new JsonArray();

        for (Empleado empleado : empleados) {

            JsonObject empleado_json = new JsonObject();

            empleado_json.addProperty(
                "id",
                empleado.get_id()
            );
            empleado_json.addProperty(
                "nombre",
                empleado.get_nombre()
            );
            empleado_json.addProperty(
                "tipo",
                empleado.get_tipo()
            );
            empleado_json.addProperty(
                "cargo",
                empleado.get_cargo()
            );
            empleado_json.addProperty(
                "salario",
                empleado.get_salario()
            );
            empleado_json.addProperty(
                "horario",
                empleado.get_horario()
            );
            empleado_json.addProperty(
                "oculto",
                empleado.get_oculto()
            );

            empleados_json.add(empleado_json);
        }

        return empleados_json;
    }

    public JsonObject guardar_inventario() {

        JsonObject inventario_json = new JsonObject();

        inventario_json.addProperty(
            "id",
            inventario.get_id()
        );

        JsonArray productos_json = new JsonArray();

        for (Map.Entry<Producto, Map<Map<String, Object>, Integer>> producto_entry : inventario.get_productos().entrySet()) {

            JsonObject producto_json = new JsonObject();

            producto_json.addProperty(
                "producto_id",
                producto_entry.getKey().get_id()
            );

            JsonArray existencias_json = new JsonArray();

            for (Map.Entry<Map<String, Object>, Integer> existencia_entry : producto_entry.getValue().entrySet()) {

                JsonObject existencia_json = new JsonObject();

                existencia_json.add(
                    "variante",
                    GSON.toJsonTree(existencia_entry.getKey())
                );
                existencia_json.addProperty(
                    "existencias",
                    existencia_entry.getValue()
                );

                existencias_json.add(existencia_json);
            }

            producto_json.add(
                "existencias",
                existencias_json
            );

            productos_json.add(producto_json);
        }

        inventario_json.add(
            "productos",
            productos_json
        );

        return inventario_json;
    }

    public JsonArray guardar_producto() {

        JsonArray productos_json = new JsonArray();

        for (Producto producto : inventario.get_productos().keySet()) {

            JsonObject producto_json = new JsonObject();

            producto_json.addProperty(
                "id",
                producto.get_id()
            );
            producto_json.addProperty(
                "nombre",
                producto.get_nombre()
            );
            producto_json.addProperty(
                "descripcion",
                producto.get_descripcion()
            );

            JsonArray categorias_json = new JsonArray();

            for (String categoria : producto.get_categorias()) {
                categorias_json.add(categoria);
            }

            producto_json.add(
                "categorias",
                categorias_json
            );

            JsonArray variantes_json = new JsonArray();

            for (Map<String, Object> variante : producto.get_variantes()) {
                variantes_json.add(GSON.toJsonTree(variante));
            }

            producto_json.add(
                "variantes",
                variantes_json
            );

            producto_json.addProperty(
                "coste_compra",
                producto.get_coste_compra()
            );
            producto_json.addProperty(
                "precio_venta",
                producto.get_precio_venta()
            );

            JsonArray proveedores_json = new JsonArray();

            for (Proveedor proveedor : producto.get_proveedores()) {
                proveedores_json.add(proveedor.get_id());
            }

            producto_json.add(
                "proveedores",
                proveedores_json
            );

            producto_json.addProperty(
                "oculto",
                producto.get_oculto()
            );

            productos_json.add(producto_json);
        }

        return productos_json;
    }

    public JsonArray guardar_proveedor() {

        JsonArray proveedores_json = new JsonArray();

        for (Proveedor proveedor : proveedores) {

            JsonObject proveedor_json = new JsonObject();

            proveedor_json.addProperty(
                "id",
                proveedor.get_id()
            );
            proveedor_json.addProperty(
                "nombre",
                proveedor.get_nombre()
            );
            proveedor_json.addProperty(
                "contacto",
                proveedor.get_contacto()
            );
            proveedor_json.addProperty(
                "ubicacion",
                proveedor.get_ubicacion()
            );
            proveedor_json.addProperty(
                "oculto",
                proveedor.get_oculto()
            );

            proveedores_json.add(proveedor_json);
        }

        return proveedores_json;
    }

    public JsonArray guardar_venta() {

        JsonArray ventas_json = new JsonArray();

        for (Venta venta : ventas) {

            JsonObject venta_json = new JsonObject();

            venta_json.addProperty(
                "id",
                venta.get_id()
            );
            venta_json.addProperty(
                "fecha",
                venta.get_fecha()
            );
            venta_json.addProperty(
                "hora",
                venta.get_hora()
            );
            venta_json.addProperty(
                "metodo_pago",
                venta.get_metodo_pago()
            );
            venta_json.addProperty(
                "subtotal",
                venta.get_subtotal()
            );
            venta_json.addProperty(
                "total",
                venta.get_total()
            );

            JsonArray productos_venta_json = new JsonArray();

            for (DetalleVenta detalle : venta.get_productos_venta()) {
                productos_venta_json.add(detalle.get_id());
            }

            venta_json.add(
                "productos_venta",
                productos_venta_json
            );
            venta_json.add(
                "descuentos",
                GSON.toJsonTree(venta.get_descuentos())
            );
            venta_json.add(
                "impuestos",
                GSON.toJsonTree(venta.get_impuestos())
            );
            venta_json.addProperty(
                "oculto",
                venta.get_oculto()
            );

            ventas_json.add(venta_json);
        }

        return ventas_json;
    }

    public void cargar_datos() {

        File archivo = new File("datos.json");

        if (!archivo.exists()) {return;}

        try (FileReader reader = new FileReader(archivo)) {

            JsonObject datos = GSON.fromJson(reader, JsonObject.class);

            Map<Integer, Proveedor> proveedores_por_id = cargar_proveedor(datos.getAsJsonArray("Proveedor"));
            Map<Integer, Producto> productos_por_id = cargar_producto(datos.getAsJsonArray("Producto"), proveedores_por_id);
            Map<Integer, DetalleVenta> detalles_por_id = cargar_detalle_venta(datos.getAsJsonArray("DetalleVenta"), productos_por_id);

            proveedores.clear();
            proveedores.addAll(proveedores_por_id.values());

            empleados.clear();
            empleados.addAll(cargar_empleado(datos.getAsJsonArray("Empleado")));

            inventario = cargar_inventario(datos.getAsJsonArray("Inventario"), productos_por_id);
            administrador_financiero = cargar_administrador_financiero(datos.getAsJsonArray("AdministradorFinanciero"));

            ventas.clear();
            ventas.addAll(cargar_venta(datos.getAsJsonArray("Venta"), detalles_por_id));

            ids_globales = cargar_ids_globales(datos.getAsJsonObject("ids_globales"));

        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    public AdministradorFinanciero cargar_administrador_financiero(JsonArray administradores_json) {

        if (administradores_json == null || administradores_json.isEmpty()) {return null;}

        JsonObject administrador_json = administradores_json.get(0).getAsJsonObject();

        AdministradorFinanciero administrador_financiero = new AdministradorFinanciero(
            administrador_json.get("id").getAsInt(),
            administrador_json.get("costos_mantenimiento").getAsFloat(),
            administrador_json.get("servicios").getAsFloat(),
            administrador_json.get("otros_gastos").getAsFloat()
        );

        administrador_financiero.set_ingreso_total(administrador_json.get("ingresos_total").getAsFloat());

        return administrador_financiero;
    }

    public Map<Integer, DetalleVenta> cargar_detalle_venta(JsonArray detalles_json, Map<Integer, Producto> productos_por_id) {

        Map<Integer, DetalleVenta> detalles_cargados = new HashMap<>();

        if (detalles_json == null) {return detalles_cargados;}

        Type tipo_variante = new TypeToken<Map<String, Object>>() {}.getType();

        for (JsonElement elemento : detalles_json) {

            JsonObject detalle_json = elemento.getAsJsonObject();

            Producto producto = productos_por_id.get(
                detalle_json.get("producto_id").getAsInt()
            );

            if (producto == null) {continue;}

            Map<String, String> variante = GSON.fromJson(
                detalle_json.get("variante"),
                tipo_variante
            );

            DetalleVenta detalle = new DetalleVenta(
                detalle_json.get("id").getAsInt(),
                producto,
                variante,
                detalle_json.get("cantidad").getAsInt(),
                detalle_json.get("precio_unitario").getAsFloat()
            );

            detalles_cargados.put(detalle.get_id(), detalle);
        }

        return detalles_cargados;
    }

    public List<Empleado> cargar_empleado(JsonArray empleados_json) {

        List<Empleado> empleados_cargados = new ArrayList<>();

        if (empleados_json == null) {return empleados_cargados;}

        for (JsonElement elemento : empleados_json) {

            JsonObject empleado_json = elemento.getAsJsonObject();

            Empleado empleado = new Empleado(
                empleado_json.get("id").getAsInt(),
                empleado_json.get("nombre").getAsString(),
                empleado_json.get("tipo").getAsString(),
                empleado_json.get("cargo").getAsString(),
                empleado_json.get("salario").getAsFloat(),
                empleado_json.get("horario").getAsString()
            );

            empleado.set_oculto(empleado_json.get("oculto").getAsBoolean());

            empleados_cargados.add(empleado);
        }

        return empleados_cargados;
    }

    public Inventario cargar_inventario(JsonArray inventarios_json, Map<Integer, Producto> productos_por_id) {

        if (inventarios_json == null || inventarios_json.isEmpty()) {return null;}

        JsonObject inventario_json = inventarios_json.get(0).getAsJsonObject();

        Inventario inventario_cargado = new Inventario(
            inventario_json.get("id").getAsInt()
        );

        Type tipo_variante = new TypeToken<Map<String, Object>>() {}.getType();

        for (JsonElement elemento : inventario_json.getAsJsonArray("productos")) {

            JsonObject producto_json = elemento.getAsJsonObject();

            Producto producto = productos_por_id.get(
                producto_json.get("producto_id").getAsInt()
            );

            if (producto == null) {continue;}

            inventario_cargado.agregar_producto(producto);

            for (JsonElement existencia_elemento : producto_json.getAsJsonArray("existencias")) {

                JsonObject existencia_json = existencia_elemento.getAsJsonObject();

                Map<String, Object> variante = GSON.fromJson(
                    existencia_json.get("variante"),
                    tipo_variante
                );

                inventario_cargado.agregar_existencias(
                    producto,
                    variante,
                    existencia_json.get("existencias").getAsInt()
                );
            }
        }

        return inventario_cargado;
    }

    public Map<Integer, Producto> cargar_producto(JsonArray productos_json, Map<Integer, Proveedor> proveedores_por_id) {

        Map<Integer, Producto> productos_cargados = new HashMap<>();

        if (productos_json == null) {return productos_cargados;}

        Type tipo_categorias = new TypeToken<List<String>>() {}.getType();
        Type tipo_variantes = new TypeToken<List<Map<String, Object>>>() {}.getType();

        for (JsonElement elemento : productos_json) {

            JsonObject producto_json = elemento.getAsJsonObject();

            List<String> categorias = GSON.fromJson(
                producto_json.get("categorias"),
                tipo_categorias
            );

            List<Map<String, Object>> variantes = GSON.fromJson(
                producto_json.get("variantes"),
                tipo_variantes
            );

            List<Proveedor> proveedores_producto = new ArrayList<>();

            for (JsonElement proveedor_id_json : producto_json.getAsJsonArray("proveedores")) {

                int proveedor_id = proveedor_id_json.getAsInt();

                if (proveedores_por_id.containsKey(proveedor_id)) {
                    proveedores_producto.add(proveedores_por_id.get(proveedor_id));
                }
            }

            Producto producto = new Producto(
                producto_json.get("id").getAsInt(),
                producto_json.get("nombre").getAsString(),
                producto_json.get("descripcion").getAsString(),
                producto_json.get("coste_compra").getAsFloat(),
                producto_json.get("precio_venta").getAsFloat()
            );

            for (String categoria : categorias) {
                producto.anadir_categoria(categoria);
            }

            for (Map<String, Object> variante : variantes) {
                producto.agregar_variante(variante);
            }

            for (Proveedor proveedor : proveedores_producto) {
                producto.agregar_proveedor(proveedor);
            }

            producto.set_oculto(producto_json.get("oculto").getAsBoolean());

            productos_cargados.put(producto.get_id(), producto);
        }

        return productos_cargados;
    }

    public Map<Integer, Proveedor> cargar_proveedor(JsonArray proveedores_json) {

        Map<Integer, Proveedor> proveedores_cargados = new HashMap<>();

        if (proveedores_json == null) {return proveedores_cargados;}

        for (JsonElement elemento : proveedores_json) {

            JsonObject proveedor_json = elemento.getAsJsonObject();

            Proveedor proveedor = new Proveedor(
                proveedor_json.get("id").getAsInt(),
                proveedor_json.get("nombre").getAsString(),
                proveedor_json.get("contacto").getAsString(),
                proveedor_json.get("ubicacion").getAsString()
            );

            proveedor.set_oculto(proveedor_json.get("oculto").getAsBoolean());

            proveedores_cargados.put(proveedor.get_id(), proveedor);
        }

        return proveedores_cargados;
    }

    public List<Venta> cargar_venta(JsonArray ventas_json, Map<Integer, DetalleVenta> detalles_por_id) {

        List<Venta> ventas_cargadas = new ArrayList<>();

        if (ventas_json == null) {return ventas_cargadas;}

        Type tipo_mapa = new TypeToken<Map<String, Float>>() {}.getType();

        for (JsonElement elemento : ventas_json) {

            JsonObject venta_json = elemento.getAsJsonObject();

            List<DetalleVenta> productos_venta = new ArrayList<>();

            for (JsonElement detalle_id_json : venta_json.getAsJsonArray("productos_venta")) {

                int detalle_id = detalle_id_json.getAsInt();

                if (detalles_por_id.containsKey(detalle_id)) {
                    productos_venta.add(detalles_por_id.get(detalle_id));
                }
            }

            Map<String, Float> descuentos = GSON.fromJson(
                venta_json.get("descuentos"),
                tipo_mapa
            );

            Map<String, Float> impuestos = GSON.fromJson(
                venta_json.get("impuestos"),
                tipo_mapa
            );

            Venta venta = new Venta(
                venta_json.get("id").getAsInt()
            );

            for (DetalleVenta detalle : productos_venta) {
                venta.agregar_productos(detalle);
            }

            for (Map<String, Float> descuento : descuentos) {
                venta.agregar_descuento(descuento_key, descuento_value);
            }

            for (Map<String, Float> impuesto : impuestos) {
                venta.agregar_impuesto(impuesto_key, impuesto_value);
            }

            venta.set_fecha(venta_json.get("fecha").getAsString());
            venta.set_hora(venta_json.get("hora").getAsString());
            venta.set_metodo_pago(venta_json.get("metodo_pago").getAsString());
            venta.calcular_subtotal();
            venta.calcular_total();
            
            venta.set_oculto(venta_json.get("oculto").getAsBoolean());

            ventas_cargadas.add(venta);
        }

        return ventas_cargadas;
    }

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
}