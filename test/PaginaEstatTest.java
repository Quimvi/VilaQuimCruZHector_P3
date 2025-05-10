import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import prog2.model.PaginaEstat;

public class PaginaEstatTest {

    @Test
    public void testGettersAndSetters() {
        PaginaEstat pagina = new PaginaEstat(1, 75.5f, 200.0f, 150.0f, 180.0f, 950.0f);

        assertEquals(75.5f, pagina.getInsercioBarres(), 0.01);
        assertEquals(200.0f, pagina.getOutputReactor(), 0.01);
        assertEquals(150.0f, pagina.getOutputSistemaDeRefrigeracio(), 0.01);
        assertEquals(180.0f, pagina.getOutputGeneradorDeVapor(), 0.01);
        assertEquals(950.0f, pagina.getOutputTurbina(), 0.01);
    }
}