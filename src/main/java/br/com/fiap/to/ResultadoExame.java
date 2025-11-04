package br.com.fiap.to;

import java.time.LocalDate;

public class ResultadoExame {

    private int idResultado;
    private LocalDate dataResultado;
    private String descricaoResultado;
    private String fichaMedica;
    private String instituicao;
    private int idPaciente;

    // Extras para mostrar o vínculo
    private String nomePaciente;
    private String cpfPaciente;

    // Construtores
    public ResultadoExame() {}

    public ResultadoExame(LocalDate dataResultado, String descricaoResultado, String fichaMedica, String instituicao, int idPaciente) {
        this.dataResultado = dataResultado;
        this.descricaoResultado = descricaoResultado;
        this.fichaMedica = fichaMedica;
        this.instituicao = instituicao;
        this.idPaciente = idPaciente;
    }

    public ResultadoExame(int idResultado, LocalDate dataResultado, String descricaoResultado, String fichaMedica, String instituicao, int idPaciente) {
        this.idResultado = idResultado;
        this.dataResultado = dataResultado;
        this.descricaoResultado = descricaoResultado;
        this.fichaMedica = fichaMedica;
        this.instituicao = instituicao;
        this.idPaciente = idPaciente;
    }


    // Getters e Setters
    public int getIdResultado() { return idResultado; }
    public void setIdResultado(int idResultado) { this.idResultado = idResultado; }

    public LocalDate getDataResultado() { return dataResultado; }
    public void setDataResultado(LocalDate dataResultado) { this.dataResultado = dataResultado; }

    public String getDescricaoResultado() { return descricaoResultado; }
    public void setDescricaoResultado(String descricaoResultado) { this.descricaoResultado = descricaoResultado; }

    public String getFichaMedica() { return fichaMedica; }
    public void setFichaMedica(String fichaMedica) { this.fichaMedica = fichaMedica; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }

    public String getCpfPaciente() { return cpfPaciente; }
    public void setCpfPaciente(String cpfPaciente) { this.cpfPaciente = cpfPaciente; }
}
