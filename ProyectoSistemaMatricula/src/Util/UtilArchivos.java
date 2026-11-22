package Util;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UtilArchivos {

    public static String seleccionarGuardarArchivo(String nombrePorDefecto, String extension) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new java.io.File(nombrePorDefecto + "." + extension));
        chooser.setFileFilter(new FileNameExtensionFilter(extension.toUpperCase() + " Files", extension));
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            if (!path.toLowerCase().endsWith("." + extension)) {
                path += "." + extension;
            }
            return path;
        }
        return null;
    }
}
