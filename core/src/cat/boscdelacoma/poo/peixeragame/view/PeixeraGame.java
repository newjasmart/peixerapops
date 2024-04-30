package cat.boscdelacoma.poo.peixeragame.view;

import cat.boscdelacoma.poo.peixeragame.utils.TextureFiles;
import cat.boscdelacoma.poo.peixeragame.physics.PeixeraModel;
import cat.boscdelacoma.poo.peixeragame.model.Peix;
import cat.boscdelacoma.poo.peixeragame.model.PeixFemella;
import cat.boscdelacoma.poo.peixeragame.model.Peixera;
import cat.boscdelacoma.poo.peixeragame.model.PeixMascle;
import cat.boscdelacoma.poo.peixeragame.model.Pop;
import cat.boscdelacoma.poo.peixeragame.model.TauroFemella;
import cat.boscdelacoma.poo.peixeragame.model.TauroMascle;
import cat.boscdelacoma.poo.peixeragame.utils.FileUtils;
import cat.boscdelacoma.poo.peixeragame.utils.PeixeraUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Joc que simula la vida d'una peixera
 *
 * @author Marc Nicolau
 */
public class PeixeraGame extends ApplicationAdapter {

    //<editor-fold defaultstate="collapsed" desc="ATRIBUTS">
    private int nPeixos;
    private SpriteBatch batch;
    private Peixera peixera;
    private PeixeraModel model;
    private Box2DDebugRenderer debugRenderer;
    private Texture background;
    private final int nTaurons;
    private final int nPops;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="COMPORTAMENT">
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Constructor
     * 
     * @param nPeixos quantitat de peixos que es volen crear
     * @param nTaurons quantitat de taurons que es volen crear
     * @param nPops quantitat de pops que es volen crear
     */
    public PeixeraGame(int nPeixos, int nTaurons, int nPops) {
        this.nPeixos = nPeixos;
        this.nTaurons = nTaurons;
        this.nPops = nPops;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementar ApplicationAdapter: Cicle de vida del joc">
    @Override
    public void create() {
        Box2D.init();
        peixera = new Peixera((int) PeixeraModel.WORLD_WIDTH, (int) PeixeraModel.WORLD_HEIGHT);
        PeixeraUtils.crearPeixos(peixera, nPeixos);
        PeixeraUtils.crearTaurons(peixera, nTaurons);
        PeixeraUtils.crearPops(peixera, nPops);
        
        model = new PeixeraModel(peixera);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        
        batch = new SpriteBatch();
        
        // Murs dels límits de la peixera
        model.createWall(PeixeraModel.WORLD_WIDTH, 0.1f, 0, 0);
        model.createWall(PeixeraModel.WORLD_WIDTH, 0.1f, 0, PeixeraModel.WORLD_HEIGHT);
        model.createWall(0.1f, PeixeraModel.WORLD_HEIGHT, 0, PeixeraModel.WORLD_HEIGHT);
        model.createWall(0.1f, PeixeraModel.WORLD_HEIGHT, PeixeraModel.WORLD_WIDTH, PeixeraModel.WORLD_HEIGHT);
        // fons de la peixera
        background = new Texture("background.jpg");
    }
    
    @Override
    public void render() {
        
        model.logicStep(Gdx.graphics.getDeltaTime());
        
        destroyBodies();
        
        peixera.afegir(model.getPeixosNous());
        model.getPeixosNous().clear();
        model.getParelles().clear();
        
        ScreenUtils.clear(Color.SKY);
        
        batch.begin();
        
        batch.disableBlending();
        batch.draw(background, 0, 0, model.getCamera().viewportWidth, model.getCamera().viewportHeight);
        batch.enableBlending();
        
        peixera.getPeixos().forEach(peix -> drawPeix(peix));
        
        batch.end();       
    }
    
    @Override
    public void resize(int width, int height) {
        model.getViewport().update(width, height, true);
        batch.setProjectionMatrix(model.getCamera().combined);
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        debugRenderer.dispose();
        peixera.getPeixos().clear();
        model.dispose();
        background.dispose();
    }
    //</editor-fold>
    
    /**
     * Dibuixa un peix
     *
     * @param peix El peix que es vol dibuixar
     */
    private void drawPeix(Peix peix) {
        Sprite sprite = null;
        
        // si encara no té Body (ni Sprite)
        if (peix.getBody() == null) {
            if (peix instanceof PeixMascle) {
                sprite = new Sprite(TextureFiles.PEIX_M_TEXTURE[peix.getDireccio().ordinal()]);
            } else if (peix instanceof PeixFemella) {
                sprite = new Sprite(TextureFiles.PEIX_F_TEXTURE[peix.getDireccio().ordinal()]);
            } else if (peix instanceof TauroMascle) {
                sprite = new Sprite(TextureFiles.TAURO_M_TEXTURE[peix.getDireccio().ordinal()]);
            } else if (peix instanceof TauroFemella) {
                sprite = new Sprite(TextureFiles.TAURO_F_TEXTURE[peix.getDireccio().ordinal()]);
            } else if (peix instanceof Pop) {
                sprite = new Sprite(TextureFiles.OCTOPUS_TEXTURE[peix.getDireccio().ordinal()]);
            }
            float width = sprite.getWidth() * PeixeraModel.SCALE;
            float height = sprite.getHeight() * PeixeraModel.SCALE;
            sprite.setSize(width, height);
            sprite.setOrigin(0, 0);
            peix.setSprite(sprite);
            
            String bodyName = FileUtils.getFileNameWithoutExtension(sprite.getTexture().getTextureData().toString());
            Body body = model.createBody(bodyName, peix);
            
            peix.setBody(body);
            peix.setSprite(sprite);
            
        }
        
        peix.updateVelocity();
        drawSprite(sprite, peix);
        
    }
    
    /**
     * Dibuixa un sprite
     *
     * @param sprite L'sprite que es vol dibuixar
     * @param x Coordenada x on es vol dibuixar
     * @param y Coordenada y on es vol dibuixar
     */
    private void drawSprite(Sprite sprite, Peix peix) {
        
        if (sprite == null) {
            sprite = peix.getSprite();
        }
        if(peix.getX() < peix.getWidth()) {
            peix.setX(peix.getWidth());
        }
        if(peix.getX() > peix.getX() + peixera.getAmplada()) {
            peix.setY(peixera.getAmplada() - peix.getWidth());
        }
        if(peix.getY() > peix.getY() + peixera.getAlt()) {
            peix.setY(peixera.getAlt() - peix.getHeight());
        }
        if(peix.getY() < peix.getHeight()) {
            peix.setY(peix.getHeight());
        }
        
        sprite.setPosition(peix.getX(), peix.getY());
        if (peix instanceof PeixMascle) {
            sprite.setTexture(TextureFiles.PEIX_M_TEXTURE[peix.getDireccio().ordinal()]);
        } else if (peix instanceof PeixFemella) {
            sprite.setTexture(TextureFiles.PEIX_F_TEXTURE[peix.getDireccio().ordinal()]);
        } else if (peix instanceof TauroMascle) {
            sprite.setTexture(TextureFiles.TAURO_M_TEXTURE[peix.getDireccio().ordinal()]);
        } else if (peix instanceof TauroFemella) {
            sprite.setTexture(TextureFiles.TAURO_F_TEXTURE[peix.getDireccio().ordinal()]);
        } else if (peix instanceof Pop) {
            sprite.setTexture(TextureFiles.OCTOPUS_TEXTURE[peix.getDireccio().ordinal()]);
        }
        sprite.draw(batch);
    }
    
    /**
     * Destrueix els bodies pendents de ser esborrats
     */
    private void destroyBodies() {
        Peix peix;
        while (!model.getPeixosMorts().isEmpty()) {
            peix = model.getPeixosMorts().get(model.getPeixosMorts().size() - 1);
            if (model.getWorld().getBodyCount() > 0) {
                model.getWorld().destroyBody(peix.getBody());
                peixera.esborrarPeix(peix);
                model.getPeixosMorts().remove(peix);
            }
        }
        if (!model.getPeixosMorts().isEmpty()) {
            model.getPeixosMorts().clear();
        }
    }
//</editor-fold>

}
