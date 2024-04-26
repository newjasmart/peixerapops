package cat.boscdelacoma.poo.peixeragame.model;

/**
 *
 * @author TimOliver
 */
public class Tauro extends Peix {

    public Tauro(int x, int y, Direccio direccio, Peixera peixera) {
        super(x, y, direccio, peixera);
    }

    @Override
    public void combatre(Peix other) {
        if (other instanceof PeixMascle) {
            PeixMascle otherMale = (PeixMascle) other;
            if (getSex() == otherMale.getSex()) {
                // Tauro eats PeixMascle of the same sex
                setSalut(getSalut() + otherMale.getSalut());
                getPeixera().esborrarPeix(otherMale);
                return;
            }
        } else if (other instanceof PeixFemella) {
            PeixFemella otherFemale = (PeixFemella) other;
            if (getSex() == otherFemale.getSex()) {
                // Tauro eats PeixFemella of the same sex
                setSalut(getSalut() + otherFemale.getSalut());
                getPeixera().esborrarPeix(otherFemale);
                return;
            }
        } else if (other instanceof Tauro) {
            Tauro otherTauro = (Tauro) other;
            if (getSex() == otherTauro.getSex()) {
                // Tauro eats Tauro of the same sex
                setSalut(getSalut() + otherTauro.getSalut());
                getPeixera().esborrarPeix(otherTauro);
                return;
            }
        }

        // If the Tauro can't eat the other Peix, it fights with it
        super.combatre(other);
    }
}