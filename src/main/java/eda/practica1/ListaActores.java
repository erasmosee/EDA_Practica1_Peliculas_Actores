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

    public void buscarActorID() {

        System.out.print(i18n.get("menu.opcion.2.input.actor"));
        String idActorBusqueda = sc.nextLine().trim();
        if (!idActorBusqueda.matches("\\d+")) {
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

//
    }
}
