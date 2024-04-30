package cat.boscdelacoma.poo.peixeragame.model;

public class Pop extends Peix {
    
    public Pop(int x, int y, Direccio direccio, Peixera peixera) {
        super(x, y, direccio, peixera);
    }

    @Override
    public void canviDireccio() {
        switch (getDireccio()) {
            case AMUNT:
                break;
            case AVALL:
                break;
            case DRETA:
                setDireccio(Direccio.ESQUERRA);
                break;
            case ESQUERRA:
                setDireccio(Direccio.DRETA);
                break;
        }
    }
}