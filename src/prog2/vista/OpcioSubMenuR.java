package prog2.vista;

public enum OpcioSubMenuR {
    ACTIVAR_REACTOR("Activar reactor"),
    DESACTIVAR_REACTOR("Desactivar reactor"),
    MOSTRAR_ESTAT("Mostrar estat"),
    SORTIR("Sortir");

    private final String descripcio;

    OpcioSubMenuR(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public String toString() {
        return descripcio;
    }
}