public abstract class Entidad {

    private final int id;
    private boolean oculto;

    public Entidad(int id) {
        this.id = id;
        this.oculto = false;
    }

    public int get_id() {
        return id;
    }

    public boolean get_oculto() {
        return oculto;
    }

    public void set_oculto(boolean oculto) {
        this.oculto = oculto;
    }
}