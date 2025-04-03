import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraTest {
    @Test
    void testGetDiasHastaFecha() {
        assertEquals(31,Calculadora.getDiasHastaFecha(31,1));
        assertEquals(59,Calculadora.getDiasHastaFecha(59,2));
        assertEquals(90,Calculadora.getDiasHastaFecha(90,3));
    }
}