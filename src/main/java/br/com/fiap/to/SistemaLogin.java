package br.com.fiap.to;

import java.time.LocalDate;

public class SistemaLogin {
    private int idLogin;
    private String tipoLogin;
    private int codigoVerificacao;
    private LocalDate dataEnvio;
    private String status;
    private int idPaciente;

    // Construtores
    public SistemaLogin() {}

    public SistemaLogin(int idLogin, String tipoLogin, int codigoVerificacao, LocalDate dataEnvio, String status, int idPaciente) {
        this.idLogin = idLogin;
        this.tipoLogin = tipoLogin;
        this.codigoVerificacao = codigoVerificacao;
        this.dataEnvio = dataEnvio;
        this.status = status;
        this.idPaciente = idPaciente;
    }

    // Getters e Setters
    public int getIdLogin() { return idLogin; }
    public void setIdLogin(int idLogin) { this.idLogin = idLogin; }

    public String getTipoLogin() { return tipoLogin; }
    public void setTipoLogin(String tipoLogin) { this.tipoLogin = tipoLogin; }

    public int getCodigoVerificacao() { return codigoVerificacao; }
    public void setCodigoVerificacao(int codigoVerificacao) { this.codigoVerificacao = codigoVerificacao; }

    public LocalDate getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDate dataEnvio) { this.dataEnvio = dataEnvio; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
}
