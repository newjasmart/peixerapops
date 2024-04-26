package cat.boscdelacoma.poo.peixeragame.model;

/**
 *
 * @author TimOliver
 */
public class PeixMascle extends Peix {

    public PeixMascle(int x, int y, Direccio direccio, Peixera peixera) {
        super(x, y, direccio, peixera);
    }
    
    public PeixMascle(int x, int y, Peixera peixera) {
        super(x, y, peixera);
    }
}