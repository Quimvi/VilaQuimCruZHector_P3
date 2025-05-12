package prog2.model;

import prog2.vista.CentralUBException;

public class Reactor implements InComponent {
    private boolean activat;
    private float temperaturaReactor, temperatura;
    private float costOperatiu;

    /**
     * Constructor. Inicialitza la temperatura del reactor a 25ºC i el desactiva.
     */
    public Reactor() {
        setTemperaturaReactor(25);
        desactiva();
    }

    public void setTemperaturaReactor(float temperaturaReactor) throws CentralUBException {
        if (temperaturaReactor < 0) {
            throw new CentralUBException("La temperatura no pot ser negativa");
        }
        this.temperaturaReactor = temperaturaReactor;
    }

    public float getTemperaturaReactor() {
        if (getActivat())
            return temperatura;
        else
            return temperaturaReactor;
    }

    public void activa() {
        this.activat = true;
    }

    public void desactiva() {
        this.activat = false;
    }

    public boolean getActivat() {
        return this.activat;
    }

    /**
     * Revisa l'estat del reactor i afegeix incidències si cal.
     * - Si no està activat, s’informa.
     * - Si la temperatura supera els 1000ºC, es desactiva automàticament i s'informa.
     */
    public void revisa(PaginaIncidencies p) {
        if (!getActivat()) {
            p.afegeixIncidencia("El reactor ha sigut desactivat");
        } else if (getTemperaturaReactor() > 1000) {
            desactiva();
            p.afegeixIncidencia("El reactor es va desactivar per superar la temperatura màxima");
        }
    }

    /**
     * Retorna el cost operatiu del reactor (35 si està actiu, 0 si no ho està).
     */
    public float getCostOperatiu() {
        return activat ? 35 : 0;
    }

    public void setCostOperatiu(float costOperatiu) {
        this.costOperatiu = costOperatiu;
    }

    /**
     * Calcula la nova temperatura del reactor en funció de la inserció de barres de control.
     * @param input Percentatge d'inserció de barres (com més alt, menys calor es genera).
     * @return La nova temperatura.
     */
    public float calculaOutput(float input) {
        if (!getActivat()) {
            return temperaturaReactor;
        } else {
            // Quan el reactor està actiu, la temperatura puja segons la inserció de barres
            temperatura = temperaturaReactor + (100 - input) * 10;
            return temperaturaReactor + (100 - input) * 10;
        }
    }

    public String toString() {
        return "Està activat : " + getActivat() + "\n"
                + "Temperatura reactor : " + getTemperaturaReactor() + "\n"
                + "Cost operatiu : " + getCostOperatiu();
    }
}
