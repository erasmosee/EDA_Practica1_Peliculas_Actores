package eda.practica1;

import java.util.ArrayList;
import java.util.HashMap;

public class Actor {

    String idActor;
    String nombreActor;
    boolean activo;
    private final HashMap<String, Participacion> participaciones = new HashMap<>();

    public Actor(String idActor, String nombreActor) {

        this.idActor = idActor;
        this.nombreActor = nombreActor;
        this.activo = true;

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

    public void agregarParticipacion(Participacion participacion) {
        String clave = participacion.getPelicula().getIdPelicula() + "_" + participacion.getAnio();
        participaciones.put(clave, participacion);
    }

    public Participacion buscarParticipacion(String idPelicula, int anio) {
        String clave = idPelicula + "_" + anio;
        return participaciones.get(clave);
    }

    public void eliminarParticipacion(String idPelicula, int anio) {
        String clave = idPelicula + "_" + anio;
        participaciones.remove(clave);
    }

    public ArrayList<Pelicula> devolverPeliculas() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();

        for (Participacion participacion : participaciones.values()) {
            Pelicula peliculaParticipacion = participacion.getPelicula();
            boolean yaEstaEnLaLista = false;

            for (Pelicula pelicula : peliculas) {
                if (pelicula.getIdPelicula().equals(peliculaParticipacion.getIdPelicula())) {
                    yaEstaEnLaLista = true;
                    break;
                }
            }

            if (!yaEstaEnLaLista) {
                peliculas.add(peliculaParticipacion);
            }
        }

        return peliculas;
    }

}
