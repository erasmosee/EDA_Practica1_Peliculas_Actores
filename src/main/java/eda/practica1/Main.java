package eda.practica1;

public class Main {

    public static void main(String[] args) {
    ListaActores listaActores = new ListaActores();
    ListaPeliculas listaPeliculas = new ListaPeliculas();

    Lector lector = new Lector(listaActores, listaPeliculas);
    Menu menu = new Menu(lector, listaActores, listaPeliculas);

    menu.mostrarMenu();
    }
}
