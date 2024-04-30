package cat.boscdelacoma.poo.peixeragame.physics;

import cat.boscdelacoma.poo.peixeragame.model.Peix;
import cat.boscdelacoma.poo.peixeragame.model.PeixFemella;
import cat.boscdelacoma.poo.peixeragame.model.Peixera;
import cat.boscdelacoma.poo.peixeragame.model.PeixMascle;
import cat.boscdelacoma.poo.peixeragame.model.Tauro;
import cat.boscdelacoma.poo.peixeragame.model.TauroFemella;
import cat.boscdelacoma.poo.peixeragame.model.TauroMascle;
import cat.boscdelacoma.poo.peixeragame.utils.PeixeraUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.codeandweb.physicseditor.PhysicsShapeCache;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que modela el comporament de la peixera.
 *
 * @author Marc
 */
public class PeixeraModel implements ContactListener {

    //<editor-fold defaultstate="collapsed" desc="CONSTANTS">
    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;
    public static final float SCALE = 0.05f;

    public static final float WORLD_WIDTH = 66.5f;
    public static final float WORLD_HEIGHT = 50;
    //</editor-fold>
    public static float VELOCITAT_PEIX = 5f; //Ajustar en cas d'Anar massa rapid o lent

    //<editor-fold defaultstate="collapsed" desc="ATRIBUTS">
    private final World world;
    private final OrthographicCamera camera;
    private final ExtendViewport viewport;
    private final PhysicsShapeCache physicsBodies;
    private float accumulator = 0;

