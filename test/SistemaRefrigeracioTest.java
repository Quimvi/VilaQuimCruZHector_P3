import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.BombaRefrigerant;
import prog2.model.SistemaRefrigeracio;
import prog2.model.VariableUniforme;

import static org.junit.jupiter.api.Assertions.*;

class SistemaRefrigeracioTest {

    private SistemaRefrigeracio sistema;
    private BombaRefrigerant bomba1;
    private BombaRefrigerant bomba2;

    @BeforeEach
    void setUp() {
        sistema = new SistemaRefrigeracio();
        VariableUniforme vu = new VariableUniforme(2);
        VariableUniforme vu2 = new VariableUniforme(2);

        bomba1 = new BombaRefrigerant(vu, 1);
        bomba2 = new BombaRefrigerant(vu2, 2);

        sistema.afegirBomba(bomba1);
        sistema.afegirBomba(bomba2);
    }

    @Test
    void testInicialitzacioLlista() {
        assertEquals(2, sistema.getLlistaBomba().size());
    }

    @Test
    void testActiva() {
        sistema.activa();

        assertTrue(bomba1.getActivat());
        assertTrue(bomba2.getActivat());
    }

    @Test
    void testDesactiva() {
        sistema.activa(); // Primer activem
        sistema.desactiva();

        assertFalse(bomba1.getActivat());
        assertFalse(bomba2.getActivat());
    }

    @Test
    void testGetActivat() {
        assertFalse(sistema.getActivat());
        bomba2.activa();
        assertTrue(sistema.getActivat());
    }

    @Test
    void testGetCostOperatiu() {
        assertEquals(0, sistema.getCostOperatiu(), 0.01);
        bomba1.activa();
        bomba2.activa();
        assertEquals(260, sistema.getCostOperatiu(), 0.01);
    }

    @Test
    void testCalculaOutputAmbSuficientCapacitat() {
        bomba1.activa();
        bomba2.activa();
        float output = sistema.calculaOutput(250);
        assertEquals(250, output, 0.01);
    }

    @Test
    void testCalculaOutputAmbCapacitatLimitada() {
        bomba1.activa();
        bomba2.activa();
        float output = sistema.calculaOutput(500); // Capacitat total és 300
        assertEquals(500, output, 0.01);
    }

    @Test
    void testCalculaOutputSenseBombesActives() {
        float output = sistema.calculaOutput(100);
        assertEquals(0, output, 0.01);
    }
}
