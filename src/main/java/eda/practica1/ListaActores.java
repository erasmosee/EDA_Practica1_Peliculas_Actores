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

    public void agregar(Actor Actor) {
        listaActores.put(Actor.getIdActor(), Actor);
    }

    public Actor buscar(String idActor) {
        return listaActores.get(idActor);
    }

    public HashMap<String, Actor> getListaActores() {
        return listaActores;
    }

    public boolean  existe(String idActor) {
        Actor actor = buscar(idActor);
        if (actor != null) {
            return true;
        } else {
            return false;
        }
    }

    public void buscarActorID() {
//ID Actor 60576424
        System.out.print(i18n.get("menu.opcion.2.input.actor"));
        String idActorBusqueda = sc.nextLine().trim();
        if (!Utils.validarIdNumerico(idActorBusqueda)) {
            System.out.println(i18n.get("msg.opcion.validar.id.numerico"));
            return;
        }

        Actor actor = buscar(idActorBusqueda);

        if (actor == null) {
            System.out.println(i18n.get("menu.opcion.2.actor.no.encontrado"));
        } else {
            System.out.println(i18n.get("menu.opcion.2.actor.encontrado") + actor.getIdActor()
                    + " - " + actor.getNombreActor());
        }
    }

    public void insertarActorID() {
        System.out.println(i18n.get("menu.opcion.3.input.nombre"));
        String nombreActor = sc.nextLine().trim();
        if (!Utils.validarNombre(nombreActor)) {
            System.out.println(i18n.get("msg.opcion.validar.nombre"));
            return;
        }
        System.out.println(i18n.get("menu.opcion.3.input.pelicula"));
        String idPelicula = sc.nextLine().trim();

        if (!Utils.validarIdNumerico(idPelicula)) {
            System.out.println(i18n.get("msg.opcion.validar.id.numerico"));
            return;
        }
        String idActorBusqueda = Utils.generarIdActor(this);
        Actor nuevoActor = new Actor(idActorBusqueda, nombreActor);
        agregar(nuevoActor);

    }
}
