package eda.practica1;

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
                listaActores.buscarActorID();
            case 10 ->
                mostrarMenuUtilidades();
            case 0 ->
                System.out.println(i18n.get("menu.opcion.0.saliendo"));
            default ->
                System.out.println(i18n.get("msg.opcion.invalida"));
        }

    }

    private void cargarDatos() {
        String rutaDefecto = "./resources";

        System.out.print(i18n.get("menu.opcion.1.insertar.ruta", rutaDefecto));
        String ruta = sc.nextLine().trim();

        if (ruta.isEmpty()) {
            ruta = rutaDefecto;
        }

        lector.leerCarpeta(ruta);
        this.datosCargados = true;
    }

    private void mostrarMenuUtilidades() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n" + i18n.get("menu.texto.utilidades"));
            System.out.println(i18n.get("menu.opcion.10.1.letras.raras"));
            System.out.println(i18n.get("menu.opcion.0.atras"));
            System.out.print(i18n.get("menu.texto.seleccionar.opcion"));

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 ->
                        Utils.mostrarLetrasRaras(listaActores, listaPeliculas);
                    case 0 ->
                        System.out.println(i18n.get("menu.opcion.10.utilidades.volver"));
                    default ->
                        System.out.println(i18n.get("msg.opcion.invalida"));
                }
            } catch (NumberFormatException e) {
                System.out.println(i18n.get("msg.opcion.invalida"));
            }
        }
    }

}
