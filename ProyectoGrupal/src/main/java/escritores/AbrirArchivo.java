package escritores;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AbrirArchivo {

	public static void abrir(String rutaArchivo) {
        try {
            // Obtiene el objeto Desktop
            Desktop desktop = Desktop.getDesktop();

            // Verifica si es compatible con la acción de abrir
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                // Abre el archivo con la aplicación predeterminada
                desktop.open(new File(rutaArchivo));
            } else {
                System.out.println("La acción de abrir no es compatible en este sistema.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
