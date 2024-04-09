package cat.boscdelacoma.poo.peixeragame.physics;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Classe que implementa una parella de peixos.
 * 
 * @author Marc Nicolau
 */
public class Parella {
    
    //<editor-fold defaultstate="collapsed" desc="ATRIBUTS">
    private Body peixA, peixB;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="COMPORTAMENT">
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Parella(Body peixA, Body peixB) {
        this.peixA = peixA;
        this.peixB = peixB;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Body getPeixA() {
        return peixA;
    }
    
    public void setPeixA(Body peixA) {
        this.peixA = peixA;
    }
    
    public Body getPeixB() {
        return peixB;
    }
    
    public void setPeixB(Body peixB) {
        this.peixB = peixB;
    }
    //</editor-fold>
    
    //</editor-fold>    
}
