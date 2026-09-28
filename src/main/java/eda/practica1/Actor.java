package eda.practica1;

public class Actor {

    String idActor;
    String nombreActor;
    boolean activo;

    public Actor(String idActor, String nombreActor) {

        this.idActor = idActor;
        this.nombreActor = nombreActor;
        this.activo = true;
    }

    public String getIdActor() {
        return idActor;
    }

    public void setIdActor(String idActor) {
        this.idActor = idActor;
    }

    public String getNombreActor() {
        return nombreActor;
    }

    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
