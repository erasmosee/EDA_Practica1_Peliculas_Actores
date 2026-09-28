package eda.practica1;

import java.util.HashMap;
import java.util.Scanner;

public class ListaPeliculas {

    private final Scanner sc;
    private final HashMap<String, Pelicula> listaPeliculas;
    private final Idioma i18n = Idioma.getInstance();

    public ListaPeliculas() {
        this.sc = new Scanner(System.in);
        listaPeliculas = new HashMap<>();
    }

    public void agregarPeliculaPorId(Pelicula pelicula) {
        listaPeliculas.put(pelicula.getIdPelicula(), pelicula);
    }

    public void agregarPeliculaPorNombre(Pelicula pelicula) {
        listaPeliculas.put(pelicula.getNombrePelicula(), pelicula);
    }

    public Pelicula buscarPeliculaPorId(String idPelicula) {
        return listaPeliculas.get(idPelicula);
    }

    public HashMap<String, Pelicula> getListaPeliculas() {
        return listaPeliculas;
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

    private int solicitarAnioPelicula(int viejoAnioPelicula) {
        System.out.print(i18n.get("menu.opcion.input.anio"));
        int nuevoAnioPelicula = Integer.parseInt(sc.nextLine().trim());
        if (!Utils.validarAnio(nuevoAnioPelicula)) {
            System.out.println(i18n.get("msg.opcion.validar.anio"));
            return viejoAnioPelicula;
        }
        return nuevoAnioPelicula;
    }

    public void consultarFichaPelicula() {
        String idPelicula = solicitarIdPelicula();
        Pelicula pelicula = buscarPeliculaPorId(idPelicula);

        if (pelicula == null) {
            System.out.println(i18n.get("menu.opcion.6.pelicula.no.encontrada"));
        } else {
            System.out.println(i18n.get("msg.ficha.pelicula", pelicula.getIdPelicula()));
            System.out.println(i18n.get("msg.ficha.pelicula.nombre", pelicula.getNombrePelicula()));
            System.out.println(i18n.get("msg.ficha.pelicula.anio", pelicula.getAnioPelicula()));
        }

    }

    public void modificarAnioPelicula() {

        //     System.out.print(i18n.get("menu.opcion.input.pelicula"));
        //     String idPelicula = sc.nextLine().trim();
        //     if (!Utils.validarIdNumerico(idPelicula_old)) {
        //         System.out.println(i18n.get("msg.opcion.validar.id.numerico"));
        //         return;
        //     }
        String idPelicula = solicitarIdPelicula();
        Pelicula pelicula = buscarPeliculaPorId(idPelicula);

        if (pelicula == null) {
            System.out.println(i18n.get("menu.opcion.6.pelicula.no.encontrada"));
        } else {

            int nuevoAnioPelicula = solicitarAnioPelicula(pelicula.getAnioPelicula());
            modificarAnioPelicula(idPelicula, nuevoAnioPelicula);
        }
    }

    public void modificarAnioPelicula(String idPelicula, int nuevoAnioPelicula) {
        listaPeliculas.get(idPelicula).setAnioPelicula(nuevoAnioPelicula);
    }

}
