package br.com.fiap.to;
public class Cuidador {

    private int idCuidador;
    private String nome;
    private String telefone;
    private int idPaciente; // FK para HC_PACIENTE

    // Construtor para INSERT
    public Cuidador(String nome, String telefone, int idPaciente) {
        this.nome = nome;
        this.telefone = telefone;
        this.idPaciente = idPaciente;
    }

    // Construtor para UPDATE
    public Cuidador(int idCuidador, String nome, String telefone, int idPaciente) {
        this.idCuidador = idCuidador;
        this.nome = nome;
        this.telefone = telefone;
        this.idPaciente = idPaciente;
    }

    // Construtor vazio
    public Cuidador() {}

    // Getters e Setters
    public int getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(int idCuidador) {
        this.idCuidador = idCuidador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
