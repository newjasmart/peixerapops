/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.poo.peixeragame.model;

/**
 *
 * @author TimOliver
 */
public class Peix {
    public enum Sex {
        MALE, FEMALE
    }
    
    private int x;
    private int y;
    private Sex sex;
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
    
     public void mostrar() {
        System.out.print("Peix de sexe " + sex + " a (" + x + ", " + y + "), direcció: " + direcció);
    }
    
    public static void main(String[] args) {
        Peix peix1 = new Peix(0, 5,Peix.Sex.MALE, 1);
        Peix peix2 = new Peix(10, 5,Peix.Sex.FEMALE, -1);

        for (int i = 0; i < 20; i++) {
            peix1.nedar();
            peix2.nedar();
            peix1.mostrar();
            peix2.mostrar();
            System.out.println();
        }
    }
    
}
