package prog2.model;

/**
 * Classe abstracta que representa una pàgina de la bitàcola de la central.
 * Cada pàgina està associada a un dia concret.
 *
 * Les subclasses implementaran el mètode toString() per mostrar la informació específica
 * (com pot ser una pàgina d’estat, d’incidències o econòmica).
 */
public abstract class PaginaBitacola {

    private int dia; // Dia al qual fa referència la pàgina

    /**
     * Constructor que inicialitza el dia de la pàgina.
     * @param dia El dia de la simulació associat a aquesta pàgina.
     */
    public PaginaBitacola(int dia){
        setDia(dia);
    }

    // Setter per assignar el dia
    public void setDia(int dia){
        this.dia = dia;
    }

    // Getter per obtenir el dia
    public int getDia(){
        return this.dia;
    }

    /**
     * Mètode abstracte per representar la pàgina en format de text.
     * Les subclasses han d’implementar-lo.
     */
    public abstract String toString();
}
