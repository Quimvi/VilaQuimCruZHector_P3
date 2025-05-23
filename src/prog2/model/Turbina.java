package prog2.model;

import prog2.vista.CentralUBException;

import java.io.Serializable;

public class Turbina implements InComponent, Serializable {
    private boolean activat;
    private float costOperatiu;

    /**
     * Constructor de la turbina. La turbina es desactiva inicialment.
     */
    public Turbina(){
        desactiva();
    }

    /**
     * Activa la turbina.
     * @throws CentralUBException Si cal gestionar alguna excepció relacionada amb l'activació, actualment no és necessària.
     */
    public void activa() throws CentralUBException {
        this.activat = true;
    }

    /**
     * Desactiva la turbina.
     * @throws CentralUBException Si cal gestionar alguna excepció relacionada amb la desactivació, actualment no és necessària.
     */
    public void desactiva() throws CentralUBException{
        this.activat = false;
    }

    /**
     * Retorna l'estat d'activació de la turbina.
     * @return true si està activada, false si està desactivada.
     */
    public boolean getActivat() {
        return this.activat;
    }

    /**
     * Revisa l'estat de la turbina i afegeix una incidència a la pàgina corresponent.
     */
    public void revisa(PaginaIncidencies p){

    }

    /**
     * Retorna el cost operatiu de la turbina, que és 0 si està desactivada.
     * @return el cost operatiu de la turbina.
     */
    public float getCostOperatiu(){
        return (getActivat()) ? 20 : 0;
    }

    /**
     * Estableix el cost operatiu de la turbina. Tot i que el cost operatiu és fix (20) quan està activada,
     * es pot modificar si es vol més flexibilitat.
     */
    public void setCostOperatiu(float costOperatiu) throws CentralUBException {
        if (costOperatiu < 0) {
            throw new CentralUBException("El cost operatiu no pot ser negatiu");
        }
        this.costOperatiu = costOperatiu;
    }

    /**
     * Calcula el resultat de la turbina basat en l'entrada de potència.
     * Si la turbina no està activada, el resultat és 0.
     * Si l'entrada és menor que 100, el resultat també és 0.
     * Si l'entrada és suficient, es calcula el resultat multiplicant per 2.
     * @param input la potència d'entrada.
     * @return la potència d'entrada processada per la turbina.
     */
    public float calculaOutput(float input){
        if (!getActivat())
            return 0;

        if (input < 100)
            return 0;

        return input * 2; // La turbina genera el doble de potència quan s'activa i té suficient entrada.
    }
}
