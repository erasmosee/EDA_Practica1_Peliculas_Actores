package eda.practica1;

import java.util.HashMap;

public class Pelicula {

    String idPelicula;
    String nombrePelicula;
    int anioPelicula;
    boolean activo;
    private final HashMap<String, Actor> listaActoresDeLaPelicula;

    public Pelicula(String idPelicula, String nombrePelicula, int anioPelicula, HashMap<String, Actor> listaActores) {

        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.anioPelicula = anioPelicula;
        this.listaActoresDeLaPelicula = listaActores;
        this.activo = true;
    }

    public void agregarActor(Actor actor) {
        listaActoresDeLaPelicula.put(actor.getIdActor(), actor);
    }

    public Actor buscarActor(String idActor) {
        return listaActoresDeLaPelicula.get(idActor);
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
