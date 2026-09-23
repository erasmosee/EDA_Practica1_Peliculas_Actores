package eda.practica1;

import java.util.HashMap;
import java.util.Scanner;

public class ListaActores {
  private final Scanner sc;
  private final HashMap<String, Actor> listaActores;

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
        Idioma i18n = Idioma.getInstance();

        System.out.print(i18n.get("menu.opcion.objetivo.2.input.actor"));
        String idActorBusqueda = sc.nextLine().trim();
        if (!idActorBusqueda.matches("\\d+")) {
            System.out.println(i18n.get("menu.opcion.objetivo.2.id.no.valido"));
            return;
        }

        Actor actor = buscar(idActorBusqueda);

        if (actor == null) {
            System.out.println(i18n.get("menu.opcion.objetivo.2.actor.no.encontrado"));
        } else {
            System.out.println(i18n.get("menu.opcion.objetivo.2.actor.encontrado") + actor.getIdActor()
                    + " - " + actor.getNombreActor());
        }
    }
}