package eda.practica1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Lector {

    private final ListaActores listaActores;
    private final ListaPeliculas listaPeliculas;
    private final Idioma i18n = Idioma.getInstance();

    //private int contador = 0;

    public Lector(ListaActores listaActores, ListaPeliculas listaPeliculas) {
        this.listaActores = listaActores;
        this.listaPeliculas = listaPeliculas;
    }

    public void leerCarpeta(String rutaCarpeta) {

        try (Stream<Path> cadena = Files.list(Paths.get(rutaCarpeta))) {
            System.out.println("rutaCarpeta: " + rutaCarpeta);

            cadena.forEach(elemento -> {
                //   System.out.println("elemento: " + elemento);
                leerFichero(elemento.toString());
            });
        } catch (IOException e) {
            System.out.print(i18n.get("msg.error.acceso.carpeta", rutaCarpeta));
        }

    }

    public void leerFichero(String elemento) {

        try {
             System.out.print(i18n.get("msg.info.procesar.fichero", ( elemento.substring(elemento.length() - 25))));

            System.out.println("Procesando contenido de: " + elemento.substring(elemento.length() - 25));

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

                            Actor actor = listaActores.buscar(idActor);
                            if (actor == null) {
                                actor = new Actor(idActor, nombreActor);
                                listaActores.agregar(actor);
                            }

                            Pelicula pelicula = listaPeliculas.buscar(idPelicula);
                            if (pelicula == null) {
                                pelicula = new Pelicula(idPelicula, nombrePelicula, anioPelicula, new java.util.HashMap<>());
                                listaPeliculas.agregar(pelicula);
                            }

                            pelicula.agregarActor(actor);
                            // contador++;

                            // if (contador % 25000 == 0) {
                            //     System.out.println("Líneas leídas: " + contador + "\t" + idActor + " ### " + nombreActor);
                            // }
                        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                            System.err.println("Error procesando línea en " + elemento + ": " + linea);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error procesando elemento: " + elemento);
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
