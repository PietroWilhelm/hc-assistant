package br.com.fiap.bo;

import br.com.fiap.dao.AtendimentoDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.Atendimento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AtendimentoBO {

    // Inserir atendimento
    public String inserirAtendimento(Atendimento atendimento) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO(con);
        String resultado = atendimentoDAO.inserir(atendimento);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Atualizar atendimento
    public String atualizarAtendimento(Atendimento atendimento) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO(con);
        String resultado = atendimentoDAO.alterar(atendimento);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Excluir atendimento
    public String excluirAtendimento(Atendimento atendimento) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO(con);
        String resultado = atendimentoDAO.excluir(atendimento);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Buscar atendimento por ID
    public String buscarAtendimento(Atendimento atendimento) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO(con);
        String resultado = atendimentoDAO.listarUm(atendimento);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Listar Atendimento Especifico por paciente
    public List<Atendimento> listarAtendimentosPorPaciente(int idPaciente) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO(con);
        List<Atendimento> lista = atendimentoDAO.listarTodosPorPaciente(idPaciente);
        ConnectionFactory.closeConnection();
        return lista;
    }
}
