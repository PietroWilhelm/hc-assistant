package br.com.fiap.to;

import javax.swing.*;

public class Faq {

    private int idFaq;
    private String pergunta;
    private String resposta;

    // Construtor para INSERT
    public Faq(String pergunta, String resposta) {
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    // Construtor para UPDATE
    public Faq(int idFaq, String pergunta, String resposta) {
        this.idFaq = idFaq;
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public Faq() {}

    // Getters e Setters
    public int getIdFaq() {
        return idFaq;
    }

    public void setIdFaq(int idFaq) {
        this.idFaq = idFaq;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

}
