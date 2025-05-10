import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import prog2.model.PaginaIncidencies;

public class PaginaIncidenciesTest {

    @Test
    public void testAfegirIncidenciaIToString() {
        PaginaIncidencies pagina = new PaginaIncidencies(1);
        pagina.afegeixIncidencia("Falla en el reactor");
        pagina.afegeixIncidencia("Bomba 2 desactivada");

        String esperat = "# Pàgina Incidències \n- Dia: 1\n- Descripció Incidència: Falla en el reactor\n- Descripció Incidència: Bomba 2 desactivada\n";
        assertEquals(esperat.trim(), pagina.toString().trim());
    }
}