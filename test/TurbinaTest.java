import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Turbina;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

class TurbinaTest {

    private Turbina turbina;

    @BeforeEach
    void setUp() throws CentralUBException {
        turbina = new Turbina();
    }

    @Test
    void testInicialitzacioTurbinaDesactivada() {
        assertFalse(turbina.getActivat());
    }

    @Test
    void testActiva() throws CentralUBException {
        turbina.activa();
        assertTrue(turbina.getActivat());
    }

    @Test
    void testDesactiva() throws CentralUBException {
        turbina.activa();
        turbina.desactiva();
        assertFalse(turbina.getActivat());
    }

    @Test
    void testGetCostOperatiuDesactivada() {
        assertEquals(0, turbina.getCostOperatiu(), 0.01);
    }

    @Test
    void testGetCostOperatiuActivada() throws CentralUBException {
        turbina.activa();
        assertEquals(20, turbina.getCostOperatiu(), 0.01);
    }

    @Test
    void testCalculaOutputDesactivada() {
        float resultat = turbina.calculaOutput(150);
        assertEquals(0, resultat, 0.01);
    }

    @Test
    void testCalculaOutputActivadaEntradaBaixa() throws CentralUBException {
        turbina.activa();
        float resultat = turbina.calculaOutput(50); // Menor de 100
        assertEquals(0, resultat, 0.01);
    }

    @Test
    void testCalculaOutputActivadaEntradaSuficient() throws CentralUBException {
        turbina.activa();
        float resultat = turbina.calculaOutput(150); // 150 * 2 = 300
        assertEquals(300, resultat, 0.01);
    }
}
