package eda.practica1;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Lector {

    private final ListaActores listaActoresId;
    private final ListaPeliculas listaPeliculasId;
    private final Idioma i18n = Idioma.getInstance();

    //private int contador = 0;
    public Lector(ListaActores listaActores, ListaPeliculas listaPeliculas) {
        this.listaActoresId = listaActores;
        this.listaPeliculasId = listaPeliculas;
    }

    public boolean leerCarpeta(String rutaCarpeta) {
        boolean retorno = false;
        try {
            File carpeta = new File(rutaCarpeta);
            File[] ficheros = carpeta.listFiles();
            int totalRegistros = 0;

            if (ficheros != null && ficheros.length > 0) {
                int totalFicheros = ficheros.length;
                int totalAlmohadillas = 100;
                int almohadillasPintadas = 0;

                System.out.println(i18n.get("msg.info.procesar.ficheros", (totalFicheros)));

                Utils.iniciarbarracarga();
                System.out.println();

                for (int i = 0; i < totalFicheros; i++) {
                    System.out.println(i18n.get("msg.info.fichero.analizando", ficheros[i].getName()));
                    getMensajeRaruno(i % 20);
                    totalRegistros += leerFichero(ficheros[i].getPath());

                    int almohadillasQueDeberiaHaber = ((i + 1) * totalAlmohadillas) / totalFicheros;
                    int almohadillasAPintar = almohadillasQueDeberiaHaber - almohadillasPintadas;
                    int columnaSiguiente = 10 + almohadillasPintadas;
                    Utils.ansiSubirFilas(2);
                    Utils.ansiIrPosicion(columnaSiguiente);
                    for (int j = 0; j < almohadillasAPintar; j++) {
                        Utils.avanzarbarracarga();
                    }
                    almohadillasPintadas = almohadillasQueDeberiaHaber;
                    Utils.ansiBajarFilas(1);
                    Utils.ansiIrPosicion(1);
                    Utils.ansiBorrarFila(2);
                }
                Utils.ansiSubirFilas(1);
                Utils.ansiIrPosicion(110);
                Utils.finalizarbarracarga();
                System.out.println();
            }
            System.out.println(i18n.get("msg.info.registros.procesados", (totalRegistros)));
            retorno = true;
        } catch (Exception e) {
            System.out.println(i18n.get("msg.error.acceso.carpeta", (rutaCarpeta)));
        }
        return retorno;
    }

    public int leerFichero(String elemento) {
        int registros = 0;

        //public void leerFichero(String elemento) {
        try {

            try (Scanner entrada = new Scanner(Files.newInputStream(Paths.get(elemento)), StandardCharsets.UTF_8)) {
                String linea;
                while (entrada.hasNext()) {
                    linea = entrada.nextLine();
                    registros++;
                    String[] datos = linea.split("\\s+###\\s+");
                    if (4 == datos.length) {
                        try {
                            String idPelicula = extraerId(datos[2]);
                            String nombrePelicula = datos[3].trim();
                            String idActor = extraerId(datos[0]);
                            String nombreActor = datos[1].trim();

                            Integer anioPelicula = Integer.valueOf(elemento.substring(elemento.length() - 8, elemento.length() - 4));

                            Actor actor = listaActoresId.buscarActorPorId(idActor);
                            if (actor == null) {
                                actor = new Actor(idActor, nombreActor, new HashMap<>());
                                listaActoresId.agregarActorPorId(actor);
                            }

                            Pelicula pelicula = listaPeliculasId.buscarPeliculaPorId(idPelicula);
                            if (pelicula == null) {
                                pelicula = new Pelicula(idPelicula, nombrePelicula, anioPelicula, new HashMap<>());
                                listaPeliculasId.agregarPeliculaPorId(pelicula);
                            }

                            actor.agregarPelicula(pelicula);
                            pelicula.agregarActor(actor);
                            // contador++;

                            // if (contador % 25000 == 0) {
                            //     System.out.println("Líneas leídas: " + contador + "\t" + idActor + " ### " + nombreActor);
                            // }
                        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                            System.err.print(i18n.get("msg.error.procesando.linea", elemento, linea));
                        }
                    }
                }

            }

        } catch (Exception e) {
            System.err.println(i18n.get("msg.error.procesando.fichero", elemento));
        }
        return registros;

    }

    private String extraerId(String texto) {
        int pos = texto.indexOf("/entity/Q");
        if (pos != -1) {
            return String.valueOf(texto.substring(pos + 9).trim());
        }
        return String.valueOf(texto.trim());
    }
    // Se ha usado IA para obtener un listado de mensajes graciosos.
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
            Utils.ansiIrPosicion(1);
            Utils.ansiBorrarFila(2);
            System.out.print(MENSAJES_RAROS[index]);
        }
    }

}
