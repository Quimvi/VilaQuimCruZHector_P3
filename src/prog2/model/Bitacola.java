package prog2.model;

// Imports necessaris per utilitzar llistes i iteradors
import prog2.vista.CentralUBException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Definició de la classe Bitacola que implementa la interfície InBitacola
public class Bitacola implements InBitacola {

    // Llista on es guarden les pàgines de la bitàcola
    private ArrayList<PaginaBitacola> paginesBitacola;

    // Constructor per defecte: inicialitza la llista buida
    public Bitacola() {
        paginesBitacola = new ArrayList<PaginaBitacola>();
    }

    // Mètode per afegir una pàgina (de tipus PaginaBitacola) a la bitàcola
    public void afegeixPagina(PaginaBitacola p) throws CentralUBException {
        if (p == null) {
            throw new CentralUBException("La pàgina no pot ser null");
        }
        paginesBitacola.add(p);
    }

    // Mètode que retorna una llista amb només les pàgines que són instàncies de PaginaIncidencies
    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> llista = new ArrayList<>();
        Iterator<PaginaBitacola> itr = paginesBitacola.iterator();

        // Recorre totes les pàgines de la bitàcola
        while (itr.hasNext()) {
            PaginaBitacola pagina = itr.next();
            // Comprova si la pàgina és una instància de PaginaIncidencies
            if (pagina instanceof PaginaIncidencies) {
                // Es fa el càsting i s’afegeix a la llista de retorn
                PaginaIncidencies paginaIncidencies = (PaginaIncidencies) pagina;
                llista.add(paginaIncidencies);
            }
        }
        return llista;
    }

    // Mètode que retorna una representació en forma de String de les tres primeres pàgines de la bitàcola
    public String toString() {
        return paginesBitacola.get(0).toString() + "\n" +
                paginesBitacola.get(1).toString() + "\n" +
                paginesBitacola.get(2).toString();
    }
}
