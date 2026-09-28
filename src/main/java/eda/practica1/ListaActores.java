package eda.practica1;

import java.util.HashMap;

public class ListaActores {

    private final HashMap<String, Actor> listaActores;

    public ListaActores() {
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

    public Actor eliminarActorPorId(String idActor) {
        return listaActores.remove(idActor);
    }

    public HashMap<String, Actor> getListaActores() {
        return listaActores;
    }

    public boolean existe(String idActor) {
        Actor actor = buscarActorPorId(idActor);
        return actor != null;
    }
}
