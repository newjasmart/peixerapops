package cat.boscdelacoma.poo.peixeragame.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Classe que conté constants i mètodes estàtics de suport a les físiques del joc
 * 
 * @author Marc Nicolau
 */
public class PhysicsUtils {

    //<editor-fold defaultstate="collapsed" desc="CONSTANTS">

    public static final short MASCLE = 0x01;
    public static final short FEMELLA = 0x02;

    //</editor-fold>

  
    //<editor-fold defaultstate="collapsed" desc="Mètodes estàtics / de classe">
    
    /**
     * Mètode per desactivar la capacitat de contacte entre tipus d'objectes
     * 
     * @param body el cos del qual es vol desactivar els contactes
     */
    public static void desactivarFixtures(Body body) {
        if (body != null) {
            for (Fixture fix : body.getFixtureList()) {
                if (fix.getFilterData().categoryBits == MASCLE) {
                    fix.getFilterData().maskBits = 0xFF - FEMELLA;
                }
                if (fix.getFilterData().categoryBits == FEMELLA) {
                    fix.getFilterData().maskBits = 0xFF - MASCLE;
                }
            }
        }
    }
    //</editor-fold>
}
