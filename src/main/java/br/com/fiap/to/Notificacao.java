package br.com.fiap.to;

import java.time.LocalDate;

public class Notificacao {

    // Atributos
    private int idNotificacao;
    private String mensagem;
    private LocalDate dataEnvio;
    private String status;
    private int idPaciente;

    // Construtores
    public Notificacao() {}

    public Notificacao(String mensagem, LocalDate dataEnvio, String status, int idPaciente) {
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.status = status;
        this.idPaciente = idPaciente;
    }

    public Notificacao(int idNotificacao, String mensagem, LocalDate dataEnvio, String status, int idPaciente) {
        this.idNotificacao = idNotificacao;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.status = status;
        this.idPaciente = idPaciente;
    }

    // Getters e Setters
    public int getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(int idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
