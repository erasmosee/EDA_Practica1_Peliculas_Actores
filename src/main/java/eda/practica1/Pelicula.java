package eda.practica1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Pelicula {

    String idPelicula;
    String nombrePelicula;
    boolean activo;
    private final HashMap<String, Estreno> estrenos = new HashMap<>();

    public Pelicula(String idPelicula, String nombrePelicula) {

        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.activo = true;
    }

    public void agregarEstreno(Estreno estreno) {
        String clave = estreno.getActor().getIdActor() + "_" + estreno.getAnio();
        estrenos.put(clave, estreno);
    }

    public Estreno buscarEstreno(String idActor, int anio) {
        String clave = idActor + "_" + anio;
        return estrenos.get(clave);
    }

    public ArrayList<Integer> devolverAniosEstreno() {
        ArrayList<Integer> anios = new ArrayList<>();

        for (Estreno estreno : estrenos.values()) {
            if (!anios.contains(estreno.getAnio())) {
                anios.add(estreno.getAnio());
            }
        }

        Collections.sort(anios);
        return anios;
    }

    public ArrayList<String[]> devolverActoresEstreno() {
        ArrayList<String[]> actores = new ArrayList<>();

        for (Estreno estreno : estrenos.values()) {
            Actor actorEstreno = estreno.getActor();
            boolean actorYaEsta = false;

            for (String[] datosActor : actores) {
                if (datosActor[0].equals(actorEstreno.getIdActor())) {
                    actorYaEsta = true;
                    break;
                }
            }

            if (!actorYaEsta) {
                String[] datosActor = new String[2];
                datosActor[0] = actorEstreno.getIdActor();
                datosActor[1] = actorEstreno.getNombreActor();
                actores.add(datosActor);
            }
        }

        return actores;
    }

    public ArrayList<String[]> devolverEstrenos() {
        ArrayList<String[]> listaEstrenos = new ArrayList<>();

        for (Estreno estreno : estrenos.values()) {
            Actor actorEstreno = estreno.getActor();

            String[] datosEstreno = new String[3];
            datosEstreno[0] = String.valueOf(estreno.getAnio());
            datosEstreno[1] = actorEstreno.getIdActor();
            datosEstreno[2] = actorEstreno.getNombreActor();

            listaEstrenos.add(datosEstreno);
        }

        return listaEstrenos;
    }

    public boolean modificarAnioEstrenos(int anioOrigen, int anioNuevo) {
        ArrayList<Estreno> estrenosAModificar = new ArrayList<>();

        for (Estreno estreno : estrenos.values()) {
            if (estreno.getAnio() == anioOrigen) {
                estrenosAModificar.add(estreno);
            }
        }

        if (estrenosAModificar.isEmpty()) {
            return false;
        }

        for (Estreno estreno : estrenosAModificar) {
            Actor actor = estreno.getActor();
            if (actor.buscarEstreno(idPelicula, anioNuevo) != null) {
                return false;
            }
        }

        for (Estreno estreno : estrenosAModificar) {
            Actor actor = estreno.getActor();
            String claveAntigua = actor.getIdActor() + "_" + anioOrigen;
            estrenos.remove(claveAntigua);
            actor.eliminarEstreno(idPelicula, anioOrigen);

            estreno.setAnio(anioNuevo);
            agregarEstreno(estreno);
            actor.agregarEstreno(estreno);
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
