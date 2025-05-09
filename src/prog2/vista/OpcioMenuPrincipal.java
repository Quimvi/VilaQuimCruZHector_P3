package prog2.vista;

public enum OpcioMenuPrincipal {
    GESTIO_BARRES_CONTROL("Gestió barres de control"),
    GESTIO_REACTOR("Gestió reactor"),
    GESTIO_SISTEMA_REFRIGERACIO("Gestió sistema refrigeració"),
    MOSTRAR_ESTAT_CENTRAL("Mostrar estat central"),
    MOSTRAR_BITACOLA("Mostrar bitàcola"),
    MOSTRAR_INCIDENCIES("Mostrar incidències"),
    OBTENIR_DEMANDA_SATISFETA("Obtenir demanda satisfeta amb configuració actual"),
    FINALITZAR_DIA("Finalitzar dia"),
    GUARDAR_DADES("Guardar dades"),
    CARREGAR_DADES("Carregar dades"),
    SORTIR("Sortir");

    private final String descripcio;

    OpcioMenuPrincipal(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public String toString() {
        return descripcio;
    }
}