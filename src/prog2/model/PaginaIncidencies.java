package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;

public class PaginaIncidencies extends PaginaBitacola {
    // Llista on es guarden les descripcions de totes les incidències del dia
    private ArrayList<String> llistaIncidencies;

    /**
     * Constructor. Crea una pàgina d'incidències per al dia indicat
     * i inicialitza la llista d'incidències buida.
     */
    public PaginaIncidencies(int dia) {
        super(dia);
        llistaIncidencies = new ArrayList<String>();
    }

    /**
     * Afegeix una nova incidència a la llista.
     * @param descIncidencia Descripció de l'incidència a afegir.
     */
    public void afegeixIncidencia(String descIncidencia) throws CentralUBException {
        if (descIncidencia == null || descIncidencia.trim().isEmpty()) {
            throw new CentralUBException("La descripció de l'incidència no pot estar buida");
        }
        llistaIncidencies.add(descIncidencia);
    }

    /**
     * Retorna una representació en format text de totes les incidències
     * registrades per aquest dia.
     */
    @Override
    public String toString() {
        StringBuffer concatenador = new StringBuffer();
        concatenador.append("# Pàgina Incidències \n")
                .append("- Dia: ").append(this.getDia()).append("\n");

        // Recorre totes les incidències i les afegeix al text
        for (String incidencia : llistaIncidencies) {
            concatenador.append("- Descripció Incidència: ")
                    .append(incidencia).append("\n");
        }

        return concatenador.toString().trim(); // Elimina l’últim salt de línia
    }
}
