package eda.practica1;

import java.util.HashMap;

public class Pelicula {

    String idPelicula;
    String nombrePelicula;
    int anioPelicula;
    private final HashMap<String, Actor> listaActores;

    public Pelicula(String idPelicula, String nombrePelicula, int anioPelicula, HashMap<String, Actor> listaActores) {

        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.anioPelicula = anioPelicula;
        this.listaActores = listaActores;
    }

    public void agregarActor(Actor actor) {
        listaActores.put(actor.getIdActor(), actor);
    }

    public Actor buscarActor(String idActor) {
        return listaActores.get(idActor);
    }

     public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }
    public Integer getAnioPelicula() {
        return anioPelicula;
    }

    public void setAnioPelicula(Integer anioPelicula) {
        this.anioPelicula = anioPelicula;
    }
}
