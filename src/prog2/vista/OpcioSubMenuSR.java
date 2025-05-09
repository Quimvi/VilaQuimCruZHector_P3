package prog2.vista;

public enum OpcioSubMenuSR {
    ACTIVAR_TOTES_BOMBES("Activar totes les bombes"),
    DESACTIVAR_TOTES_BOMBES("Desactivar totes les bombes"),
    ACTIVAR_BOMBA("Activar bomba"),
    DESACTIVAR_BOMBA("Desactivar bomba"),
    MOSTRAR_ESTAT("Mostrar estat"),
    SORTIR("Sortir");

    private final String descripcio;

    OpcioSubMenuSR(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public String toString() {
        return descripcio;
    }
}