package cat.boscdelacoma.poo.peixeragame.utils;

import cat.boscdelacoma.poo.peixeragame.model.Direccio;
import cat.boscdelacoma.poo.peixeragame.model.Peix;
import cat.boscdelacoma.poo.peixeragame.model.PeixFemella;
import cat.boscdelacoma.poo.peixeragame.model.PeixMascle;
import cat.boscdelacoma.poo.peixeragame.model.Peixera;
import cat.boscdelacoma.poo.peixeragame.model.Tauro;
import cat.boscdelacoma.poo.peixeragame.model.TauroFemella;
import cat.boscdelacoma.poo.peixeragame.model.TauroMascle;
import cat.boscdelacoma.poo.peixeragame.model.Pop;

/**
 * Classe de suport a la peixera
 *
 * @author Marc Nicolau
 */
public class PeixeraUtils {

    //<editor-fold defaultstate="collapsed" desc="Mêtodes estàtics / de classe">
    /**
     * Crea una quanitat de peixos a la peixera (50% de mascles i 50% femelles)
     *
     * @param peixera la peixera on es crearan els peixos
     * @param quantitat la quantitat de peixos a crear
     */
    public static void crearPeixos(Peixera peixera, int quantitat) {
        crearPeixosMascle(peixera, quantitat / 2);
        crearPeixosFemella(peixera, quantitat / 2);
    }
    
    
    //Crear pops
    
    public static void crearPops(Peixera peixera, int quantitat) {
        for (int i = 0; i < quantitat; i++) {
            peixera.afegir(crearPop(peixera));
        }
    }
    
    public static Peix crearPop(Peixera peixera) {
        return new Pop(NumberUtils.getNumberBetween(0, peixera.getAmplada()),
                NumberUtils.getNumberBetween(0, peixera.getAlt()),
                Direccio.values()[NumberUtils.getNumberBetween(0, Direccio.values().length - 1)],
                peixera);
    }

    /**
     * Crea una quanitat de peixos mascle a la peixera.
     *
     * @param peixera la peixera on es crearan els peixos
     * @param quantitat la quantitat de peixos a crear
     */
    public static void crearPeixosMascle(Peixera peixera, int quantitat) {
        for (int i = 0; i < quantitat; i++) {
            peixera.afegir(crearPeixMascle(peixera));
        }
    }

    /**
     * Crea una quanitat de peixos femella a la peixera.
     *
     * @param peixera la peixera on es crearan els peixos
     * @param quantitat la quantitat de peixos a crear
     */
    public static void crearPeixosFemella(Peixera peixera, int quantitat) {
        for (int i = 0; i < quantitat; i++) {
            peixera.afegir(crearPeixFemella(peixera));
        }
    }

    /**
     * Crea un peix femella a la peixera.
     *
     * @param peixera la peixera on es crea el peix
     */
    public static Peix crearPeixFemella(Peixera peixera) {
        return new PeixFemella(NumberUtils.getNumberBetween(0, peixera.getAmplada()),
                NumberUtils.getNumberBetween(0, peixera.getAlt()),
                Direccio.values()[NumberUtils.getNumberBetween(0, Direccio.values().length - 1)],
                peixera);
    }

    /**
     * Crea un peix mascle a la peixera.
     *
     * @param peixera la peixera on es crea el peix
     */
    public static Peix crearPeixMascle(Peixera peixera) {
        return new PeixMascle(NumberUtils.getNumberBetween(0, peixera.getAmplada()),
                NumberUtils.getNumberBetween(0, peixera.getAlt()),
                Direccio.values()[NumberUtils.getNumberBetween(0, Direccio.values().length - 1)],
                peixera);
    }