    private final Peixera peixera;
    private final List<Peix> peixosMorts;
    private final List<Peix> peixosNous;
    private final Parelles parelles;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="COMPORTAMENT">
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public PeixeraModel(Peixera peixera) {
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(this);
        physicsBodies = new PhysicsShapeCache("physics.xml");
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        this.peixera = peixera;
        peixosMorts = new ArrayList<>();
        peixosNous = new ArrayList<>();
        parelles = new Parelles();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    
    /**
     * Obtenir la referència a l'objecte World
     * @return objecte World
     */
    public World getWorld() {
        return world;
    }

    /**
     * Obtenir la referència a l'objecte càmera
     * @return objecte càmera
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Obtenir referència a l'objecte ViewPort
     * @return objecte Viewport
     */
    public ExtendViewport getViewport() {
        return viewport;
    }

    /**
     * Obtenir la llista de peixos morts
     * @return llista amb els peixos morts
     */
    public List<Peix> getPeixosMorts() {
        return peixosMorts;
    }

    /**
     * Obtenir la llista amb els nous peixos que s'han de crear
     * @return llista amb els peixos nous que s'han de crear
     */
    public List<Peix> getPeixosNous() {
        return peixosNous;
    }

    /**
     * Obtenir la llista de parelles de peixos
     * @return llista amb les parelles de peixos
     */
    public Parelles getParelles() {
        return parelles;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="API pública de l'objecte">
    public void logicStep(float delta) {
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

    public Body createBody(String name, Peix peix) {
        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyType.DynamicBody;
        Body body = physicsBodies.createBody(name, world, bodyDef, SCALE, SCALE);
        body.setTransform(peix.getX(), peix.getY(), 0);
        body.setUserData(peix);

        return body;
    }

    /**
     * Creates a generic body
     *
     * @param shape Shape
     * @param type Type (Dynamic, Kinetic or Static)
     * @param x X position
     * @param y Y position
     * @return The body
     */
    public Body createBody(Shape shape, BodyDef.BodyType type, float x, float y) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.friction = 0.3f;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData("mur");

        fixtureDef.shape.dispose();

        return body;
    }

    /**
     * Creates a static wall
     *
     * @param width Width
     * @param height Height
     * @param x X position
     * @param y Y position
     * @return The body
     */
    public Body createWall(float width, float height, float x, float y) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        return createBody(shape, BodyDef.BodyType.StaticBody, x, y);
    }

    public void dispose() {
        world.dispose();
        physicsBodies.dispose();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implementació ContactListener">
    /**
     * * GESTIÓ DE COL·LISIONS
     *
     ***
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {
        Peix peixA = null, peixB = null;
        Object o1 = contact.getFixtureA().getBody().getUserData();
        Object o2 = contact.getFixtureB().getBody().getUserData();
        
        if (o1 instanceof Peix) {
            peixA = (Peix) contact.getFixtureA().getBody().getUserData();
            
        }
        if (o2 instanceof Peix) {
            peixB = (Peix) contact.getFixtureB().getBody().getUserData();
        }
        
        // SI UN PEIX ARRIBA A ALGUNA DE LES VORES DE LA PEIXERA
        if ((o1 instanceof String && o1.equals("mur"))
                || (o2 instanceof String && o2.equals("mur"))) {
            if (o1 instanceof Peix) {
                ((Peix) o1).canviDireccio();
            }
            if (o2 instanceof Peix) {
                ((Peix) o2).canviDireccio();
            }
        }
        
        // si dos peixos o taurons del mateix sexe es toquen -> es moren tots dos
        if (peixA != null && peixB != null
                && ((peixA instanceof PeixMascle && peixB instanceof PeixMascle)
                || (peixB instanceof PeixFemella && peixA instanceof PeixFemella) 
                || (peixA instanceof TauroMascle && peixB instanceof TauroMascle)
                || (peixB instanceof TauroFemella && peixA instanceof TauroFemella))) {
            
            if (peixosMorts.isEmpty()) {
                peixosMorts.add(peixB);
                peixosMorts.add(peixA);
                System.out.println("Tocat: s'ha mort el " + peixA.getClass().getSimpleName());
                System.out.println("Tocat: s'ha mort el peix" + peixA.getClass().getSimpleName());
                contact.setEnabled(false);
            }
        }
        
        // si dos peixos del mateix sexe es toquen -> es moren tots dos
        if (peixA != null && peixB != null) {
            
            if (peixA instanceof Tauro && peixB instanceof PeixMascle && peixosMorts.isEmpty()) {
                peixosMorts.add(peixB);
                System.out.println("Tauro es menja peix: s'ha mort el " + peixB.getClass().getSimpleName());
                
            } else if (peixB instanceof Tauro && peixA instanceof PeixMascle && peixosMorts.isEmpty()) {
                peixosMorts.add(peixA);
                System.out.println("Tauro es menja peix: s'ha mort el " + peixA.getClass().getSimpleName());
                
            } else if (peixA instanceof Tauro && peixB instanceof PeixFemella && peixosMorts.isEmpty()) {
                peixosMorts.add(peixB);
                System.out.println("Tauro es menja peix: s'ha mort el " + peixA.getClass().getSimpleName());
                
            } else if (peixB instanceof Tauro && peixA instanceof PeixFemella && peixosMorts.isEmpty()) {
                peixosMorts.add(peixA);
                System.out.println("Tauro es menja peix: s'ha mort el " + peixA.getClass().getSimpleName());
                
            }
            contact.setEnabled(false);
        }
    }
    
    @Override
    public void endContact(Contact contact) {
        
        Peix peixA = null, peixB = null;
        Object o1 = contact.getFixtureA().getBody().getUserData();
        Object o2 = contact.getFixtureB().getBody().getUserData();
        
        if (o1 instanceof Peix) {
            peixA = (Peix) contact.getFixtureA().getBody().getUserData();
            
        }
        if (o2 instanceof Peix) {
            peixB = (Peix) contact.getFixtureB().getBody().getUserData();
        }
        // si mascle i femella s'aparellen --> crear un peix nou
        if (peixA != null && peixB != null
                && ((peixA instanceof PeixMascle && peixB instanceof PeixFemella)
                || (peixB instanceof PeixFemella && peixA instanceof PeixMascle))) {
            if (peixosNous.isEmpty() && !parelles.contains(peixA.getBody()) && !parelles.contains(peixB.getBody())) {
                peixosNous.add(PeixeraUtils.crearPeixFill(peixA.getPeixera(), peixA, peixB));
                contact.setEnabled(false);
                System.out.println("S'ha creat un peixNou");
                parelles.add(peixA.getBody(), peixB.getBody());
            }
        }
        // si mascle i femella s'aparellen --> crear un tauró nou
        if (peixA != null && peixB != null
                && ((peixA instanceof TauroMascle && peixB instanceof TauroFemella)
                || (peixB instanceof TauroFemella && peixA instanceof TauroMascle))) {
            if (peixosNous.isEmpty() && !parelles.contains(peixA.getBody()) && !parelles.contains(peixB.getBody())) {
                peixosNous.add(PeixeraUtils.crearTauroFill(peixA.getPeixera(), peixA, peixB));
                contact.setEnabled(false);
                System.out.println("S'ha creat un tauroNou");
                parelles.add(peixA.getBody(), peixB.getBody());
            }
        }
    }
    
    
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        contact.setEnabled(false);
    }
    
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        
    }
    //</editor-fold>
    
    //</editor-fold>
}
