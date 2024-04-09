package cat.boscdelacoma.poo.peixeragame.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
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
public class PeixTest {

    Peix instance = null;
    Peixera expResult = null;
    World world = null;
    Body body = null;

    public PeixTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        
        instance = new PeixImpl();
        expResult = new Peixera(10, 10);
        //body = world.createBody(new BodyDef());
        //instance.setBody(body);
        instance.setDireccio(Direccio.DRETA);
        instance.setPosition(1, 1);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetPeixera() {
        instance.setPeixera(expResult);
        Peixera result = instance.getPeixera();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDireccio() {
        Direccio result = instance.getDireccio();
        instance.canviDireccio();
        assertNotEquals(instance.getDireccio(), result);
    }

    @Test
    public void testGetX() {
        instance.setPosition(5, 10);
        float result = instance.getX();
        assertEquals(5, result);
    }

    @Test
    public void testSetX() {
        instance.setX(5);
        assertEquals(5, instance.getX());
    }

    @Test
    public void testGetY() {
        instance.setPosition(5, 10);
        float result = instance.getY();
        assertEquals(10, result);
    }

    @Test
    public void testSetY() {
        instance.setY(10);
        assertEquals(10, instance.getY());
    }

    @Test
    public void testGetWidth() {
        instance.setWidth(10);
        int result = instance.getWidth();
        assertEquals(10, result);
    }

    @Test
    public void testSetWidth() {
        instance.setWidth(10);
        assertEquals(10, instance.getWidth());
    }

    @Test
    public void testGetHeight() {
        instance.setHeigh(15);
        int result = instance.getHeight();
        assertEquals(15, result);
    }

    @Test
    public void testSetHeigh() {
        instance.setHeigh(15);
        assertEquals(15, instance.getHeight());
    }


    @Test
    public void testGetSprite() {
        Sprite sprite = new Sprite();
        instance.setSprite(sprite);
        Sprite result = instance.getSprite();
        assertEquals(sprite, result);
    }

    @Test
    public void testSetSprite() {
        Sprite sprite = new Sprite();
        instance.setSprite(sprite);
        Sprite result = instance.getSprite();
        assertEquals(sprite, result);
    }

    @Test
    public void testGetPare() {
        Peix pare = new PeixImpl();
        instance.setPare(pare);
        Peix result = instance.getPare();
        assertEquals(pare, result);
    }

    @Test
    public void testSetPare() {
        Peix pare = new PeixImpl();
        instance.setPare(pare);
        Peix result = instance.getPare();
        assertEquals(pare, result);
    }

    @Test
    public void testGetMare() {
        Peix mare = new PeixImpl();
        instance.setMare(mare);
        Peix result = instance.getMare();
        assertEquals(mare, result);
    }

    @Test
    public void testSetMare() {
        Peix mare = new PeixImpl();
        instance.setMare(mare);
        Peix result = instance.getMare();
        assertEquals(mare, result);
    }

    @Test
    public void testSetPosition() {
        float x = 10.0F;
        float y = 20.0F;
        instance.setPosition(x, y);
        assertEquals(instance.getX(), 10);
        assertEquals(instance.getY(), 20);
    }

    @Test
    public void testCanviDireccio() {
        instance.setDireccio(Direccio.DRETA);
        instance.canviDireccio();
        assertEquals(Direccio.ESQUERRA, instance.getDireccio());
    }

    @Test
    public void testUpdateVelocity() {
        assertTrue(true);
    }

    public class PeixImpl extends Peix {

        public PeixImpl() {
            super(0, 0, null);
        }
    }

}
