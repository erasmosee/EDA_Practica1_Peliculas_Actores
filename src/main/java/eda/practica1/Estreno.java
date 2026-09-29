package eda.practica1;

import java.util.Objects;

public class Estreno {

    private Pelicula pelicula;
    private Actor actor;
    private int anio;

    public Estreno(Pelicula pelicula, Actor actor, int anio) {
        this.pelicula = pelicula;
        this.actor = actor;
        this.anio = anio;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estreno)) {
            return false;
        }
        Estreno that = (Estreno) o;
        return anio == that.anio
                && Objects.equals(pelicula.getIdPelicula(), that.pelicula.getIdPelicula())
                && Objects.equals(actor.getIdActor(), that.actor.getIdActor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(pelicula.getIdPelicula(), actor.getIdActor(), anio);
    }
}
