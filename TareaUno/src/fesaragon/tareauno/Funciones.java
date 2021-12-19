
package fesaragon.tareauno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ale
 */
public class Funciones {
    static int difVis;
    static int difSeg;
    static int promedioTwitt;
    static int promedioFace;
    
    public static ArrayList<DatosPresenciaRedes> leer(String ruta) throws FileNotFoundException, IOException { // 
        System.out.println("Los datos se han guardado dentro de un ArrayList para agregar datos en un futuro y se han separado en las siguientes columnas: ");
        ArrayList<DatosPresenciaRedes> datos = new ArrayList<>(); //arraylist de tipo DatosPresenciaRedes
        FileReader fr = null; //creamos la variable y no hace referencia a nada, fuera del try catch para que cuando queramos llamarla no 
        BufferedReader buff = null;   //nos saque error. (para que no se vuelva variable local)
        File directorio = new File(ruta); //creamos la variable de tipo file que guarde la ruta del archivo

        fr = new FileReader(directorio); //la variable hace referencia a la ruta del archivo
        buff = new BufferedReader(fr); //el contructor nos pide un obj de tipo FileReader
        String cad=null;
        int contador=0;
        while ((cad = buff.readLine()) != null) { //cad = a lo que tiene el buffer con readLine y cuando lleguemos a null, hemos llegado al final del archivo
            if(contador!=0) { //con readline leemos linea por linea y este if hara que se
                cad = cad.replace("\"", ""); //eliminar las comillas dobles del archivo csv
                cad = cad.replace(".", "");  //eliminar .
                cad = cad.replace("%", "");  //eliminar %
                String[] columnas=cad.split(","); //indicamos el separador 
                DatosPresenciaRedes datosRedes=new DatosPresenciaRedes(); //crear obj de la clase que contiene los atributos de el archvio csv
                datosRedes.setRedSocial(columnas[0]);  //almacenar el valor de la columna[0] en sus respectivas variables
                datosRedes.setConcepto(columnas[1]); 
                datosRedes.setAnio(Integer.parseInt(columnas[2])); //convertir la columna en pos[2] a int y asi...
                datosRedes.setEnero(Integer.parseInt(columnas[3])); 
                datosRedes.setFebrero(Integer.parseInt(columnas[4]));
                datosRedes.setMarzo(Integer.parseInt(columnas[5]));
                datosRedes.setAbril(Integer.parseInt(columnas[6]));
                datosRedes.setMayo(Integer.parseInt(columnas[7]));
                datosRedes.setJunio(Integer.parseInt(columnas[8]));
                datosRedes.setJulio(Integer.parseInt(columnas[9]));
                datosRedes.setAgosto(Integer.parseInt(columnas[10]));
                datosRedes.setSeptiembre(Integer.parseInt(columnas[11]));
                datosRedes.setOctubre(Integer.parseInt(columnas[12]));
                datosRedes.setNoviembre(Integer.parseInt(columnas[13]));
                datosRedes.setDiciembre(Integer.parseInt(columnas[14]));
                if(datosRedes.getEnero()==12226){ //en el primer mes (enero) del conteo de visualizaciones se tiene 12226 
                //este valor se lo restamos a el valor de los seguidores de junio (el ultimo mes) para obtener la diferencia 
                difVis=datosRedes.getJunio()-datosRedes.getEnero();
                }
                if(datosRedes.getEnero()==62404){ //en el primer mes (enero) del conteo de seguidores se tiene 62404
                //este valor se lo restamos a el valor de los seguidores de junio (el ultimo mes) para obtener la diferencia
                difSeg=datosRedes.getJunio()-datosRedes.getEnero();
                }//en el apartado de CRECIMIENTO a partir de enero hasta junio lo sumaremos y dividiremos entre 6
                if(datosRedes.getConcepto().equals("CRECIMIENTO (seguidores)")){ 
                promedioFace=(datosRedes.getEnero()+datosRedes.getFebrero()+datosRedes.getMarzo()+datosRedes.getAbril()+datosRedes.getMayo()+datosRedes.getJunio())/6;
                }
                if(datosRedes.getConcepto().equals("CRECIMIENTO DE FOLLOWERS")){ 
                promedioTwitt=(datosRedes.getEnero()+datosRedes.getFebrero()+datosRedes.getMarzo()+datosRedes.getAbril()+datosRedes.getMayo()+datosRedes.getJunio())/6;
                }
                datos.add(datosRedes); //add nos permite guardar los datos en el arraylist datos 
            }else{
                contador++;//el valor del contador simpre es 1
            }
        }
        return datos;
    }
    
    public void mostrar(String ruta){
        System.out.println("Los datos dentro del archivo son los siguientes: \n");
        FileReader fr = null; //creamos la variable y no hace referencia a nada, fuera del try catch para que cuando queramos llamarla no 
        BufferedReader buff = null;   //nos saque error. (para que no se vuelva variable local)
        try {
            //System.out.println("ruta actual: "+System.getProperty("user.dir"));
            File directorio = new File(ruta); //creamos la variable de tipo file que guarde la ruta del archivo
            fr = new FileReader(directorio); //la variable hace referencia a la ruta del archivo
            buff = new BufferedReader(fr); //el contructor nos pide un obj de tipo FileReader
            String cad;
            while((cad=buff.readLine())!=null) { //la cadena = a lo que tiene el buffer con readLine y cuando lleguemos a null, hemos llegado al final del archivo
                System.out.println(cad);
            }
        } catch (FileNotFoundException ex) { //excepción= no se encontro la ruta del archivo. y crea el objeto ex(puede llamarse como quiera de tipo fileNot...)
            ex.printStackTrace(); //llamamos al metodo .printStackTrace que hace imprimir el lugar en donde occurio el error-excepción
        } catch (IOException ex) {
            ex.printStackTrace(); //printStrack... de while de arriba
        } finally { //siempre se ejecuta encuentre o no alguna excepción
            try {
                fr.close(); //cierra el archivo y esta en otro try porque puede que no pueda cerrarlo
                buff.close(); 
            } catch (IOException ex) {  //la libreria de donde se encuentre el posible error 
                ex.printStackTrace();
            }
        }
        System.out.println("");
    }
    public void difvisualizaciones () {
        System.out.println("La diferencia de visualizaciones en Youtube entre los meses enero a junio es de: +"+difVis+" visualizaciones");
    }
    public void difSeguidores() {
        System.out.println("\nLa diferencia de seguidores en Twitter entre los meses enero a junio es de: +"+difSeg+" seguidores");
    }
    public void promedioSegFace () {
        System.out.println("El promedio de crecimiento de Facebook entre los meses enero a junio es de: "+promedioFace);
    }
    public void promedioFollowTwitt () {
        System.out.println("El promedio de crecimiento de Twitter entre los meses enero a junio es de: "+promedioTwitt);
    }
}