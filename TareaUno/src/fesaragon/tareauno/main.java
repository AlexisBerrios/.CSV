
package fesaragon.tareauno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ale
 */
public class main {
    public static void main(String[] args) {
        Funciones obj = new Funciones();
        try {
            String ruta = System.getProperty("user.dir")+"/presenciaredes.csv";    
            obj.mostrar(ruta);
            ArrayList<DatosPresenciaRedes> datos = Funciones.leer(ruta); 
           for(DatosPresenciaRedes dato : datos){
                System.out.println(dato.toString());
            }   
           obj.difSeguidores();
           obj.difvisualizaciones();
           obj.promedioSegFace();
           obj.promedioFollowTwitt();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
