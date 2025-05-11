package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;
import java.util.Iterator;

public class SistemaRefrigeracio implements InComponent {

    private ArrayList<BombaRefrigerant> llistaBomba;
    private float costOperatiu;

    /**
     * Constructor: inicialitza la llista de bombes.
     */
    public SistemaRefrigeracio(){
        llistaBomba = new ArrayList<BombaRefrigerant>();
    }

    /**
     * Afegeix una bomba refrigerant al sistema.
     */
    public void afegirBomba(BombaRefrigerant b) throws CentralUBException {
        if (b == null) {
            throw new CentralUBException("La bomba no pot ser null");
        }
        llistaBomba.add(b);
    }

    /**
     * Retorna la llista de bombes refrigerants.
     */
    public ArrayList<BombaRefrigerant> getLlistaBomba() {
        return llistaBomba;
    }

    /**
     * Activa totes les bombes que no estan fora de servei.
     */
    public void activa() {
        for (BombaRefrigerant bomba : llistaBomba) {
            if (!bomba.getForaDeServei()) {
                bomba.activa();
            }
        }
    }

    /**
     * Desactiva totes les bombes que no estan fora de servei.
     */
    public void desactiva(){
        for (BombaRefrigerant bomba : llistaBomba) {
            if (!bomba.getForaDeServei()) {
                bomba.desactiva();
            }
        }
    }

    /**
     * Retorna si almenys una bomba està activada.
     */
    public boolean getActivat() {
        for (BombaRefrigerant bomba : llistaBomba) {
            if (bomba.getActivat()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Revisa totes les bombes. Si alguna està desactivada o fora de servei,
     * se'n registra una incidència.
     */
    public void revisa(PaginaIncidencies p){
        for (BombaRefrigerant bomba : llistaBomba) {
            if (bomba.getForaDeServei() || !bomba.getActivat()) {
                bomba.revisa(p);
            }
        }
    }

    /**
     * Calcula el cost operatiu de totes les bombes activades.
     */
    public float getCostOperatiu(){
        float cost = 0;
        for (BombaRefrigerant bomba : llistaBomba) {
            if (bomba.getActivat()) {
                cost += bomba.getCostOperatiu();
            }
        }
        return cost;
    }

    public void setCostOperatiu(float costOperatiu) {
        this.costOperatiu = costOperatiu;
    }

    /**
     * Retorna la potència refrigerada pel sistema.
     * - Si la potència d'entrada és menor o igual a la suma de capacitats de bombes activades, retorna l'entrada.
     * - Si no, retorna la capacitat total disponible.
     */
    public float calculaOutput(float input){
        float capacitatTotal = 0;
        for (BombaRefrigerant bomba : llistaBomba) {
            if (bomba.getActivat()) {
                capacitatTotal += bomba.getCapacitat();
            }
        }
        return Math.min(input, capacitatTotal);
    }
}
