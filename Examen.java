public class Examen extends Empleado {

    public Examen() {
        this(0, "", "", "", 0.0f, "");
    }

    public Examen(int id, String nombre, String apPaterno, String apMaterno, float salario, String puesto) {
        super(id, nombre, apPaterno, apMaterno, salario, puesto);
    }

    
}
