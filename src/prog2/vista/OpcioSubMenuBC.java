package prog2.vista;

public enum OpcioSubMenuBC {
    OBTENIR_INSERCIO("Obtenir inserció barres"),
    ESTABLIR_INSERCIO("Establir inserció barres"),
    SORTIR("Sortir");

    private final String descripcio;

    OpcioSubMenuBC(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public String toString() {
        return descripcio;
    }
}