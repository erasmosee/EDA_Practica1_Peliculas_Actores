package eda.practica1;

import java.util.HashSet;
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

}
