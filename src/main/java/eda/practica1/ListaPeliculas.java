package eda.practica1;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaPeliculas {

    private final HashMap<String, Pelicula> listaPeliculas;

    public ListaPeliculas() {
        listaPeliculas = new HashMap<>();
    }

    public void agregarPeliculaPorId(Pelicula pelicula) {
        listaPeliculas.put(pelicula.getIdPelicula(), pelicula);
    }

    public void agregarPeliculaPorNombre(Pelicula pelicula) {
        listaPeliculas.put(pelicula.getNombrePelicula(), pelicula);
    }

    public Pelicula buscarPeliculaPorId(String idPelicula) {
        return listaPeliculas.get(idPelicula);
    }

    public HashMap<String, Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public ArrayList<String> listarPeliculas() {
        ArrayList<String> arrayPeliculas = new ArrayList<>();
        for (Pelicula pelicula : listaPeliculas.values()) {
            arrayPeliculas.add(pelicula.getNombrePelicula());
        }
        return arrayPeliculas;
    }

    public ArrayList<Actor> devolverActoresPelicula(String idPelicula) {
        Pelicula pelicula = buscarPeliculaPorId(idPelicula);

        if (pelicula == null) {
            return new ArrayList<>();
        }
        return pelicula.devolverActores();
    }

}
