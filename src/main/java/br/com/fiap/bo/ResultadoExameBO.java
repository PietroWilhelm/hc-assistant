package br.com.fiap.bo;

import br.com.fiap.dao.ResultadoExameDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.ResultadoExame;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ResultadoExameBO {

    public String inserir(ResultadoExame resultado) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        ResultadoExameDAO dao = new ResultadoExameDAO(conexao);
        String resposta = dao.inserir(resultado);
        ConnectionFactory.closeConnection();
        return resposta;
    }

    public String atualizar(ResultadoExame resultado) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        ResultadoExameDAO dao = new ResultadoExameDAO(conexao);
        String resposta = dao.alterar(resultado);
        ConnectionFactory.closeConnection();
        return resposta;
    }

    public String excluir(ResultadoExame resultado) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        ResultadoExameDAO dao = new ResultadoExameDAO(conexao);
        String resposta = dao.excluir(resultado);
        ConnectionFactory.closeConnection();
        return resposta;
    }

    public String buscarPorId(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        ResultadoExame resultado = new ResultadoExame();
        resultado.setIdResultado(id);
        ResultadoExameDAO dao = new ResultadoExameDAO(conexao);
        String resposta = dao.listarUm(resultado);
        ConnectionFactory.closeConnection();
        return resposta;
    }

    public List<ResultadoExame> listarPorPaciente(int idPaciente) throws SQLException, ClassNotFoundException {
        Connection conexao = ConnectionFactory.getConnection();
        ResultadoExameDAO dao = new ResultadoExameDAO(conexao);
        List<ResultadoExame> lista = dao.listarTodosPorPaciente(idPaciente);
        ConnectionFactory.closeConnection();
        return lista;
    }
}
