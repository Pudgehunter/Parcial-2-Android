package com.example.parcialiipuntajes;

public class Pregunta {

    private String pregunta;
    private String puntaje;
    private String promedio;
    private String promedioResultado;

    public Pregunta(){}

    public Pregunta(String pregunta, String puntaje, String promedio, String promedioResultado){
        this.pregunta = pregunta;
        this.puntaje = puntaje;
        this.promedio = promedio;
        this.promedioResultado = promedioResultado;
    }

    //Solamente pegar los puntajes ahí en el firebase para que se pinte los resultados.


    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getPromedioResultado() {
        return promedioResultado;
    }

    public void setPromedioResultado(String promedioResultado) {
        this.promedioResultado = promedioResultado;
    }
}
