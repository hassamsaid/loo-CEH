public class Empleado {
    //Atributos
    private int id;
    private String nombre;
    private String tipo;
    private String cargo;
    private float salario;
    private String horario;
    private final boolean activo;
    private final boolean oculto;

    //constructor
    public Empleado(String nombre, String tipo, String cargo, float salario, String horario) {
        this.id = 0; //aqui va generador de Ids"
        this.nombre = nombre;
        this.tipo = tipo;
        this.salario = salario;
        this.horario = horario;
        this.activo = true;
        this.oculto = false;

}
//getters y setters
public int get_id() {
    return id;
}
public void set_id(int id) {
    this.id = id;
}
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
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", horario='" + horario + '\'' +
                ", activo=" + activo +
                ", oculto=" + oculto +
                '}';
    }
}