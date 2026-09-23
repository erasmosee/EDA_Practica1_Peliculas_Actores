package eda.practica1;

import java.util.Scanner;

public class Menu {

    private final Scanner sc;
    private final Lector lector;
    private final ListaActores listaActores;
    private final ListaPeliculas listaPeliculas;
    private boolean datosCargados;

    public Menu(Lector lector, ListaActores listaActores, ListaPeliculas listaPeliculas) {
        this.sc = new Scanner(System.in);
        this.lector = lector;
        this.listaActores = listaActores;
        this.listaPeliculas = listaPeliculas;
        this.datosCargados = false;
    }

    public void mostrarMenu() {
        Idioma i18n = Idioma.getInstance();
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n" + i18n.get("menu.texto.titulo"));

            if (!datosCargados) {
                System.out.println(i18n.get("menu.opcion.objetivo.1"));
            } else {
                System.out.println(i18n.get("menu.opcion.objetivo.1.bis"));
                System.out.println(i18n.get("menu.opcion.objetivo.2"));
                System.out.println(i18n.get("menu.opcion.objetivo.3"));
                System.out.println(i18n.get("menu.opcion.objetivo.4"));
                System.out.println(i18n.get("menu.opcion.objetivo.5"));
                System.out.println(i18n.get("menu.opcion.objetivo.6"));
                System.out.println(i18n.get("menu.opcion.objetivo.7"));
                System.out.println(i18n.get("menu.opcion.objetivo.8"));
                System.out.println(i18n.get("menu.opcion.objetivo.9"));
                System.out.println(i18n.get("menu.opcion.extra.utilidades"));
            }

            System.out.println(i18n.get("menu.opcion.extra.salir"));
            System.out.print(i18n.get("menu.texto.seleccionarOpcion"));

            try {
                opcion = Integer.parseInt(sc.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println(i18n.get("msg.opcion.invalida"));
            }
        }
    }

    private void procesarOpcion(int opcion) {
        Idioma i18n = Idioma.getInstance();

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
                System.out.println(i18n.get("menu.opcion.extra.saliendo"));
            default ->
                System.out.println(i18n.get("msg.opcion_invalida"));
        }

    }

    private void cargarDatos() {
        Idioma i18n = Idioma.getInstance();
        String rutaDefecto = "./resources";

        System.out.print(i18n.get("menu.opcion.objetivo.1.input.ruta", rutaDefecto));
        String ruta = sc.nextLine().trim();

        if (ruta.isEmpty()) {
            ruta = rutaDefecto;
        }

        lector.leerCarpeta(ruta);
        this.datosCargados = true;
    }

    private void mostrarMenuUtilidades() {
        Idioma i18n = Idioma.getInstance();
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n" + i18n.get("menu.texto.extra.utilidades"));
            System.out.println(i18n.get("menu.opcion.extra.utilidades.1"));
            System.out.println(i18n.get("menu.opcion.atras"));
            System.out.print(i18n.get("menu.texto.seleccionarOpcion"));

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 ->
                        Utils.mostrarLetrasRaras(listaActores, listaPeliculas);
                    case 0 ->
                        System.out.println(i18n.get("menu.opcion.extra.utilidades.volver"));
                    default ->
                        System.out.println(i18n.get("msg.opcion.invalida"));
                }
            } catch (NumberFormatException e) {
                System.out.println(i18n.get("msg.opcion.invalida"));
            }
        }
    }

}
