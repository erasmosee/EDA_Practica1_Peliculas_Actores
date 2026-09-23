package eda.practica1;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Lector {

private  static Lector instance;
public static Lector getInstance() {

  if (instance == null){
  instance = new Lector ();
}
return instance;
}

public void leerCarpeta(String filesPath) {

        try (Stream<Path> cadena = Files.list(Paths.get(filesPath))) {
      System.out.println("filesPath: " + filesPath);

            cadena.forEach(elemento -> {
                    System.out.println("elemento: " + elemento);

            leerFichero(elemento.toString());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }



}
public void leerFichero(String filePath) {

try {
      System.out.println("filePath: " + filePath);


 Scanner entrada = new Scanner(new FileReader(filePath));

    String linea;
    String IdActor;
    String NombreActor;
    String IdPelicula;
    String NombrePelicula;

    int cont = 0;
    while (entrada.hasNext()) {
      linea = entrada.nextLine();

        String[] datos = linea.split("\\s+###\\s+");
       
        IdActor = datos[0].split("http://www.wikidata.org/entity/Q")[1];
        NombreActor = datos[1];
        IdPelicula = datos[2].split("http://www.wikidata.org/entity/Q")[1];
        NombrePelicula = datos[3];

       
      cont++; 
      if ((cont % 20) == 0) 
      System.out.println("Lineas: " + cont + "\t" + IdActor + " ### "+ NombreActor+ " ### "+ IdPelicula+ " ### "+ NombrePelicula);
    }
     
    entrada.close();
   
  } catch (Exception e) 
  {

  }
}
}

