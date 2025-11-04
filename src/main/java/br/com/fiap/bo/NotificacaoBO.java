package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.NotificacaoDAO;
import br.com.fiap.to.Notificacao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NotificacaoBO {

    // Inserir notificação
    public String inserirNotificacao(Notificacao notificacao) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        NotificacaoDAO notificacaoDAO = new NotificacaoDAO(con);
        String resultado = notificacaoDAO.inserir(notificacao);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Atualizar notificação
    public String atualizarNotificacao(Notificacao notificacao) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        NotificacaoDAO notificacaoDAO = new NotificacaoDAO(con);
        String resultado = notificacaoDAO.alterar(notificacao);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Excluir notificação
    public String excluirNotificacao(Notificacao notificacao) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        NotificacaoDAO notificacaoDAO = new NotificacaoDAO(con);
        String resultado = notificacaoDAO.excluir(notificacao);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Buscar notificação por ID
    public String buscarNotificacaoPorId(Notificacao notificacao) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        NotificacaoDAO notificacaoDAO = new NotificacaoDAO(con);
        String resultado = notificacaoDAO.listarUm(notificacao);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    //  Listar todas notificações de um paciente específico
    public List<Notificacao> listarNotificacoesPorPaciente(int idPaciente) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        NotificacaoDAO notificacaoDAO = new NotificacaoDAO(con);
        List<Notificacao> notificacoes = notificacaoDAO.listarTodosPorPaciente(idPaciente);
        ConnectionFactory.closeConnection();
        return notificacoes;
    }

}
