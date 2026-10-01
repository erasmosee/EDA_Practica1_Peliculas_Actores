package eda.practica1;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu {

    private final Lector lector;
    private final Grabador grabador;
    private final ListaActores listaActores;
    private final ListaPeliculas listaPeliculas;
    private boolean datosCargados;
    private final Idioma i18n = Idioma.getInstance();

    public Menu(Lector lector, ListaActores listaActores, ListaPeliculas listaPeliculas) {
        this.lector = lector;
        this.grabador = new Grabador(listaActores);
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
                opcion = Scaner.teclearInteger();
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println(i18n.get("msg.opcion.invalida"));
            }
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
            System.out.println(i18n.get("menu.opcion.10.5.revisar.peliculas"));
            System.out.println(i18n.get("menu.opcion.10.6.revisar.actores"));
            System.out.println(i18n.get("menu.opcion.10.7.mostrar.lista.actores"));
            System.out.println(i18n.get("menu.opcion.0.atras"));
            System.out.print(i18n.get("menu.texto.seleccionar.opcion"));

            try {
                opcion = Scaner.teclearInteger();
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
                opcion = Scaner.teclearInteger();
                procesarOpcionBorrado(opcion);
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
            case 4 ->
                devolverPeliculasActor();
            case 5 ->
                devolverActoresPelicula();
            case 6 ->
                modificarAnioPelicula();
            case 7 ->
                mostrarMenuBorrado();
            case 8 ->
                guardarDatos();
            case 9 ->
                nuevalistarActoresOrdenada();
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
                consultarFichaPelicula();
            case 5 ->
                Utils.revisarNombresDuplicados(listaPeliculas);
            case 6 ->
                Utils.revisarNombresDuplicados(listaActores);
            case 7 ->
                mostrarListaActores();
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

    public static void imprimirNomIdActor(Actor actor) {
        System.out.println(actor.getNombreActor() + " (" + actor.getIdActor() + ")");
    }

    private void nuevalistarActoresOrdenada() {
        ArrayList<Actor> actores = new ArrayList<>(listaActores.getListaActores().values());

        Utils.mostrarTiempo(i18n.get("msg.tiempo.merge.sort"), Utils.mergeSort(actores));
        Utils.saltoDeLinea(1);
        Utils.mostrarTiempo(i18n.get("msg.tiempo.imprimir"), Utils.imrpimirListado(actores));
        Utils.saltoDeLinea(1);
        Utils.mostrarTiempo(i18n.get("msg.tiempo.merge.sort.inverso"), Utils.mergeSort(actores, true));
        Utils.saltoDeLinea(1);
        Utils.mostrarTiempo(i18n.get("msg.tiempo.imprimir"), Utils.imrpimirListado(actores));
        Utils.saltoDeLinea(1);
        Utils.mostrarTiempo(i18n.get("msg.tiempo.quick.sort"), Utils.quickSort(actores));
        Utils.saltoDeLinea(1);
        Utils.mostrarTiempo(i18n.get("msg.tiempo.imprimir"), Utils.imrpimirListado(actores));
        Utils.saltoDeLinea(1);

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
                actor.borrarActor();
                listaActores.eliminarActorPorId(actor.getIdActor());
                System.out.println(i18n.get("msg.baja.definitiva.efectuada", actor.getNombreActor(), actor.getIdActor()));
            }
            case 2 -> {
                actor.setActivo(true);
                System.out.println(i18n.get("msg.recuperacion.logica.restaurada", actor.getNombreActor(), actor.getIdActor()));
            }
            default ->
                System.out.println(i18n.get("msg.opcion.invalida"));
        }
    }

    private String solicitarIdActor() {
        System.out.print(i18n.get("menu.opcion.input.actor"));
        String idActor = Scaner.teclearString();
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
        String nombreActor = Scaner.teclearString();
        if (!Utils.validarNombre(nombreActor)) {
            System.out.println(i18n.get("msg.opcion.validar.nombre"));
            return;
        }

        String idActor = Utils.generarIdActor(listaActores);
        Actor nuevoActor = new Actor(idActor, nombreActor);
        Pelicula pelicula;
        int anio;
        System.out.print(i18n.get("menu.opcion.numero.peliculas"));

        int numPeliculas = Scaner.teclearInteger();
        if (numPeliculas < 1) {
            return;
        }
        for (int i = 1; i <= numPeliculas; i++) {

            System.out.print(i18n.get("menu.opcion.input.pelicula"));
            String idPelicula = Scaner.teclearString();
            if (!Utils.validarIdNumerico(idPelicula)) {
                System.out.println(i18n.get("msg.opcion.validar.id.numerico"));
                return;
            }

            pelicula = listaPeliculas.buscarPeliculaPorId(idPelicula);
            if (pelicula == null) {
                System.out.println(i18n.get("menu.opcion.pelicula.no.encontrada"));
                return;
            }

            anio = solicitarAnio("menu.opcion.input.anio");
            if (anio == -1) {
                return;
            }

            if (nuevoActor.buscarEstreno(idPelicula, anio) != null
                    || pelicula.buscarEstreno(idActor, anio) != null) {
                System.out.println(i18n.get("msg.duplicado.relacion", anio,
                        idActor, nombreActor, idPelicula, pelicula.getNombrePelicula()));
                continue;
            }

            Estreno estreno = new Estreno(pelicula, nuevoActor, anio);
            nuevoActor.agregarEstreno(estreno);
            pelicula.agregarEstreno(estreno);
            if (i == 1) {
                listaActores.agregarActorPorId(nuevoActor);
            } else {
                listaActores.buscarActorPorId(idActor).agregarEstreno(estreno);
            }

        }

        System.out.println(i18n.get("msg.actor.insertado.pelicula", nombreActor, idActor));
    }

    private void cargarDatos() {
        String rutaDefecto = "./resources";

        System.out.print(i18n.get("menu.opcion.1.insertar.ruta", rutaDefecto));
        String ruta = Scaner.teclearString();

        if (ruta.isEmpty()) {
            ruta = rutaDefecto;
        }
        if (lector.leerCarpeta(ruta)) {
            datosCargados = true;
        }

    }

    private void guardarDatos() {
        String rutaDefecto = "./exportados";

        System.out.print(i18n.get("menu.opcion.8.insertar.ruta", rutaDefecto));
        String ruta = Scaner.teclearString();
        if (ruta.isEmpty()) {
            ruta = rutaDefecto;
        }

        try {
            grabador.escribirCarpeta(Paths.get(ruta));
            System.out.println(i18n.get("msg.info.exportacion.completada", ruta));
        } catch (IOException | InvalidPathException e) {
            System.out.println(i18n.get("msg.error.exportacion", ruta));
        }
    }

    private String solicitarIdPelicula() {

        System.out.print(i18n.get("menu.opcion.input.pelicula"));
        String idPelicula = Scaner.teclearString();
        if (!Utils.validarIdNumerico(idPelicula)) {
            System.out.println(i18n.get("msg.opcion.validar.id.numerico"));
            return null;
        }
        return idPelicula;
    }

    private int solicitarAnio(String claveMensaje) {
        System.out.print(i18n.get(claveMensaje));
        int anio;
        try {
            anio = Scaner.teclearInteger();
        } catch (NumberFormatException e) {
            System.out.println(i18n.get("msg.opcion.validar.anio"));
            return -1;
        }

        if (!Utils.validarAnio(anio)) {
            System.out.println(i18n.get("msg.opcion.validar.anio"));
            return -1;
        }
        return anio;
    }

    private void modificarAnioPelicula() {
        String idPelicula = solicitarIdPelicula();
        if (idPelicula == null) {
            return;
        }

        Pelicula pelicula = listaPeliculas.buscarPeliculaPorId(idPelicula);
        if (pelicula == null) {
            System.out.println(i18n.get("menu.opcion.pelicula.no.encontrada"));
            return;
        }

        java.util.ArrayList<Integer> anios = pelicula.devolverAniosEstreno();
        if (anios.isEmpty()) {
            System.out.println(i18n.get("msg.pelicula.sin.estrenos"));
            return;
        }

        System.out.println(i18n.get("msg.pelicula.anios.estrenos", anios));
        int anioOrigen = solicitarAnio("menu.opcion.input.anio.origen");
        if (anioOrigen == -1) {
            return;
        }
        if (!anios.contains(anioOrigen)) {
            System.out.println(i18n.get("msg.anio.sin.estrenos", String.valueOf(anioOrigen)));
            return;
        }

        int anioNuevo = solicitarAnio("menu.opcion.input.anio.nuevo");
        if (anioNuevo == -1) {
            return;
        }
        if (anioNuevo == anioOrigen) {
            System.out.println(i18n.get("msg.anio.igual"));
            return;
        }

        if (pelicula.modificarAnioEstrenos(anioOrigen, anioNuevo)) {
            System.out.println(i18n.get("msg.anio.modificado",
                    String.valueOf(anioOrigen), String.valueOf(anioNuevo), pelicula.getNombrePelicula()));
        } else {
            System.out.println(i18n.get("msg.anio.no.modificado"));
        }
    }

    public void consultarFichaPelicula() {
        String idPelicula = solicitarIdPelicula();
        Pelicula pelicula = listaPeliculas.buscarPeliculaPorId(idPelicula);

        if (pelicula == null) {
            System.out.println(i18n.get("menu.opcion.pelicula.no.encontrada"));
        } else {

            ImprimirFichaPelicula(pelicula);
        }
    }

    private void ImprimirFichaPelicula(Pelicula pelicula) {
        System.out.println();
        System.out.println(i18n.get("msg.ficha.pelicula"));
        ImprimirIdPelicula(pelicula);
        ImprimirNombrePelicula(pelicula);
        ImprimirAniosPelicula(pelicula);
        ImprimirActoresporAnioEstreno(pelicula);
    }

    private void ImprimirIdPelicula(Pelicula pelicula) {
        System.out.println(i18n.get("msg.ficha.pelicula.id", pelicula.getIdPelicula()));
    }

    private void ImprimirNombrePelicula(Pelicula pelicula) {
        System.out.println(i18n.get("msg.ficha.pelicula.nombre", pelicula.getNombrePelicula()));
    }

    private void ImprimirAniosPelicula(Pelicula pelicula) {
        System.out.print(i18n.get("msg.ficha.pelicula.anios"));
        boolean flagAnioPrimeraVez = true;
        for (Integer anios : pelicula.devolverAniosEstreno()) {
            if (!flagAnioPrimeraVez) {
                System.out.print(", ");
            }
            System.out.print(anios);
            flagAnioPrimeraVez = false;
        }
        System.out.println();
    }

    private void ImprimirActoresporAnioEstreno(Pelicula pelicula) {
        System.out.println(i18n.get("msg.ficha.pelicula.actores"));
        String anioAnterior = "0";
        for (String[] datosEstreno : pelicula.devolverEstrenos()) {
            if (!anioAnterior.equals(datosEstreno[0])) {
                System.out.println();
            }
            System.out.println("Año: " + datosEstreno[0]
                    + " | Actor: " + datosEstreno[1] + " - " + datosEstreno[2]);
            anioAnterior = datosEstreno[0];
        }
    }

    private void devolverPeliculasActor() {
        String idActor = solicitarIdActor();
        if (idActor == null) {
            return;
        }

        Actor actor = listaActores.buscarActorPorId(idActor);

        if (actor == null || !actor.isActivo()) {
            System.out.println(i18n.get("menu.opcion.actor.no.encontrado"));
        } else {
            System.out.println(i18n.get("msg.opcion.devolver.peliculas.actor", actor.getIdActor(), actor.getNombreActor()));

            HashMap<Integer, ArrayList<Pelicula>> peliculasAnio = actor.mapearPeliculas();

            for (Integer anio : actor.devolverAniosEstreno()) {
                System.out.println("Año: " + anio);

                ArrayList<Pelicula> peliculasDelAnio = peliculasAnio.get(anio);

                for (Pelicula pelicula : peliculasDelAnio) {
                    System.out.println("  " + pelicula.getNombrePelicula()
                            + " (" + pelicula.getIdPelicula() + ")");
                }
            }

            // for (Pelicula pelicula : actor.devolverPeliculas()) {
            //     System.out.println(pelicula.getNombrePelicula() + " (" + pelicula.getIdPelicula() + ")");
            // }
            // for (Pelicula pelicula : listaActores.devolverPeliculasActor(actor.getIdActor())) {
            //     System.out.println(pelicula.getNombrePelicula());
            // }
        }
    }

    private void devolverActoresPelicula() {
        String idPelicula = solicitarIdPelicula();
        if (idPelicula == null) {
            return;
        }

        Pelicula pelicula = listaPeliculas.buscarPeliculaPorId(idPelicula);
        if (pelicula == null || !pelicula.isActivo()) {
            System.out.println(i18n.get("menu.opcion.pelicula.no.encontrada"));
        } else {
            System.out.println(i18n.get("msg.opcion.devolver.actores.pelicula", pelicula.getIdPelicula(), pelicula.getNombrePelicula()));

            HashMap<Integer, ArrayList<Actor>> actoresAnio = pelicula.mapearActores();

            for (Integer anio : pelicula.devolverAniosEstreno()) {
                System.out.println("Año: " + anio);

                ArrayList<Actor> actoresDelAnio = actoresAnio.get(anio);

                for (Actor actor : actoresDelAnio) {
                    System.out.println("  " + actor.getNombreActor()
                            + " (" + actor.getIdActor() + ")");
                }
            }

            // for (Actor actor : pelicula.devolverActores()) {
            //     System.out.println(actor.getNombreActor() + " (" + actor.getIdActor() + ")");
            // }
            // for (Actor actor : listaPeliculas.devolverActoresPelicula(pelicula.getIdPelicula())) {
            //     System.out.println(actor.getNombreActor());
            // }
        }
    }

    private void mostrarListaActores() {
        ArrayList<Actor> actores = new ArrayList<>(listaActores.getListaActores().values());

        long tiempoImprimir = Utils.iniciarCronometro();
        int contador = 0;
        for (Actor actor : actores) {
            System.out.println(actor.getNombreActor() + " (" + actor.getIdActor() + ")");

            contador++;
            if (contador % 25000 != 0) {
                Utils.ansiSubirFilas(1);
                Utils.ansiIrPosicion(1);
                Utils.ansiBorrarFila(2);
            }

        }
        tiempoImprimir = Utils.pararCronometro(tiempoImprimir);

        Utils.mostrarTiempo(i18n.get("msg.tiempo.imprimir"), tiempoImprimir);

    }
}
