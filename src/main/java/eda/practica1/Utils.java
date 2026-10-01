package eda.practica1;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static void mostrarLetrasRaras(ListaActores listaActores, ListaPeliculas listaPeliculas) {
        //Esta funcion es muy ineficiente, pero necesaria para verificar que se muestran todas las letras de los ficheros correctamente.

        //se ha usado IA para organizar las letras en grupos
        String letrasAutorizadas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "ÑÁÉÍÓÚ" // Español (Ñ y tildes estándar)
                + "ÄËÏÖÜÀÈÌÒÙÂÊÎÔÛÃÕ" // Variantes Latinas Europeo Occidental (Diéresis, Umlauts, Grave, Circunflejo, Tilde)
                + "ÅÆØÐÞ" // Escandinavas y Nórdicas
                + "ÇĆČĎĐĚĒĖĘĞĠĢḤĪĬḴĶĻĽŁŃŅŇŌŐŒŘŚŞŠȘȚŤṬŨŪŬŮŰŶŹŻŽƏ" // Centroeuropeas, Bálticas y Eslavas (Diacríticos, Cediya, Comas)
                + "ẠẢẤẦẨẮẰẶĀĂĄĨẬẸẺẾỀỂỄỆỈỊİƠỌỎỐỒỖỘỚỜỠỢỤỦỮỰƯÝỲỸ" // Vietnamita (Vocales con marcas compuestas)
                + "АБВГДЕЗИЙКЛМНОПРСТУФХЦШЬЯЂĔЈ" // Cirílico
                + "ΑΔΖΙΛΟΡΣΤΧΏ" // Griego
                + "أاةتثجحخرزصضطعقلمنهوي" // Árabe
                + "আকঙজরল" // Bengalí
                + "戯遊死的亡" // Ideogramas Hanzi / Kanji
                + "ʹʻʼǃ";    // Símbolos fonéticos y modificadores

        Set<Character> letrasEncontradas = new HashSet<>();

        for (Pelicula pelicula : listaPeliculas.getListaPeliculas().values()) {
            String nombre = pelicula.getNombrePelicula();

            for (char letra : nombre.toUpperCase().toCharArray()) {
                if (Character.isLetter(letra) && letrasAutorizadas.indexOf(letra) == -1) {
                    letrasEncontradas.add(letra);
                }
            }
        }

        for (Actor actor : listaActores.getListaActores().values()) {
            String nombre = actor.getNombreActor();

            for (char letra : nombre.toUpperCase().toCharArray()) {
                if (Character.isLetter(letra) && letrasAutorizadas.indexOf(letra) == -1) {
                    letrasEncontradas.add(letra);
                }
            }
        }

        System.out.println("Letras distintas encontradas: " + letrasEncontradas);
    }

    public static boolean validarIdNumerico(String id) {
        return id.matches("\\d+");
    }

    public static boolean validarAnio(int anio) {
        return anio >= 1970 && anio <= Year.now().getValue();
    }

    public static boolean validarNombre(String nombre) {
        return nombre.matches("^.{4,}$");
    }

    public static void revisarNombresDuplicados(ListaActores listaActores) {
        HashMap<String, String> nombresEIds = new HashMap<>();

        for (Actor actor : listaActores.getListaActores().values()) {
            nombresEIds.put(actor.getIdActor(), actor.getNombreActor());
        }

        revisarNombresDuplicados(nombresEIds, "actores");
    }

    public static void revisarNombresDuplicados(ListaPeliculas listaPeliculas) {
        HashMap<String, String> nombresEIds = new HashMap<>();

        for (Pelicula pelicula : listaPeliculas.getListaPeliculas().values()) {
            nombresEIds.put(pelicula.getIdPelicula(), pelicula.getNombrePelicula());
        }

        revisarNombresDuplicados(nombresEIds, "películas");
    }

    private static void revisarNombresDuplicados(HashMap<String, String> nombresEIds, String tipo) {
        List<String> ids = new ArrayList<>(nombresEIds.keySet());
        boolean hayDuplicados = false;

        for (int i = 0; i < ids.size(); i++) {
            for (int j = i + 1; j < ids.size(); j++) {
                String id1 = ids.get(i);
                String id2 = ids.get(j);
                String nombre1 = nombresEIds.get(id1).trim();
                String nombre2 = nombresEIds.get(id2).trim();

                if (nombre1.equalsIgnoreCase(nombre2)) {
                    System.out.println("Nombre duplicado en " + tipo + ": " + nombre1
                            + " (IDs " + id1 + " y " + id2 + ")");
                    hayDuplicados = true;
                }
            }
        }

        if (!hayDuplicados) {
            System.out.println("No hay nombres duplicados en " + tipo + ".");
        }
    }

    public static String generarIdActor(ListaActores listaActores) {
        String idAleatorio;
        do {
            long numeroAleatorio = (long) (Math.random() * 9990000000L) + 10000000L;
            idAleatorio = String.valueOf(numeroAleatorio);
        } while (listaActores.existe(idAleatorio));
        return idAleatorio;
    }

    public static void barracarga() {
        int numElementos = 13;
        int numAlmohadillas = 100;

        barracarga(numElementos, numAlmohadillas);
    }

    private static void barracarga(int numElementos, int numAlmohadillas) {
        iniciarbarracarga();
        int almohadillasPintadas = 0;

        for (int i = 0; i < numElementos; i++) {

            int almohadillasQueDeberiaHaber = ((i + 1) * numAlmohadillas) / numElementos;
            int almohadillasAPintar = almohadillasQueDeberiaHaber - almohadillasPintadas;
            for (int j = 0; j < almohadillasAPintar; j++) {
                avanzarbarracarga();
                //    Thread.sleep(100);
            }
            almohadillasPintadas = almohadillasQueDeberiaHaber;
        }
        finalizarbarracarga();
    }

    public static void iniciarbarracarga() {
        System.out.print("Cargando[");
    }

    public static void avanzarbarracarga() {
        System.out.print("#");
    }

    public static void finalizarbarracarga() {
        System.out.println("]");
    }

    public static void ansiSubirFilas(int filas) {
        System.out.print("\033[" + filas + "A");
    }

    public static void ansiBajarFilas(int filas) {
        System.out.print("\033[" + filas + "B");
    }

    public static void ansiIrPosicion(int posicion) {
        System.out.print("\033[" + posicion + "G");
    }

    public static void ansiBorrarFila(int modoBorrado) {
        System.out.print("\033[" + modoBorrado + "K");
    }

    public static long iniciarCronometro() {
        return System.currentTimeMillis();
    }

    public static long pararCronometro(long tiempo) {
        return System.currentTimeMillis() - tiempo;
    }

    public static void mostrarTiempo(String mensaje, long tiempo) {
        System.out.println(mensaje + " " + tiempo);
    }

    public static <T extends Comparable<T>> void mergeSort(ArrayList<T> tabla) {
        mergeSort(tabla, 0, (tabla.size() - 1), false);
    }

    public static <T extends Comparable<T>> void mergeSort(ArrayList<T> tabla, boolean inverso) {
        mergeSort(tabla, 0, (tabla.size() - 1), inverso);
    }

    private static <T extends Comparable<T>> void mergeSort(ArrayList<T> tabla, int inicio, int fin, boolean inverso) {
        if (inicio < fin) {
            mergeSort(tabla, inicio, (inicio + fin) / 2, inverso);
            mergeSort(tabla, ((inicio + fin) / 2) + 1, fin, inverso);
            mezcla(tabla, inicio, (inicio + fin) / 2, fin, inverso);
        }
    }

    private static <T extends Comparable<T>> void mezcla(ArrayList<T> tabla, int inicio, int centro, int fin, boolean inverso) {
        ArrayList<T> laMezcla = new ArrayList<>();
        int izq = inicio;
        int der = centro + 1;

        while (izq <= centro && der <= fin) {
            Boolean orden = tabla.get(izq).compareTo(tabla.get(der)) <= 0;
            if (inverso) {
                orden = tabla.get(izq).compareTo(tabla.get(der)) >= 0;
            }

            if (orden) {
                laMezcla.add(tabla.get(izq));
                izq++;
            } else {
                laMezcla.add(tabla.get(der));
                der++;
            }
        }
        if (izq > centro) {
            while (der <= fin) {
                laMezcla.add(tabla.get(der));
                der++;
            }
        } else {
            while (izq <= centro) {
                laMezcla.add(tabla.get(izq));
                izq++;
            }
        }
        for (int j = 0; j < laMezcla.size(); j++) {
            tabla.set(inicio + j, laMezcla.get(j));
        }
    }

    public static <T extends Comparable<T>> void quickSort(ArrayList<T> tabla) {
        quickSort(tabla, 0, (tabla.size() - 1));
    }

    private static <T extends Comparable<T>> void quickSort(ArrayList<T> tabla, int inicio, int fin) {
        if (fin - inicio > 0) {
            int indiceParticion = particion(tabla, inicio, fin);
            quickSort(tabla, inicio, indiceParticion - 1);
            quickSort(tabla, indiceParticion + 1, fin);

        }
    }

    private static <T extends Comparable<T>> boolean esMayor(T mayor, T menor) {
        return (mayor).compareTo(menor) > 0;
    }

    private static <T extends Comparable<T>> T medianaDeTres(T inicio, T centro, T fin) {
        if (esMayor(inicio, centro)) {
            if (esMayor(centro, fin)) {
                return centro;
            }
            if (esMayor(inicio, fin)) {
                return fin;
            }
            return inicio;
        } else {
            if (esMayor(inicio, fin)) {
                return inicio;
            }
            if (esMayor(centro, fin)) {
                return fin;
            }
            return centro;
        }
    }

    private static <T extends Comparable<T>> int particion(ArrayList<T> tabla, int inicio, int fin) {
        int centro = inicio + (fin - inicio) / 2;

        T pivote = medianaDeTres(tabla.get(inicio), tabla.get(centro), tabla.get(fin));
        int izq = inicio;
        int der = fin;

        while (izq < der) {
            while (tabla.get(izq).compareTo(pivote) >= 0 && izq < der) {
                izq++;
            }
            while (tabla.get(der).compareTo(pivote) < 0) {
                der--;
            }

            if (izq < der) {
                swap(tabla, izq, der);
            }

        }
        tabla.set(inicio, tabla.get(der));
        tabla.set(der, pivote);
        return der;
    }

    private static <T extends Comparable<T>> void swap(ArrayList<T> tabla, int uno, int dos) {
        T temp = tabla.get(uno);
        tabla.set(uno, tabla.get(dos));
        tabla.set(dos, temp);
    }
}
