package eda.practica1;

import java.util.HashMap;

public class Pelicula {

    String idPelicula;
    String nombrePelicula;
    boolean activo;
    private final HashMap<String, Participacion> participaciones = new HashMap<>();

    public Pelicula(String idPelicula, String nombrePelicula) {

        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.activo = true;
    }

    public void agregarParticipacion(Participacion participacion) {
        String clave = participacion.getActor().getIdActor() + "_" + participacion.getAnio();
        participaciones.put(clave, participacion);
    }

    public Participacion buscarParticipacion(String idActor, int anio) {
        String clave = idActor + "_" + anio;
        return participaciones.get(clave);
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
