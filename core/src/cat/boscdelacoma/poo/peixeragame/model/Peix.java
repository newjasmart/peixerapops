/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.poo.peixeragame.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 * @author TimOliver
 */
public class Peix {
    private int x;
    private int y;
    private Sex sex;
    private int salut;
    private Direccio direccio;
    private Body body;
    private Sprite sprite;
    private Peixera peixera;

    public void setPare(Peix peixA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setMare(Peix peixA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Body getBody() {
        return body;
    }

    public Direccio getDireccio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setSprite(Sprite sprite) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setBody(Body body) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updateVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Peixera getPeixera() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setDireccio(Direccio direccio) {
        this.direccio = direccio;
                
    }

    public void setPosition(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setPeixera(Peixera expResult) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void canviDireccio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public enum Sex {
        MALE, FEMALE
    }
    
    
    public Peix (int x,int y,Sex sex, Direccio direccio) {
        this.x = x;
        this.y = y;
        this.sex = sex;
        this.direccio = direccio;
    }
    
    
    public void combatre(Peix other) {
        while (this.ésViu() && other.ésViu()) {
            if (this.sex == other.sex) {
                this.rebreDany(other.getDany());
                other.rebreDany(this.getDany());
            }
        }
    }
    
    public boolean ésViu() {
        return this.salut > 0;
    }
    
    public int getSalut() {
        return this.salut;
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
    
    
}
