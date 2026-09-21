public class Proveedor extends Entidad{
    private String nombre; 
    private String contacto; 
    private String ubicacion; 

    //constructor completo
    public Proveedor(int id, String nombre, String contacto, String ubicacion){
        super(id); 
        this.nombre = nombre; 
        this.contacto = contacto; 
        this.ubicacion = ubicacion; 
    }

    //Nombre 
    public String get_nombre() {
        return nombre; 
    }

    public void set_nombre(String nombre){
        this.nombre = nombre; 
    }

    //contacto 
    public String get_contacto(){
        return contacto; 
    }

    public void set_contacto(String contacto) {
        this.contacto = contacto; 
    }

    //ubicacion 
    public String get_ubicacion() {
        return ubicacion; 
    }

    public void set_ubicacion(String ubicacion) {
        this.ubicacion = ubicacion; 
    }
}