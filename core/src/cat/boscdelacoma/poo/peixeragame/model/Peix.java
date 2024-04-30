package cat.boscdelacoma.poo.peixeragame.model;

import cat.boscdelacoma.poo.peixeragame.physics.PeixeraModel;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 * @author TimOliver
 */
public class Peix {

    private float x;
    private float y;
    private Sex sex;
    private int salut;
    private Direccio direccio;
    private Body body;
    private Sprite sprite;
    private Peixera peixera;
    private Peix mare;
    private Peix pare;

    public Peix(int x, int y, Peixera peixera) {
        this.x = x;
        this.y = y;
        this.peixera = peixera;
    }

    public Peix(int x, int y, Direccio direccio, Peixera peixera) {
        this(x, y, peixera);
        this.salut = 100;
        this.direccio = direccio;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Direccio getDireccio() {
        return direccio;
    }

    public void setDireccio(Direccio direccio) {
        this.direccio = direccio;
    }

    public void updateVelocity() {
        //Update the velocity of the body based on the direction
        float vx = 0, vy = 0;
        switch (direccio) {
            case DRETA:
                if(getBody().getPosition().x + getSprite().getWidth() >= getPeixera().getAmplada() - 0.2f ) {
                    canviDireccio(); // Change direction when hitting the right wall
                } else {
                    vx = 1;
                }
                break;
            case ESQUERRA:
                if(getBody().getPosition().x <= 0.2f ) {
                    canviDireccio(); // Change direction when hitting the left wall
                } else {
                    vx = -1;
                }
                break;
            case AMUNT:
                if(getBody().getPosition().y + getSprite().getHeight() >= getPeixera().getAlt() - 0.2f ) {
                    canviDireccio(); // Change direction when hitting the top wall
                } else {
                    vy = 1;
                }
                break;
            case AVALL:
                if(getBody().getPosition().y <= 0.2f ) {
                    canviDireccio(); // Change direction when hitting the bottom wall
                } else {
                    vy = -1;
                }
                break;
        }

        body.setLinearVelocity(vx * PeixeraModel.VELOCITAT_PEIX, vy * PeixeraModel.VELOCITAT_PEIX);
    }

    public Peixera getPeixera() {
        return peixera;
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setPeixera(Peixera peixera) {
        this.peixera = peixera;
    }

    public void canviDireccio() {
        //Change the direction randomly
        switch (getDireccio()) {
            case AMUNT:
                setDireccio(Direccio.AVALL);
                break;
            case AVALL:
                setDireccio(Direccio.AMUNT);
                break;
            case DRETA:
                setDireccio(Direccio.ESQUERRA);
                break;
            case ESQUERRA:
                setDireccio(Direccio.DRETA);
                break;
        }

    }

    public float getX() {
        if(getBody() !=  null) {

            return getBody().getPosition().x;
        } else {
            return x;
        }
    }

    public void setX(float x) {
        this.x = x;
        if(getBody() !=  null) {
            getBody().getPosition().x = x;
            updateVelocity();
        }
    }

    public float getY() {
        if(getBody() !=  null) {

            return getBody().getPosition().y;
        } else {
            return y;
        }
    }

    public void setY(float y) {
        this.y = y;
        if(getBody() !=  null) {
            getBody().getPosition().y = y;
            updateVelocity();
        }
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public void setWidth(float width) {
        sprite.setSize(width, sprite.getHeight());
    }

    public short getHeight() {
        return (short) sprite.getHeight();
    }

    public void setHeigh(int height) {
        sprite.setSize(sprite.getWidth(), height);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public enum Sex {
        MALE, FEMALE
    }

    public Sex getSex() {
        return sex;
    }

    public Peix(int x, int y, Sex sex, Direccio direccio) {
        this.x = x;
        this.y = y;
        this.sex = sex;
        this.direccio = direccio;
    }

    public void combatre(Peix other) {
        while (this.esViu() && other.esViu()) {
            if (this.sex == other.sex) {
                this.rebreDany(other.getDany());
                other.rebreDany(this.getDany());
            }
        }
    }

    public boolean esViu() {
        return this.salut > 0;
    }

    public int getSalut() {
        return this.salut;
    }

    public void setSalut(int salut) {
        this.salut = Math.max(0, Math.min(salut, 10));
    }

    public void rebreDany(int dany) {
        this.salut -= dany;
        if (this.salut < 0) {
            this.salut = 0;
        }
    }

    public int getDany() {
        return 10;
    }

    //Falta implementar
    public void setPare(Peix peixA) {
        this.pare = pare;
    }

    public void setMare(Peix peixA) {
        this.mare = mare;
    }

    Peix getPare() {
        return this.pare;
    }

    Peix getMare() {
        return this.mare;
    }

}
