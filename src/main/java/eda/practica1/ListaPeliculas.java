package eda.practica1;

import java.util.HashMap;

public class ListaPeliculas {

  private final HashMap<String, Pelicula> listaPeliculas;

  public ListaPeliculas() {
    listaPeliculas = new HashMap<>();
  }

  public void agregar(Pelicula pelicula) {
    listaPeliculas.put(pelicula.getIdPelicula(), pelicula);
  }

  public Pelicula buscar(String idPelicula) {
    return listaPeliculas.get(idPelicula);
  }

  public HashMap<String, Pelicula> getListaPeliculas() {
    return listaPeliculas;
  }
}