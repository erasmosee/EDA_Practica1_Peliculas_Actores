package eda.practica1;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Lector {

    private final ListaActores listaActoresId;
    private final ListaPeliculas listaPeliculasId;
    private final Idioma i18n = Idioma.getInstance();

    //private int contador = 0;
    public Lector(ListaActores listaActores, ListaPeliculas listaPeliculas) {
        this.listaActoresId = listaActores;
        this.listaPeliculasId = listaPeliculas;
    }

    public void leerCarpeta(String rutaCarpeta) {

        try {
            File carpeta = new File(rutaCarpeta);
            File[] ficheros = carpeta.listFiles();

            if (ficheros != null && ficheros.length > 0) {

                int totalFicheros = ficheros.length;
                int totalAlmohadillas = 100;
                int almohadillasPintadas = 0;

                System.out.println(i18n.get("msg.info.procesar.ficheros", (totalFicheros)));

                Utils.iniciarbarracarga();

                for (int i = 0; i < totalFicheros; i++) {
                    leerFichero(ficheros[i].getPath());

                    int almohadillasQueDeberiaHaber = ((i + 1) * totalAlmohadillas) / totalFicheros;
                    int almohadillasAPintar = almohadillasQueDeberiaHaber - almohadillasPintadas;
                    for (int j = 0; j < almohadillasAPintar; j++) {
                        Utils.avanzarbarracarga();
                    }
                    almohadillasPintadas = almohadillasQueDeberiaHaber;
                }
                Utils.finalizarbarracarga();
            }

        } catch (Exception e) {
            System.out.println(i18n.get("msg.error.acceso.carpeta", (rutaCarpeta)));
        }

    }

    public void leerFichero(String elemento) {

        try {

            try (Scanner entrada = new Scanner(Files.newInputStream(Paths.get(elemento)), StandardCharsets.UTF_8)) {
                String linea;

                while (entrada.hasNext()) {
                    linea = entrada.nextLine();

                    String[] datos = linea.split("\\s+###\\s+");
                    if (4 == datos.length) {
                        try {
                            String idPelicula = extraerId(datos[2]);
                            String nombrePelicula = datos[3].trim();
                            String idActor = extraerId(datos[0]);
                            String nombreActor = datos[1].trim();

                            Integer anioPelicula = Integer.valueOf(elemento.substring(elemento.length() - 8, elemento.length() - 4));

                            Actor actor = listaActoresId.buscar(idActor);
                            if (actor == null) {
                                actor = new Actor(idActor, nombreActor);
                                listaActoresId.agregar(actor);
                            }

                            Pelicula pelicula = listaPeliculasId.buscarPeliculaPorId(idPelicula);
                            if (pelicula == null) {
                                pelicula = new Pelicula(idPelicula, nombrePelicula, anioPelicula, new HashMap<>());
                                listaPeliculasId.agregarPeliculaPorId(pelicula);
                            }

                            pelicula.agregarActor(actor);
                            // contador++;

                            // if (contador % 25000 == 0) {
                            //     System.out.println("Líneas leídas: " + contador + "\t" + idActor + " ### " + nombreActor);
                            // }
                        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                            System.err.print(i18n.get("msg.error.procesando.linea", elemento, linea));
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(i18n.get("msg.error.procesando.fichero", elemento));
        }
    }

    private String extraerId(String texto) {
        int pos = texto.indexOf("/entity/Q");
        if (pos != -1) {
            return String.valueOf(texto.substring(pos + 9).trim());
        }
        return String.valueOf(texto.trim());
    }

}
