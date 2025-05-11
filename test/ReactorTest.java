import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Reactor;

import static org.junit.jupiter.api.Assertions.*;

class ReactorTest {

    private Reactor reactor;

    @BeforeEach
    void setUp() {
        reactor = new Reactor();
    }

    @Test
    void testInicialitzacio() {
        assertFalse(reactor.getActivat());
        assertEquals(25, reactor.getTemperaturaReactor(), 0.001);
    }

    @Test
    void testActivaIDesactiva() {
        reactor.activa();
        assertTrue(reactor.getActivat());

        reactor.desactiva();
        assertFalse(reactor.getActivat());
    }

    @Test
    void testGetCostOperatiu() {
        assertEquals(0, reactor.getCostOperatiu());
        reactor.activa();
        assertEquals(35, reactor.getCostOperatiu());
    }

    @Test
    void testSetTemperaturaReactor() {
        reactor.setTemperaturaReactor(500);
        assertEquals(500, reactor.getTemperaturaReactor());
    }

    @Test
    void testSetCostOperatiu() {
        reactor.setCostOperatiu(99); // No afecta el getCostOperatiu()
    }

    @Test
    void testCalculaOutputDesactivat() {
        reactor.setTemperaturaReactor(100);
        float output = reactor.calculaOutput(50);
        assertEquals(100, output);
    }

    @Test
    void testCalculaOutputActivat() {
        reactor.activa();
        reactor.setTemperaturaReactor(200);
        float output = reactor.calculaOutput(50); // (100 - 50) * 10 = 500 + 200 = 700
        assertEquals(700, output, 0.001f);
    }
}
