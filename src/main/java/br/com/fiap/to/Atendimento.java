package br.com.fiap.to;
import java.time.LocalDate;

public class Atendimento {
    private LocalDate dataAtendimento;
    private int idPaciente;
    private int idFaq;
    private int idSuporte;

    // Construtores
    public Atendimento() {}

    public Atendimento(LocalDate dataAtendimento, int idPaciente, int idFaq, int idSuporte) {
        this.dataAtendimento = dataAtendimento;
        this.idPaciente = idPaciente;
        this.idFaq = idFaq;
        this.idSuporte = idSuporte;
    }

    // Getters e Setters
    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdFaq() {
        return idFaq;
    }

    public void setIdFaq(int idFaq) {
        this.idFaq = idFaq;
    }

    public int getIdSuporte() {
        return idSuporte;
    }

    public void setIdSuporte(int idSuporte) {
        this.idSuporte = idSuporte;
    }
}
