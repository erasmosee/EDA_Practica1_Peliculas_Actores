package eda.practica1;

import java.lang.reflect.Array;
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

    void mergeSort(ArrayList tabla) {
        mergeSort(tabla, 0, (tabla.size() - 1));
    }

    private void mergeSort(ArrayList tabla, int inicio, int fin) {
        if (inicio < fin) {
            mergeSort(tabla, inicio, (inicio + fin) / 2);
            mergeSort(tabla, ((inicio + fin) / 2) + 1, fin);
            mezcla(tabla, inicio, (inicio + fin) / 2, fin);
        }
    }

    private void mezcla(ArrayList tabla, int inicio, int centro, int fin) {
        ArrayList laMezcla = (ArrayList) (new Comparable[fin + inicio + 1]);
        int izq = inicio;
        int der = centro + 1;
        int k = 0;
        while (izq == centro && der == fin) {
            if (tabla[izq].compareTo(tabla[der]) <= 0) {
                laMezcla[k] = tabla[izq];
                k++;
                izq++;
            } else {
                laMezcla[k] = tabla[der];
                k++;
                der++;
            }
        }
        if (izq > centro) {
            while (der <= fin) {
                laMezcla[k] = tabla[der];
                k++;
                der++;
            }
        } else {
            while (izq <= centro) {
                laMezcla[k] = tabla[der];
                k++;
                izq++;

            }
        }
        for (int j = inicio; j < fin; j++) {
            tabla[j] = laMezcla[j - inicio];

        }
    }

    //Precondición: values no es nulo y contiene ArrayLists no nulos de actores con, al menos, un elemento, temp no es nulo y tiene al menos el mismo tamaño que
    //values.
    private void mergeSort(ArrayList<Actor>[] values, ArrayList<Actor>[] temp, int start, int end) {
        if (start >= end) {
            return;
        }
        mergeSort(values, temp, start, (start + end) / 2);
        mergeSort(values, temp, (start + end) / 2 + 1, end);
        merge(values, temp, start, (start + end) / 2, end);

    }

    //Precondición: values no es nulo y contiene ArrayLists no nulos de actores con, al menos, un elemento, temp no es nulo y tiene al menos el mismo tamaño que
    //values.
    private void merge(ArrayList<Actor>[] values, ArrayList<Actor>[] temp, int start, int mid, int end) {
        int left = start, right = mid + 1, i = start;
        while (left <= mid && right <= end) {
            if (values[right].get(0).compareTo(values[left].get(0)) < 0) {
                temp[i] = values[right];
                i++;
                right++;
            } else {
                temp[i] = values[left];
                i++;
                left++;
            }
        }
        while (left <= mid) {
            temp[i] = values[left];
            i++;
            left++;

        }
        while (right <= end) {
            temp[i] = values[right];
            i++;
            right++;
        }
        for (i = start; i <= end; i++) {
            values[i] = temp[i];
        }

    }
}
