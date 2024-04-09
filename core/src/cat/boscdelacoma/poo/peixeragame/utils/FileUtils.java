package cat.boscdelacoma.poo.peixeragame.utils;

import java.io.File;

/**
 * Classe d'utilitats pel sistema de fitxers.
 *
 * @author Marc
 */
public class FileUtils {

    /**
     * Obté el nom d'un fitxer sense l'extensió
     *
     * @param fullFilename el nom complet del fitxer (futa absoluta)
     * @return el nom del fitxer sense path ni externsió.
     */
    public static String getFileNameWithoutExtension(String fullFilename) {
        final String fileName = new File(fullFilename).getName();
        final int dotIndex = fileName.lastIndexOf('.');
        final String fileNameWithoutExtension;
        if (dotIndex == -1) {
            fileNameWithoutExtension = fileName;
        } else {
            fileNameWithoutExtension = fileName.substring(0, dotIndex);
        }
        return fileNameWithoutExtension;
    }
}
