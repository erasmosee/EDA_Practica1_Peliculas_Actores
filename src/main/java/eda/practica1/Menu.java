package eda.practica1;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {

    private final Scanner sc;
    private final Lector lector;
    private final ListaActores listaActores;
    private final ListaPeliculas listaPeliculas;
    private boolean datosCargados;
    private final Idioma i18n = Idioma.getInstance();

    public Menu(Lector lector, ListaActores listaActores, ListaPeliculas listaPeliculas) {
        this.sc = new Scanner(System.in);
        this.lector = lector;
        this.listaActores = listaActores;
        this.listaPeliculas = listaPeliculas;
        this.datosCargados = false;
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n" + i18n.get("menu.texto.titulo"));

            if (!datosCargados) {
                System.out.println(i18n.get("menu.opcion.1.cargar.datos"));
            } else {
                System.out.println(i18n.get("menu.opcion.1.recargar.datos"));
                System.out.println(i18n.get("menu.opcion.2.buscar.actor"));
                System.out.println(i18n.get("menu.opcion.3.insertar.actor"));
                System.out.println(i18n.get("menu.opcion.4.peliculas.actor"));
                System.out.println(i18n.get("menu.opcion.5.actores.peliculas"));
                System.out.println(i18n.get("menu.opcion.6.modificar.anio"));
                System.out.println(i18n.get("menu.opcion.7.borrar.actor"));
                System.out.println(i18n.get("menu.opcion.8.guardar.fichero"));
                System.out.println(i18n.get("menu.opcion.9.actores.nomApe"));
                System.out.println(i18n.get("menu.opcion.10.utilidades"));
            }

            System.out.println(i18n.get("menu.opcion.0.salir"));
            System.out.print(i18n.get("menu.texto.seleccionar.opcion"));

            try {
                opcion = Integer.parseInt(sc.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println(i18n.get("msg.opcion.invalida"));
            }
        }
    }

    private void procesarOpcion(int opcion) {

        if (!datosCargados && opcion > 1) {
            System.out.println(i18n.get("msg.opcion.invalida"));
            return;
        }

        switch (opcion) {
            case 1 ->
                cargarDatos();
            case 2 ->
                buscarActor();
            case 3 ->
                insertarActor();
            case 6 ->
                listaPeliculas.modificarAnioPelicula();
            case 7 ->
                mostrarMenuBorrado();
            case 10 ->
                mostrarMenuUtilidades();
            case 0 ->
                System.out.println(i18n.get("menu.opcion.0.saliendo"));
            default ->
                System.out.println(i18n.get("msg.opcion.invalida"));
        }

    }

    private void procesarOpcionUtilidades(int opcion) {

        switch (opcion) {
            case 1 ->
                Utils.mostrarLetrasRaras(listaActores, listaPeliculas);
            case 2 ->
                Utils.generarIdActor(listaActores);
            case 3 ->
                Utils.barracarga();
            case 4 ->
                listaPeliculas.consultarFichaPelicula();
            case 0 ->
                System.out.println(i18n.get("menu.opcion.10.utilidades.volver"));
            default ->
                System.out.println(i18n.get("msg.opcion.invalida"));
        }

    }

    private void procesarOpcionBorrado(int opcion) {

        switch (opcion) {
            case 1 ->
                procesarBorradoActor(0);
            case 2 ->
                procesarBorradoActor(1);
            case 3 ->
                procesarBorradoActor(2);
            case 0 ->
                System.out.println(i18n.get("menu.opcion.10.utilidades.volver"));
            default ->
                System.out.println(i18n.get("msg.opcion.invalida"));
        }

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

    private void buscarActor() {
        String idActor = solicitarIdActor();
        if (idActor == null) {
            return;
        }

        Actor actor = listaActores.buscarActorPorId(idActor);
        if (actor == null || !actor.isActivo()) {
            System.out.println(i18n.get("menu.opcion.actor.no.encontrado"));
        } else {
            System.out.println(i18n.get("menu.opcion.actor.encontrado") + actor.getIdActor()
                    + " - " + actor.getNombreActor());
        }
    }

    private void insertarActor() {
        System.out.print(i18n.get("menu.opcion.input.nombre"));
        String nombreActor = sc.nextLine().trim();
        if (!Utils.validarNombre(nombreActor)) {
            System.out.println(i18n.get("msg.opcion.validar.nombre"));
            return;
        }

        System.out.print(i18n.get("menu.opcion.input.pelicula"));
        String idPelicula = sc.nextLine().trim();
        if (!Utils.validarIdNumerico(idPelicula)) {
            System.out.println(i18n.get("msg.opcion.validar.id.numerico"));
            return;
        }

        Pelicula pelicula = listaPeliculas.buscarPeliculaPorId(idPelicula);
        if (pelicula == null) {
            System.out.println(i18n.get("menu.opcion.6.pelicula.no.encontrada"));
            return;
        }

        String idActor = Utils.generarIdActor(listaActores);
        Actor nuevoActor = new Actor(idActor, nombreActor, new HashMap<>());
        nuevoActor.agregarPelicula(pelicula);
        pelicula.agregarActor(nuevoActor);
        listaActores.agregarActorPorId(nuevoActor);
        System.out.println(i18n.get("msg.actor.insertado.pelicula", nombreActor, idActor, pelicula.getNombrePelicula()));
    }

    private void procesarBorradoActor(int codigoBorrado) {
        String idActor = solicitarIdActor();
        if (idActor == null) {
            return;
        }

        Actor actor = listaActores.buscarActorPorId(idActor);
        if (actor == null) {
            System.out.println(i18n.get("menu.opcion.actor.no.encontrado"));
            return;
        }

        switch (codigoBorrado) {
            case 0 -> {
                actor.setActivo(false);
                System.out.println(i18n.get("msg.baja.logica.efectuada", actor.getNombreActor(), actor.getIdActor()));
            }
            case 1 -> {
                listaActores.eliminarActorPorId(actor.getIdActor());
                System.out.println(i18n.get("msg.baja.definitiva.efectuada", actor.getNombreActor(), actor.getIdActor()));
            }
            case 2 -> {
                actor.setActivo(true);
                System.out.println(i18n.get("msg.recuperacion.logica.restaurada", actor.getNombreActor(), actor.getIdActor()));
            }
            default -> System.out.println(i18n.get("msg.opcion.invalida"));
        }
    }

    private void cargarDatos() {
        String rutaDefecto = "./resources";

        System.out.print(i18n.get("menu.opcion.1.insertar.ruta", rutaDefecto));
        String ruta = sc.nextLine().trim();

        if (ruta.isEmpty()) {
            ruta = rutaDefecto;
        }
        if (lector.leerCarpeta(ruta)) {
            datosCargados = true;
        }

    }

    private void mostrarMenuUtilidades() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n" + i18n.get("menu.texto.utilidades"));
            System.out.println(i18n.get("menu.opcion.10.1.letras.raras"));
            System.out.println(i18n.get("menu.opcion.10.2.id.aleatorio"));
            System.out.println(i18n.get("menu.opcion.10.3.barra.carga"));
            System.out.println(i18n.get("menu.opcion.10.4.ficha.pelicula"));
            System.out.println(i18n.get("menu.opcion.0.atras"));
            System.out.print(i18n.get("menu.texto.seleccionar.opcion"));

            try {
                opcion = Integer.parseInt(sc.nextLine());
                procesarOpcionUtilidades(opcion);
            } catch (NumberFormatException e) {
                System.out.println(i18n.get("msg.opcion.invalida"));
            }

        }
    }

    private void mostrarMenuBorrado() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n" + i18n.get("menu.texto.borrado"));
            System.out.println(i18n.get("menu.opcion.7.borrado.logico"));
            System.out.println(i18n.get("menu.opcion.7.borrado.definitivo"));
            System.out.println(i18n.get("menu.opcion.7.recuperacion.logica"));
            System.out.println(i18n.get("menu.opcion.0.atras"));
            System.out.print(i18n.get("menu.texto.seleccionar.opcion"));

            try {
                opcion = Integer.parseInt(sc.nextLine());
                procesarOpcionBorrado(opcion);
            } catch (NumberFormatException e) {
                System.out.println(i18n.get("msg.opcion.invalida"));
            }

        }
    }

}
