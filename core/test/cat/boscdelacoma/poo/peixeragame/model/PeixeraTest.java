/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cat.boscdelacoma.poo.peixeragame.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Marc Nicolau
 */
public class PeixeraTest {

    Peixera instance;
    final int WIDTH = 50;
    final int HEIGHT = 50;

    public PeixeraTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Peixera(WIDTH, HEIGHT);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAmplada() {
        int expResult = WIDTH;
        int result = instance.getAmplada();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAlt() {
        int expResult = HEIGHT;
        int result = instance.getAlt();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPeixos() {
        List<Peix> result = instance.getPeixos();
        assertEquals(new ArrayList<Peix>(), result);
    }

    @Test
    public void testAfegir_Peix() {
        Peix peix = new PeixMascle(5, 5, instance);
        int count = instance.getPeixos().size();
        instance.afegir(peix);
        assertEquals(count + 1, instance.getPeixos().size());
    }

    @Test
    public void testEsborrarPeix() {
        Peix peix = new PeixMascle(5, 5, instance);
        instance.afegir(peix);

        int count = instance.getPeixos().size();

        instance.esborrarPeix(peix);
        assertEquals(count-1, instance.getPeixos().size());
    }

}
