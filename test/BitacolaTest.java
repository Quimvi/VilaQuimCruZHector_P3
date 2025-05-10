import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import prog2.model.Bitacola;
import prog2.model.PaginaEstat;
import prog2.model.PaginaIncidencies;
import prog2.model.PaginaEconomica;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;
import prog2.model.GeneradorVapor;
import prog2.model.Turbina;


public class BitacolaTest {
    @Test
    public void testAfegirPagina() {
        Bitacola bitacola = new Bitacola();
        PaginaIncidencies paginaInc = new PaginaIncidencies(1);
        PaginaEstat paginaEstat = new PaginaEstat(1, 80.0f, 200.0f, 150.0f, 180.0f, 950.0f);

        // Afegir pàgines i verificar la mida
        bitacola.afegeixPagina(paginaInc);
        Assertions.assertEquals(1, bitacola.getIncidencies().size());

        bitacola.afegeixPagina(paginaEstat);
        Assertions.assertEquals(1, bitacola.getIncidencies().size()); // Només la d'incidències compta
    }

    @Test
    public void testGetIncidencies() {
        Bitacola bitacola = new Bitacola();
        PaginaIncidencies paginaInc1 = new PaginaIncidencies(1);
        PaginaIncidencies paginaInc2 = new PaginaIncidencies(2);
        PaginaEstat paginaEstat = new PaginaEstat(1, 80.0f, 200.0f, 150.0f, 180.0f, 950.0f);

        bitacola.afegeixPagina(paginaInc1);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaInc2);

        // Verificar que només es retornen les 2 pàgines d'incidències
        Assertions.assertEquals(2, bitacola.getIncidencies().size());
        assertTrue(bitacola.getIncidencies().contains(paginaInc1));
        assertTrue(bitacola.getIncidencies().contains(paginaInc2));
    }

    @Test
    public void testToString() {
        Bitacola bitacola = new Bitacola();
        PaginaIncidencies paginaInc = new PaginaIncidencies(1);
        PaginaEstat paginaEstat = new PaginaEstat(1, 80.0f, 200.0f, 150.0f, 180.0f, 950.0f);
        PaginaEconomica paginaEco = new PaginaEconomica(1, 1000.0f, 1200.0f, 0.0f, 500.0f,
                new Reactor(), new SistemaRefrigeracio(), new GeneradorVapor(), new Turbina());

        // Afegir 3 pàgines
        bitacola.afegeixPagina(paginaInc);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaEco);

        // Verificar concatenació
        String expected = paginaInc.toString() + "\n" + paginaEstat.toString() + "\n" + paginaEco.toString();
        Assertions.assertEquals(expected, bitacola.toString());
    }
}