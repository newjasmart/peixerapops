package cat.boscdelacoma.poo.peixeragame.utils;

import java.util.Random;

/**
 * Classe amb mètodes estàtics d'utilitats numèriques
 * 
 * @author Marc Nicolau
 */
public class NumberUtils {
    
    //<editor-fold defaultstate="collapsed" desc="ATRIBUTS">
    private static Random rnd = new Random();
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mètodes estàtics/de classe">

    public static int getNumberBetween(int start, int end) {
        return rnd.nextInt(start, end + 1);
    }
    //</editor-fold>
}
