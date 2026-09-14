public class Proveedor {
    private final int id; 
    private String nombre; 
    private String contacto; 
    private String ubicacion; 
    private boolean oculto; 

    //constructor completp 
    public Proveedor(int id, String nombre, String contacto, String ubicacion){
        this.id = id; 
        this.nombre = nombre; 
        this.contacto = contacto; 
        this.ubicacion = ubicacion; 
        this.oculto = false; //no esta oculto por defecto
    }

    //constuctor simulando valores por defecto 
    public Proveedor(int id, String nombre){
        this.id = id; 
        this.nombre = nombre; 
        this.contacto = ""; 
        this.ubicacion = ""; 
        this.oculto = false; 
    } 

    //ID 
    public int get_id() {
        return id;
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

    //oculto 
    public boolean get_oculto() {
        return oculto; 
    }

    public void set_oculto(boolean oculto) {
        this.oculto = oculto; 
    }
}