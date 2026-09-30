package eda.practica1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Actor {

    String idActor;
    String nombreActor;
    boolean activo;
    private final HashMap<String, Estreno> estrenos = new HashMap<>();

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

    public void agregarEstreno(Estreno estreno) {
        String clave = estreno.getPelicula().getIdPelicula() + "_" + estreno.getAnio();
        estrenos.put(clave, estreno);
    }

    public Estreno buscarEstreno(String idPelicula, int anio) {
        String clave = idPelicula + "_" + anio;
        return estrenos.get(clave);
    }

    public void eliminarEstreno(String idPelicula, int anio) {
        String clave = idPelicula + "_" + anio;
        estrenos.remove(clave);
    }

    public ArrayList<Pelicula> devolverPeliculas() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();

        for (Estreno estreno : estrenos.values()) {
            Pelicula peliculaEstreno = estreno.getPelicula();
            boolean yaEstaEnLaLista = false;

            for (Pelicula pelicula : peliculas) {
                if (pelicula.getIdPelicula().equals(peliculaEstreno.getIdPelicula())) {
                    yaEstaEnLaLista = true;
                    break;
                }
            }

            if (!yaEstaEnLaLista) {
                peliculas.add(peliculaEstreno);
            }
        }

        return peliculas;
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

    public HashMap<Integer, ArrayList<Pelicula>> mapearPeliculas() {
        HashMap<Integer, ArrayList<Pelicula>> mapaPeliculas = new HashMap<>();

        for (Estreno estreno : estrenos.values()) {
            Integer anio = estreno.getAnio();
            Pelicula pelicula = estreno.getPelicula();

            ArrayList<Pelicula> peliculasAnio = mapaPeliculas.get(anio);

            if (peliculasAnio == null) {
                peliculasAnio = new ArrayList<>();
                mapaPeliculas.put(anio, peliculasAnio);
            }

            peliculasAnio.add(pelicula);
        }

        return mapaPeliculas;
    }

}
