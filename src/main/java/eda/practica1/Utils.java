package eda.practica1;

import java.time.Year;
import java.util.HashSet;
import java.util.Random;
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
        return anio > 1984 && anio <= Year.now().getValue();
    }

    public static boolean validarNombre(String nombre) {
        return nombre.matches("^.{4,}$");
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

    private static final String[] MENSAJES_RAROS = {
        "Alimentando a los actores...",
        "Convenciendo al director de no cambiar el guion...",
        "Rebobinando las cintas VHS...",
        "Buscando los palomitas perdidas...",
        "Llamando al doble de acción...",
        "Añadiendo efectos especiales de bajo presupuesto...",
        "Negociando el caché de la estrella...",
        "Limpiando la alfombra roja..."
    };

    private static final Random RANDOM = new Random();

    public static void getMensajeRaruno(int validador) {
        if (validador == 0) {
            int index = RANDOM.nextInt(MENSAJES_RAROS.length);
            ansiIrPosicion(1);
            ansiBorrarFila(2);
            System.out.print(MENSAJES_RAROS[index]);
        }
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
}
