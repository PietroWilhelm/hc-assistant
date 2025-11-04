package br.com.fiap.to;

import javax.swing.*;

public class Suporte {

    //Atributos
    private int idSuporte;
    private String tipo;
    private String contato;

    //Construtores
    public Suporte(int idSuporte,String tipo, String contato) {
        this.idSuporte = idSuporte;
        this.tipo = tipo;
        this.contato = contato;
    }

    public Suporte(String tipo, String contato) {
        this.tipo = tipo;
        this.contato = contato;
    }

    public Suporte() {
    }

    //Getters e Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public int getIdSuporte() {
        return idSuporte;
    }

    public void setIdSuporte(int idSuporte) {
        this.idSuporte = idSuporte;
    }

    //Métodos da Classe
    public void iniciarAtendimento() {
        JOptionPane.showMessageDialog(null,
                " Atendimento iniciado via " + tipo + "\nContato: " + contato,
                "Suporte", JOptionPane.INFORMATION_MESSAGE);
    }

    public void finalizarAtendimento() {
        JOptionPane.showMessageDialog(null,
                " Atendimento finalizado. Obrigado por utilizar nosso suporte!",
                "Suporte", JOptionPane.INFORMATION_MESSAGE);
    }
}
