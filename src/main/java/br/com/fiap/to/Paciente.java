package br.com.fiap.to;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Paciente {
    //Atributos
    private int idPaciente;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private boolean possuiCuidador;
    private String email;


    //Construtores
    public Paciente(int idPaciente ,String nome, String cpf, LocalDate dataNascimento, String telefone, String email) {
        this.idPaciente = idPaciente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
    }

    // Construtor para INSERT (sem idPaciente, sem possuiCuidador)
    public Paciente(String nome, String cpf, LocalDate dataNascimento, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
    }

    // construtor para update sem CPF
    public Paciente(int idPaciente, String nome, LocalDate dataNascimento, String telefone, String email) {
        this.idPaciente = idPaciente;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
    }



    public Paciente() {
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isPossuiCuidador() {
        return possuiCuidador;
    }

    public void setPossuiCuidador(boolean possuiCuidador) {
        this.possuiCuidador = possuiCuidador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    //Métodos da Classe
    public static String lerCpf() {
        String cpf = "";
        boolean valido = false;

        do {
            try {
                cpf = JOptionPane.showInputDialog(null, "CPF (Deve conter 11 dígitos):", "CPF", JOptionPane.QUESTION_MESSAGE);
                if (cpf == null) System.exit(0);

                if (!cpf.matches("\\d{11}")) {
                    throw new Exception("CPF deve conter exatamente 11 dígitos numéricos.");
                }

                valido = true;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro de CPF", JOptionPane.ERROR_MESSAGE);
            }
        } while (!valido);

        return cpf;
    }

    public static LocalDate lerDataNascimento(String mensagem) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;
        boolean valido = false;

        do {
            try {
                String entrada = JOptionPane.showInputDialog(null, mensagem, "Data de nascimento", JOptionPane.QUESTION_MESSAGE);
                if (entrada == null) System.exit(0);

                data = LocalDate.parse(entrada, formatoData);

                if (data.getYear() < 1940) {
                    throw new Exception("Ano de nascimento não pode ser anterior a 1940.");
                }

                if (data.isAfter(LocalDate.now())) {
                    throw new Exception("Data de nascimento não pode ser no futuro.");
                }

                valido = true;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro de Data", JOptionPane.ERROR_MESSAGE);
            }
        } while (!valido);

        return data;
    }
}
