package com.example.rodrigo.quizpro;

public class Produto {

    private String questao;
    private String altA, altB, altC, altD, altCorreta;

    public Produto(String questao, String altA, String altB, String altC, String altD, String altCorreta) {
        this.questao = questao;
        this.altA = altA;
        this.altB = altB;
        this.altC = altC;
        this.altD = altD;
        this.altCorreta = altCorreta;
    }

    public String getQuestao() {

        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
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
}
