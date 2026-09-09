import java.util.List;

class AdministracionFinanciera {

    // Atributos
    private int id;
    private float costos_mantenimiento;
    private float servicios;
    private float otros_gastos;
    private float ingreso_total;

    // Constructor
    public AdministracionFinanciera(int id, float costos_mantenimiento, float servicios, float otros_gastos) {
        this.id = id;
        this.costos_mantenimiento = costos_mantenimiento;
        this.servicios = servicios;
        this.otros_gastos = otros_gastos;
        this.ingreso_total = 0.0f; 
    }

    // Getters y Setters
    public int get_id() { return id; }
    public void set_id(int id) { this.id = id; }

    public float get_costos_mantenimiento() { return costos_mantenimiento; }
    public void set_costos_mantenimiento(float costos_mantenimiento) { this.costos_mantenimiento = costos_mantenimiento; }

    public float get_servicios() { return servicios; }
    public void set_servicios(float servicios) { this.servicios = servicios; }

    public float get_otros_gastos() { return otros_gastos; }
    public void set_otros_gastos(float otros_gastos) { this.otros_gastos = otros_gastos; }

    public float get_ingreso_total() { return ingreso_total; }
    public void set_ingreso_total(float ingreso_total) { this.ingreso_total = ingreso_total; }


    // Métodos de cálculo 
    private float obtener_salario_empleado(Empleado emp) {
        if (emp == null) {
            return 0.0f;
        }

        try {
            return ((Number) emp.getClass().getMethod("get_salario").invoke(emp)).floatValue();
        } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            try {
                return ((Number) emp.getClass().getMethod("getSalario").invoke(emp)).floatValue();
            } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e2) {
                return 0.0f;
            }
        }
    }

    public float calcular_total_nomina(List<Empleado> empleados) {
        float total = 0.0f;
        if (empleados != null) {
            for (Empleado emp : empleados) {
                total += obtener_salario_empleado(emp);
            }
        }
        return total;
    }

    public float calcular_ingresos(List<Venta> ventas) {
        float total = 0.0f;
        if (ventas != null) {
            for (Venta venta : ventas) {
                total += venta.get_total();
            }
        }
        this.ingreso_total = total;
        return total;
    }

    public float calcular_gastos(List<Empleado> empleados) {
        return costos_mantenimiento + servicios + otros_gastos + calcular_total_nomina(empleados);
    }

    public float calcular_utilidad(List<Venta> ventas, List<Empleado> empleados) {
        return calcular_ingresos(ventas) - calcular_gastos(empleados);
    }

    public static class Venta {
        private float total;

        public Venta(float total) {
            this.total = total;
        }

        public float get_total() {
            return total;
        }

        public void set_total(float total) {
            this.total = total;
        }
    }
}