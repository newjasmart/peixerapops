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

    public void setPare(Peix peixA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setMare(Peix peixA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getBody() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getDireccio() {
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

    void setDireccio(Direccio direccio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setDireccio(Direccio direccio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setPosition(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setPeixera(Peixera expResult) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public enum Sex {
        MALE, FEMALE
    }
    
    private int x;
    private int y;
    private Sex sex;
    private int salut;
    private int direcció;
    
    public Peix (int x,int y,Sex sex, int direcció) {
        this.x = x;
        this.y = y;
        this.sex = sex;
        this.direcció = direcció;
    }
    
    public void nedar() {
        if (x == 0 && direcció == 1) {
            direcció = -1;
        } else if (x == 10 && direcció == -1) {
            direcció = 1;
        }

        x += direcció;
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
    
     public void mostrar() {
        System.out.print("Peix de sexe " + sex + " a (" + x + ", " + y + "), direcció: " + direcció);
    }
    
    public static void main(String[] args) {
        Peix peixA = new Peix(0, 5,Peix.Sex.MALE, 1);
        Peix peixB = new Peix(10, 5,Peix.Sex.FEMALE, -1);

        for (int i = 0; i < 20; i++) {
            peixA.nedar();
            peixB.nedar();
            peixA.mostrar();
            peixB.mostrar();
            System.out.println();
        }
    }
    
}
