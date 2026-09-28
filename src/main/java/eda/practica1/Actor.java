package eda.practica1;

import java.util.HashMap;

public class Actor {

    String idActor;
    String nombreActor;
    boolean activo;
    private final HashMap<String, Pelicula> listaPeliculasDelActor;

    public Actor(String idActor, String nombreActor, HashMap<String, Pelicula> listaPeliculas) {

        this.idActor = idActor;
        this.nombreActor = nombreActor;
        this.activo = true;
        this.listaPeliculasDelActor = listaPeliculas;

    }

    public String getIdActor() {
        return idActor;
    }

    public void setIdActor(String idActor) {
        this.idActor = idActor;
    }

    public String getNombreActor() {
        return nombreActor;
    }

    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void agregarPelicula(Pelicula pelicula) {
        listaPeliculasDelActor.put(pelicula.getIdPelicula(), pelicula);
    }

    public Pelicula buscarPelicula(String idPelicula) {
        return listaPeliculasDelActor.get(idPelicula);
    }

}
