import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.*;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class DadesTest {

    private Dades dades;

    @BeforeEach
    void setUp() {
        dades = new Dades();
    }

    @Test
    void testConstructorInicialitzaCorrectament() {
        assertEquals(100, dades.getInsercioBarres());
        assertNotNull(dades.mostraReactor());
        assertNotNull(dades.mostraSistemaRefrigeracio());
        assertNotNull(dades.mostraBitacola());
        assertEquals(0, dades.getGuanysAcumulats());
    }

    @Test
    void testSetInsercioBarresValid() throws CentralUBException {
        dades.setInsercioBarres(75);
        assertEquals(75, dades.getInsercioBarres());
    }

    @Test
    void testSetInsercioBarresInvalid() {
        assertThrows(CentralUBException.class, () -> dades.setInsercioBarres(110));
    }

    @Test
    void testActivaDesactivaReactor() throws CentralUBException {
        dades.activaReactor();
        assertTrue(dades.mostraReactor().getActivat());

        dades.desactivaReactor();
        assertFalse(dades.mostraReactor().getActivat());
    }

    @Test
    void testActivaBombaValida() throws CentralUBException {
        dades.activaBomba(1);
        assertTrue(dades.mostraSistemaRefrigeracio().getLlistaBomba().get(1).getActivat());
    }

    @Test
    void testActivaBombaInvalida() {
        assertThrows(CentralUBException.class, () -> dades.activaBomba(4));
    }

    @Test
    void testDesactivaBombaValida() throws CentralUBException {
        dades.activaBomba(2);
        dades.desactivaBomba(2);
        assertFalse(dades.mostraSistemaRefrigeracio().getLlistaBomba().get(2).getActivat());
    }

    @Test
    void testCalculaPotenciaSenseActivarReactor() {
        float potencia = dades.calculaPotencia();
        assertEquals(0.0f, potencia);
    }

    @Test
    void testMostraEstat() {
        PaginaEstat estat = dades.mostraEstat();
        assertEquals(1, estat.getDia());
        assertEquals(dades.getInsercioBarres(), estat.getInsercioBarres());
    }

    @Test
    void testMostraIncidenciesDespresFinalitzaDia() {
        dades.finalitzaDia(100.0f);
        assertFalse(dades.mostraIncidencies().isEmpty());
    }
}
