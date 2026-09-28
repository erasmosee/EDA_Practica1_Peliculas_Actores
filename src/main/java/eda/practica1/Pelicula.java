package eda.practica1;

import java.util.ArrayList;
import java.util.Collections;
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

    public ArrayList<Integer> devolverAniosParticipacion() {
        ArrayList<Integer> anios = new ArrayList<>();

        for (Participacion participacion : participaciones.values()) {
            if (!anios.contains(participacion.getAnio())) {
                anios.add(participacion.getAnio());
            }
        }

        Collections.sort(anios);
        return anios;
    }

    public ArrayList<String[]> devolverActoresParticipacion() {
        ArrayList<String[]> actores = new ArrayList<>();

        for (Participacion participacion : participaciones.values()) {
            Actor actorParticipacion = participacion.getActor();
            boolean actorYaEsta = false;

            for (String[] datosActor : actores) {
                if (datosActor[0].equals(actorParticipacion.getIdActor())) {
                    actorYaEsta = true;
                    break;
                }
            }

            if (!actorYaEsta) {
                String[] datosActor = new String[2];
                datosActor[0] = actorParticipacion.getIdActor();
                datosActor[1] = actorParticipacion.getNombreActor();
                actores.add(datosActor);
            }
        }

        return actores;
    }

    public ArrayList<String[]> devolverParticipaciones() {
        ArrayList<String[]> listaParticipaciones = new ArrayList<>();

        for (Participacion participacion : participaciones.values()) {
            Actor actorParticipacion = participacion.getActor();

            String[] datosParticipacion = new String[3];
            datosParticipacion[0] = String.valueOf(participacion.getAnio());
            datosParticipacion[1] = actorParticipacion.getIdActor();
            datosParticipacion[2] = actorParticipacion.getNombreActor();

            listaParticipaciones.add(datosParticipacion);
        }

        return listaParticipaciones;
    }

    public boolean modificarAnioParticipaciones(int anioOrigen, int anioNuevo) {
        ArrayList<Participacion> participacionesAModificar = new ArrayList<>();

        for (Participacion participacion : participaciones.values()) {
            if (participacion.getAnio() == anioOrigen) {
                participacionesAModificar.add(participacion);
            }
        }

        if (participacionesAModificar.isEmpty()) {
            return false;
        }

        for (Participacion participacion : participacionesAModificar) {
            Actor actor = participacion.getActor();
            if (actor.buscarParticipacion(idPelicula, anioNuevo) != null) {
                return false;
            }
        }

        for (Participacion participacion : participacionesAModificar) {
            Actor actor = participacion.getActor();
            String claveAntigua = actor.getIdActor() + "_" + anioOrigen;
            participaciones.remove(claveAntigua);
            actor.eliminarParticipacion(idPelicula, anioOrigen);

            participacion.setAnio(anioNuevo);
            agregarParticipacion(participacion);
            actor.agregarParticipacion(participacion);
        }

        return true;
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
