import org.junit.jupiter.api.Test;
import prog2.model.BombaRefrigerant;
import prog2.model.VariableUniforme;
import prog2.vista.CentralUBException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BombaRefrigerantTest {

    @Test
    void testConstructorAndGetters() throws CentralUBException {
        VariableUniforme vu = new VariableUniforme(2);
        BombaRefrigerant bomba = new BombaRefrigerant(vu, 1);
        assertEquals(1, bomba.getId());
        assertFalse(bomba.getActivat());
        assertFalse(bomba.getForaDeServei());
    }

    @Test
    void testSetIdValid() throws CentralUBException {
        VariableUniforme vu = new VariableUniforme(1);
        BombaRefrigerant bomba = new BombaRefrigerant(vu, 0);
        bomba.setId(3);
        assertEquals(3, bomba.getId());
    }

    @Test
    void testSetIdInvalid() {
        VariableUniforme vu = new VariableUniforme(1);
        assertThrows(CentralUBException.class, () -> new BombaRefrigerant(vu, 4));
    }

    @Test
    void testActivaDesactiva() throws CentralUBException {
        VariableUniforme vu = new VariableUniforme(1);
        BombaRefrigerant bomba = new BombaRefrigerant(vu, 0);
        bomba.activa();
        assertTrue(bomba.getActivat());

        bomba.desactiva();
        assertFalse(bomba.getActivat());
    }

    @Test
    void testActivaForaDeServei() {
        VariableUniforme vu = new VariableUniforme(0); // 0 % 4 == 0 → fora de servei si id = 0
        BombaRefrigerant bomba = new BombaRefrigerant(vu, 0);

        assertTrue(bomba.getForaDeServei());
        assertThrows(CentralUBException.class, bomba::activa);
    }

    @Test
    void testCapacitatICost() throws CentralUBException {
        VariableUniforme vu = new VariableUniforme(3);
        BombaRefrigerant bomba = new BombaRefrigerant(vu, 1);

        // Inici: desactivada
        assertEquals(0, bomba.getCapacitat());
        assertEquals(0, bomba.getCostOperatiu());

        // Activada
        bomba.activa();
        assertEquals(250, bomba.getCapacitat());
        assertEquals(130, bomba.getCostOperatiu());

        // Ara si la fem fallar
        VariableUniforme vu2 = new VariableUniforme(2);
        BombaRefrigerant bomba2 = new BombaRefrigerant(vu2, 2);
        bomba2.activa();
        assertEquals(250, bomba2.getCapacitat());
        assertEquals(130, bomba2.getCostOperatiu());
    }

    @Test
    void testToString() {
        VariableUniforme vu = new VariableUniforme(2);
        BombaRefrigerant bomba = new BombaRefrigerant(vu, 2);
        String text = bomba.toString();
        assertTrue(text.contains("Id=2"));
        assertTrue(text.contains("Activat=false"));
        assertTrue(text.contains("Fora de servei=false"));
    }
}
