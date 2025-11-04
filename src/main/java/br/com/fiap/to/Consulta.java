package br.com.fiap.to;

import java.time.LocalDateTime;

public class Consulta {

    // Atributos
    private int idConsulta;
    private int idPaciente; // FK para HC_PACIENTE
    private String tipo;    // TP_CONSULTA
    private String status;  // DS_STATUS
    private LocalDateTime dataHora; // DT_HORA_DATA

    // Construtor vazio
    public Consulta() {}

    // Construtor para INSERT
    public Consulta(int idPaciente, String tipo, String status, LocalDateTime dataHora) {
        this.idPaciente = idPaciente;
        this.tipo = tipo;
        this.status = status;
        this.dataHora = dataHora;
    }

    // Construtor para UPDATE
    public Consulta(int idConsulta, int idPaciente, String tipo, LocalDateTime dataHora) {
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.tipo = tipo;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public int getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
