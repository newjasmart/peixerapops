package cat.boscdelacoma.poo.peixeragame.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TimOliver
 */
public class Peixera {
    
    private int amplada;
    private int alt;
    private List<Peix> peixos;

    public Peixera(int amplada, int alt) {
        this.amplada = amplada;
        this.alt = alt;
        this.peixos = new ArrayList<>();
    }

    public void afegir(Peix peix) {
        this.peixos.add(peix);
    }

    public void afegir(List<Peix> peixosExtra) {
        this.peixos.addAll(peixosExtra);
    }
    
    public int getAmplada() {
        return this.amplada;
    }

    public int getAlt() {
        return this.alt;
    }

    public List<Peix> getPeixos() {
        return this.peixos;
    }

    public void esborrarPeix(Peix peix) {
        this.peixos.remove(peix);
    }   
}
