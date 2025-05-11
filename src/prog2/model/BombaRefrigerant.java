package prog2.model;

// Importació d’una excepció personalitzada per a la gestió d'errors
import prog2.vista.CentralUBException;

// Classe que representa una bomba refrigerant i implementa la interfície InBombaRefrigerant
public class BombaRefrigerant implements InBombaRefrigerant {

    // Identificador de la bomba (ha d’estar entre 0 i 3)
    private int id;

    // Estat d’activació de la bomba (true si està activada)
    private boolean activat;

    // Indica si la bomba està fora de servei (true si no funciona)
    private boolean foraDeServei = false;

    // Valor enter obtingut d’un generador de valors aleatoris o seqüencials (VariableUniforme)
    private int variableUniforme;

    // Constructor que rep una VariableUniforme i un id
    public BombaRefrigerant(VariableUniforme variableUniforme, int id) {
        // Obté el següent valor de la variable
        this.variableUniforme = variableUniforme.seguentValor();
        // Assigna l’id validant-lo
        setId(id);
    }

    // Setter per a l'id, amb validació. Només permet valors entre 0 i 3
    public void setId(int id) throws CentralUBException {
        if (id > 3 || id < 0)
            throw new CentralUBException("El Id ha de ser de 0-3");
        else
            this.id = id;
    }

    // Getter per l’id
    public int getId() {
        return this.id;
    }

    // Activa la bomba, excepte si està fora de servei
    public void activa() throws CentralUBException {
        if (this.foraDeServei)
            throw new CentralUBException("La bomba no pot ser activada, es troba fora de servei.");
        else
            this.activat = true;
    }

    // Desactiva la bomba
    public void desactiva() {
        this.activat = false;
    }

    // Indica si la bomba està activada
    public boolean getActivat() {
        return this.activat;
    }

    // Revisa l’estat de la bomba. Si `variableUniforme % 4 == id`, la posa fora de servei.
    // En tots els casos, afegeix una incidència a la pàgina rebuda com a paràmetre.
    public void revisa(PaginaIncidencies p) {
        if (variableUniforme % 4 == id) {
            this.foraDeServei = true;
            p.afegeixIncidencia("La bomba refrig. " + id + " està fora de servei");
        } else {
            p.afegeixIncidencia("La bomba refrig. " + id + " està en servei");
        }
    }

    // Retorna si la bomba està fora de servei
    public boolean getForaDeServei() {
        return this.foraDeServei;
    }

    // Retorna la capacitat de la bomba: 250 si està activada i en servei, sinó 0
    public float getCapacitat() {
        if (!getActivat() || getForaDeServei())
            return 0;
        else
            return 250;
    }

    // Retorna el cost operatiu de la bomba: 130 si està activada i en servei, sinó 0
    public float getCostOperatiu() {
        if (!getActivat() || getForaDeServei())
            return 0;
        else
            return 130;
    }

    // Retorna una representació textual de l’estat de la bomba
    public String toString() {
        return "Id=" + getId() + ", Activat=" + getActivat() + ", Fora de servei=" + getForaDeServei();
    }
}
