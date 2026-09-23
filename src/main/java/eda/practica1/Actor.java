package eda.practica1;

import java.util.List;

public class Actor {
  Integer idActor;
  String NombreActor;
  List<ListaPeliculas> ListaPeliculas;
    
    public Actor (Integer idActor, String NombreActor, List<ListaPeliculas> ListaPeliculas){
      
      idActor = idActor;
      NombreActor = NombreActor;
      ListaPeliculas = ListaPeliculas;
    }
}