    /**
     * Crea un fill/a (aleatori) entre dos peixos.
     *
     * @param peixera la peixera on es crea el peix
     * @param peixA un dels peixos de la parella que crea el fill
     * @param peixB l'altre peix de la parella que crea el fill
     */
    public static Peix crearPeixFill(Peixera peixera, Peix peixA, Peix peixB) {
        Peix peixFill = null;

        if (NumberUtils.getNumberBetween(0, 1) == 0) {
            peixFill = crearPeixMascle(peixera);
        } else {
            peixFill = crearPeixFemella(peixera);
        }
        if (peixA instanceof PeixMascle) {
            peixFill.setPare(peixA);
        }
        if (peixA instanceof PeixFemella) {
            peixFill.setMare(peixA);
        }
        if (peixB instanceof PeixMascle) {
            peixFill.setPare(peixB);
        }
        if (peixB instanceof PeixFemella) {
            peixFill.setMare(peixB);
        }
        return peixFill;
    }

    /**
     * Crea una quantitat de taurons a la peixera (50% mascles i 50% femelles)
     *
     * @param peixera on es creen els taurons
     * @param quantitat la quanitat de taurons que es creen
     */
    public static void crearTaurons(Peixera peixera, int quantitat) {
        crearTauronsMascle(peixera, quantitat / 2);
        crearTauronsFemella(peixera, quantitat / 2);
    }

    /**
     * Crea una quanitat de taurons mascle a la peixera
     *
     * @param peixera la peixera on es creen els taurons
     * @param quantitat la quantitat de taurons que es creen
     */
    private static void crearTauronsMascle(Peixera peixera, int quantitat) {
        for (int i = 0; i < quantitat; i++) {
            peixera.afegir(crearTauroMascle(peixera));
        }
    }

    /**
     * Crea una quanitat de taurons famella a la peixera
     *
     * @param peixera la peixera on es creen els taurons
     * @param quantitat la quantitat de taurons que es creen
     */
    private static void crearTauronsFemella(Peixera peixera, int quantitat) {
        for (int i = 0; i < quantitat; i++) {
            peixera.afegir(crearTauroFemella(peixera));
        }
    }

    /**
     * Crea un tauró mascle a la peixera
     *
     * @param peixera la peixera on es crea el tauró
     */
    private static Tauro crearTauroMascle(Peixera peixera) {
        return new TauroMascle(NumberUtils.getNumberBetween(0, peixera.getAmplada()),
                NumberUtils.getNumberBetween(0, peixera.getAlt()),
                Direccio.values()[NumberUtils.getNumberBetween(0, Direccio.values().length - 1)],
                peixera);
    }

    /**
     * Crea un tauró famella a la peixera
     *
     * @param peixera la peixera on es crea el tauró
     */
    private static Tauro crearTauroFemella(Peixera peixera) {
        return new TauroFemella(NumberUtils.getNumberBetween(0, peixera.getAmplada()),
                NumberUtils.getNumberBetween(0, peixera.getAlt()),
                Direccio.values()[NumberUtils.getNumberBetween(0, Direccio.values().length - 1)],
                peixera);
    }

     /**
     * Crea un fill/a (aleatori) entre dos taurons.
     *
     * @param peixera la peixera on es crea el tauro
     * @param peixA un dels taurons de la parella que crea el fill
     * @param peixB l'altre tauro de la parella que crea el fill
     */   
    public static Tauro crearTauroFill(Peixera peixera, Peix tauroA, Peix tauroB) {
        Tauro tauroFill = null;

        if (NumberUtils.getNumberBetween(0, 1) == 0) {
            tauroFill = crearTauroMascle(peixera);
        } else {
            tauroFill = crearTauroFemella(peixera);
        }
        if (tauroA instanceof TauroMascle) {
            tauroFill.setPare(tauroA);
        }
        if (tauroA instanceof TauroFemella) {
            tauroFill.setMare(tauroA);
        }
        if (tauroB instanceof TauroMascle) {
            tauroFill.setPare(tauroB);
        }
        if (tauroB instanceof TauroFemella) {
            tauroFill.setMare(tauroB);
        }
        return tauroFill;
    }
    //</editor-fold>

}
