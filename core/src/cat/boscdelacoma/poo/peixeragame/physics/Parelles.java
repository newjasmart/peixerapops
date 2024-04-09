package cat.boscdelacoma.poo.peixeragame.physics;

import com.badlogic.gdx.physics.box2d.Body;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que modela una llista de parelles de peixos
 *
 * @author Marc Nicolau
 */
public class Parelles {

    //<editor-fold defaultstate="collapsed" desc="ATRIBUTS">
    private final List<Parella> parelles;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="COMPORTAMENT">
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructor
     */
    public Parelles() {
        parelles = new ArrayList<>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="API pública de l'objecte">
    /**
     * Afegir una parella a la llista
     * 
     * @param parella l'objecte Parella que es vol afegir a la llista
     */
    public void add(Parella parella) {
        parelles.add(parella);
    }
    
    /**
     * afegir una parella a partir dels respectius objectes Body
     * 
     * @param peixA objecte Body
     * @param peixB objecte Body
     */
    public void add(Body peixA, Body peixB) {
        parelles.add(new Parella(peixA, peixB));
    }
    
    /**
     * Indica si la llista de parelles és buida
     * 
     * @return true si és buida, false en cas contrari
     */
    public boolean isEmpty() {
        return parelles.isEmpty();
    }
    
    /**
     * Netejar la llista i eliminar tots els objectes que conté
     */
    public void clear() {
        parelles.clear();
    }
    
    /**
     * Indica si la llita conté un objecte Parella.
     * 
     * @param parella la parella que es vol comprovar si es troba dins la llista
     * @return true si ha trobat l'objecte, false en cas contrari.
     */
    public boolean contains(Parella parella) {
        return parelles.contains(parella);
    }
    
    /**
     * Indica si la llita conté un objecte Parella.
     * 
     * @param peix el peix que es vol comprovar si existeix dins la llista de parelles
     * @return true si ha trobat l'objecte, false en cas contrari.
     */
    public boolean contains(Body peix) {
        
        int i = 0;
        boolean find = false;
        
        while (i < parelles.size() && !find) {
            find = parelles.get(i).getPeixA().equals(peix)
                    || parelles.get(i).getPeixB().equals(peix);
            i++;
        }
        return find;
    }
    
    /**
     * Obtenir la parella d'una determinada posició
     * 
     * @param i la posició d'on es vol obtenir la parella.
     * @return la parella que ocupa la posició indicada.
     */
    public Parella get(int i) {
        return parelles.get(i);
    }
    
    /**
     * Obtenir la mida de la llita de parelles.
     * 
     * @return el valor de la mida.
     */
    public int size() {
        return parelles.size();
    }
    //</editor-fold>
    //</editor-fold>
}
