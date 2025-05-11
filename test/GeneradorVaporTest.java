import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.GeneradorVapor;

import static org.junit.jupiter.api.Assertions.*;

class GeneradorVaporTest {

    private GeneradorVapor generador;

    @BeforeEach
    void setUp() {
        generador = new GeneradorVapor();
    }

    @Test
    void testInicialmentDesactivat() {
        assertFalse(generador.getActivat());
        assertEquals(0, generador.getCostOperatiu());
        assertEquals(25, generador.calculaOutput(100));
    }

    @Test
    void testActiva() {
        generador.activa();
        assertTrue(generador.getActivat());
    }

    @Test
    void testDesactiva() {
        generador.activa();
        generador.desactiva();
        assertFalse(generador.getActivat());
    }

    @Test
    void testGetCostOperatiu() {
        assertEquals(0, generador.getCostOperatiu());
        generador.activa();
        assertEquals(25, generador.getCostOperatiu());
    }

    @Test
    void testCalculaOutputActivat() {
        generador.activa();
        float input = 100;
        float expected = 90;
        assertEquals(expected, generador.calculaOutput(input), 0.001);
    }

    @Test
    void testCalculaOutputDesactivat() {
        float input = 100;
        assertEquals(25, generador.calculaOutput(input));
    }
}
