package eda.practica1;

import java.util.HashMap;
import java.util.Scanner;

public class ListaActores {

    private final Scanner sc;
    private final HashMap<String, Actor> listaActores;
    private final Idioma i18n = Idioma.getInstance();

    public ListaActores() {
        this.sc = new Scanner(System.in);
        listaActores = new HashMap<>();
    }

    public void agregarActorPorId(Actor actor) {
        listaActores.put(actor.getIdActor(), actor);
    }
     public void agregarActorPorNombre(Actor actor) {
        listaActores.put(actor.getNombreActor(), actor);
    }

    public Actor buscarActorPorId(String idActor) {
        return listaActores.get(idActor);
    }

    public HashMap<String, Actor> getListaActores() {
        return listaActores;
    }

    public boolean existe(String idActor) {
        Actor actor = buscarActorPorId(idActor);
        return actor != null;
    }

    private String solicitarIdActor() {

        System.out.print(i18n.get("menu.opcion.input.actor"));
        String idActor = sc.nextLine().trim();
        if (!Utils.validarIdNumerico(idActor)) {
            System.out.println(i18n.get("msg.opcion.validar.id.numerico"));
            return null;
        }
        return idActor;
    }

    private String solicitarNombreActor() {

        System.out.println(i18n.get("menu.opcion.input.nombre"));
        String nombreActor = sc.nextLine().trim();
        if (!Utils.validarNombre(nombreActor)) {
            System.out.println(i18n.get("msg.opcion.validar.nombre"));
            return null;
        }
        return nombreActor;
    }

    private String solicitarIdPelicula() {

        System.out.print(i18n.get("menu.opcion.input.pelicula"));
        String idPelicula = sc.nextLine().trim();
        if (!Utils.validarIdNumerico(idPelicula)) {
            System.out.println(i18n.get("msg.opcion.validar.id.numerico"));
            return null;
        }
        return idPelicula;
    }

    public void buscarActorID() {
        String idActorBusqueda = solicitarIdActor();
        if (idActorBusqueda == null) {
            return;
        }

        Actor actor = buscarActorPorId(idActorBusqueda);

        if (actor == null || !actor.isActivo()) {
            System.out.println(i18n.get("menu.opcion.actor.no.encontrado"));
        } else {
            System.out.println(i18n.get("menu.opcion.actor.encontrado") + actor.getIdActor()
                    + " - " + actor.getNombreActor());
        }
    }

    public void insertarActorID(ListaPeliculas listaPeliculas) {
        String nombreActor = solicitarNombreActor();
        if (nombreActor == null) {
            return;
        }

        String idPelicula = solicitarIdPelicula();
        if (idPelicula == null) {
            return;
        }

        Pelicula pelicula = listaPeliculas.buscarPeliculaPorId(idPelicula);
        if (pelicula == null) {
            System.out.println(i18n.get("menu.opcion.6.pelicula.no.encontrada"));
            return;
        }

        String idActorBusqueda = Utils.generarIdActor(this);
        Actor nuevoActor = new Actor(idActorBusqueda, nombreActor, new HashMap<>());
        agregarActorPorId(nuevoActor);
        pelicula.agregarActor(nuevoActor);
        System.err.println(i18n.get("msg.actor.insertado.pelicula", nuevoActor.getNombreActor(), nuevoActor.getIdActor(), pelicula.getNombrePelicula()));
    }

    public void borrarActor(int codigoBorrado) {
        //codigo borrado 0 logico, 1 definitivo

        String idActorBusqueda = solicitarIdActor();
        if (idActorBusqueda == null) {
            return;
        }
        Actor actor = buscarActorPorId(idActorBusqueda);

        if (actor == null) {
            System.out.println(i18n.get("menu.opcion.actor.no.encontrado"));
        } else {
            switch (codigoBorrado) {
                case 0 -> {
                    actor.setActivo(false);
                    System.out.println(i18n.get("msg.baja.logica.efectuada", actor.getNombreActor(), actor.getIdActor()));
                }
                case 1 -> {
                    listaActores.remove(actor.getIdActor());
                    System.out.println(i18n.get("msg.baja.definitiva.efectuada", actor.getNombreActor(), actor.getIdActor()));
                }
                case 2 -> {
                    actor.setActivo(true);
                    System.out.println(i18n.get("msg.recuperacion.logica.restaurada", actor.getNombreActor(), actor.getIdActor()));
                }
            }

        }
    }
}
