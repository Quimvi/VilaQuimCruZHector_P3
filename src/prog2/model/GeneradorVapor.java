package prog2.model;

import prog2.vista.CentralUBException;

/**
 * Classe que representa el generador de vapor de la central.
 * Aquest component transforma la calor procedent del sistema
 * de refrigeració en vapor per moure la turbina.
 */
public class GeneradorVapor implements InComponent {

    private boolean activat;         // Estat d'activació del generador
    private float costOperatiu;      // Cost operatiu personalitzable

    // Constructor: per defecte es desactiva
    public GeneradorVapor() {
        desactiva();
    }

    // Activa el generador
    public void activa() {
        this.activat = true;
    }

    // Desactiva el generador
    public void desactiva() {
        this.activat = false;
    }

    // Retorna si el generador està activat o no
    public boolean getActivat() {
        return this.activat;
    }

    /**
     * Afegeix una incidència a la pàgina si el generador està desactivat.
     * @param p la pàgina d'incidències del dia
     */
    public void revisa(PaginaIncidencies p) {
        if (!getActivat()) {
            p.afegeixIncidencia("El generador de vapor està desactivat");
        }
    }

    /**
     * Retorna el cost operatiu. Només es cobra si el generador està activat.
     */
    public float getCostOperatiu() {
        if (!getActivat())
            return 0;
        else
            return 25;
    }

    // Permet modificar el cost operatiu
    public void setCostOperatiu(float costOperatiu) throws CentralUBException {
        if (costOperatiu < 0) {
            throw new CentralUBException("El cost operatiu no pot ser negatiu");
        }
        this.costOperatiu = costOperatiu;
    }

    /**
     * Calcula la sortida del generador en funció de la calor (input).
     * Si està desactivat, retorna un valor constant de 25.
     * Si està activat, transforma el 90% de l'energia d'entrada.
     * @param input l’energia tèrmica que rep
     * @return energia útil en forma de vapor
     */
    public float calculaOutput(float input) {
        if (!getActivat())
            return 25;
        else
            return input * 0.9f;
    }
}
