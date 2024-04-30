package cat.boscdelacoma.poo.peixeraapp;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import cat.boscdelacoma.poo.peixeragame.view.PeixeraGame;
import javax.swing.JOptionPane;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

    public static void main(String[] arg) {
        int nPeixos, nTaurons, nPops = 0;
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Peixera Game");

        config.setWindowedMode(1024, 768);
        config.setResizable(false);

        try {
            nPeixos = Integer.parseInt(JOptionPane.showInputDialog(null, "Quants peixos vols crear?", "Peixera", JOptionPane.QUESTION_MESSAGE));
            if (nPeixos < 0 || nPeixos > 300) {
                JOptionPane.showMessageDialog(null, "El valor no és vàlid. No pot ser menor a 0 ni major a 300", "Error en l'entrada", JOptionPane.ERROR_MESSAGE);
            } else {
                nTaurons = Integer.parseInt(JOptionPane.showInputDialog(null, "Quants taurons vols crear?", "Peixera", JOptionPane.QUESTION_MESSAGE));
                if (nTaurons < 0 || nTaurons > 100) {
                    JOptionPane.showMessageDialog(null, "El valor no és vàlid. No pot ser menor a 0 ni major a 100", "Error en l'entrada", JOptionPane.ERROR_MESSAGE);
                } else {
                    nPops = Integer.parseInt(JOptionPane.showInputDialog(null, "Vols crear un pop?", "Peixera", JOptionPane.QUESTION_MESSAGE));
                    if (nPops < 0 || nPops > 1) {
                        JOptionPane.showMessageDialog(null, "El valor no és vàlid. No pot ser menor a 0 ni major a 1", "Error en l'entrada", JOptionPane.ERROR_MESSAGE);
                    } else {
                        new Lwjgl3Application(new PeixeraGame(nPeixos, nTaurons, nPops), config);
                    }
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El valor no és vàlid", "Error en l'entrada", JOptionPane.ERROR_MESSAGE);
        }

    }
}
