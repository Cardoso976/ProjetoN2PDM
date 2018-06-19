package com.example.rodrigo.quizpro;

public class Pergunta {

    private String pergunta, altA, altB, altC, altD, altCorreta;
    private boolean respondido;

    public Pergunta(String pergunta, String altA, String altB, String altC, String altD, String altCorreta) {
        this.pergunta = pergunta;
        this.altA = altA;
        this.altB = altB;
        this.altC = altC;
        this.altD = altD;
        this.altCorreta = altCorreta;
    }

    public Pergunta(){

    }

    public String getPergunta() {

        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getAltA() {
        return altA;
    }

    public void setAltA(String altA) {
        this.altA = altA;
    }

    public String getAltB() {
        return altB;
    }

    public void setAltB(String altB) {
        this.altB = altB;
    }

    public String getAltC() {
        return altC;
    }

    public void setAltC(String altC) {
        this.altC = altC;
    }

    public String getAltD() {
        return altD;
    }

    public void setAltD(String altD) {
        this.altD = altD;
    }

    public String getAltCorreta() {
        return altCorreta;
    }

    public void setAltCorreta(String altCorreta) {
        this.altCorreta = altCorreta;
    }

    public boolean isRespondido() {
        return respondido;
    }

    public void setRespondido(boolean respondido) {
        this.respondido = respondido;
    }

    @Override
    public String toString() {
        return pergunta + "\n"+ (respondido ? "Já foi respondido.": "Não respondida");
    }
}
