package eda.practica1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Grabador {

    private static final String URI = "http://www.wikidata.org/entity/Q";
    private final ListaActores listaActores;

    public Grabador(ListaActores listaActores) {
        this.listaActores = listaActores;
    }

    public void escribirCarpeta(Path carpeta) throws IOException {
        Files.createDirectories(carpeta);
        HashMap<Integer, ArrayList<String>> lineasPorAnio = new HashMap<>();

        for (Actor actor : listaActores.getListaActores().values()) {
            HashMap<Integer, ArrayList<Pelicula>> peliculasPorAnio = actor.mapearPeliculas();

            for (Integer anio : peliculasPorAnio.keySet()) {
                ArrayList<String> lineas = lineasPorAnio.get(anio);
                if (lineas == null) {
                    lineas = new ArrayList<>();
                    lineasPorAnio.put(anio, lineas);
                }

                for (Pelicula pelicula : peliculasPorAnio.get(anio)) {
                    String linea = URI + actor.getIdActor() + " ### "
                            + actor.getNombreActor() + " ### "
                            + URI + pelicula.getIdPelicula() + " ### "
                            + pelicula.getNombrePelicula();
                    lineas.add(linea);
                }
            }
        }

        for (Integer anio : lineasPorAnio.keySet()) {
            Path fichero = carpeta.resolve("actors_and_films_" + anio + ".txt");
            Files.write(fichero, lineasPorAnio.get(anio), StandardCharsets.UTF_8);
        }
    }
}