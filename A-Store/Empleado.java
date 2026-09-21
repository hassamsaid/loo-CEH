public class Empleado extends Entidad {
    //Atributos
    private String nombre;
    private String tipo;
    private String cargo;
    private float salario;
    private String horario;

    //constructor
    public Empleado(int id, String nombre, String tipo, String cargo, float salario, String horario) {
        super(id);
        this.nombre = nombre;
        this.tipo = tipo;
        this.salario = salario;
        this.horario = horario;

}
//getters y setters
public String get_nombre() {
    return nombre;
}
public void set_nombre(String nombre) {
    this.nombre = nombre;
}
public String get_tipo() {
    return tipo;
}
public void set_tipo(String tipo) {
    this.tipo = tipo;
}
public String get_cargo() {
    return cargo;
}
public void set_cargo(String cargo) {
    this.cargo = cargo;
}
public float get_salario() {
    return salario;
}
public void set_salario(float salario) {
    this.salario = salario;
}
public String get_horario() {
    return horario;
}
public void set_horario(String horario) {
    this.horario = horario;
}

//representacion en texto
@Override
public String toString() {
return "Empleado{" +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", horario='" + horario + '\'' +
                '}';
    }
}