package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.to.Paciente;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class PacienteBO {

    //  Buscar todos os pacientes
    public ArrayList<Paciente> listarTodos() {
        ArrayList<Paciente> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection()) {
            PacienteDAO dao = new PacienteDAO(con);
            lista = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
        return lista;
    }

    // Buscar paciente por ID
    public Paciente buscarPacientePorId(int idPaciente) {
        Paciente paciente = null;
        try (Connection con = ConnectionFactory.getConnection()) {
            PacienteDAO dao = new PacienteDAO(con);
            paciente = dao.buscarPorId(idPaciente);
        } catch (Exception e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return paciente;
    }

    // Inserir paciente
    public Paciente cadastrarPaciente(Paciente paciente) {
        try (Connection con = ConnectionFactory.getConnection()) {
            PacienteDAO dao = new PacienteDAO(con);
            return dao.inserir(paciente);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
        return null;
    }

    // Atualizar paciente
    public Paciente atualizarPaciente(Paciente paciente) {
        try (Connection con = ConnectionFactory.getConnection()) {
            PacienteDAO dao = new PacienteDAO(con);
            return dao.alterar(paciente);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
        return null;
    }

    // Excluir paciente
    public boolean excluirPaciente(int idPaciente) {
        try (Connection con = ConnectionFactory.getConnection()) {
            PacienteDAO dao = new PacienteDAO(con);
            return dao.excluir(idPaciente);
        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        }
        return false;
    }

    public Paciente buscarPorCpfEDataNascimento(String cpf, LocalDate dataNascimento) {
        try (Connection con = ConnectionFactory.getConnection()) {
            PacienteDAO dao = new PacienteDAO(con);
            return dao.buscarPorCpfEDataNascimento(cpf, dataNascimento);
        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        }
        return null;
    }

}
