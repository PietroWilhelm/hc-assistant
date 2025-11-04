package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.SuporteDAO;
import br.com.fiap.to.Suporte;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SuporteBO {

    // Inserir suporte
    public String inserir(Suporte suporte) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        SuporteDAO suporteDAO = new SuporteDAO(con);
        String resultado = suporteDAO.inserir(suporte);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Atualizar suporte
    public String atualizar(Suporte suporte) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        SuporteDAO suporteDAO = new SuporteDAO(con);
        String resultado = suporteDAO.alterar(suporte);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Excluir suporte
    public String excluir(Suporte suporte) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        SuporteDAO suporteDAO = new SuporteDAO(con);
        String resultado = suporteDAO.excluir(suporte);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Buscar suporte por ID
    public String buscarPorId(Suporte suporte) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        SuporteDAO suporteDAO = new SuporteDAO(con);
        String resultado = suporteDAO.listarUm(suporte);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    public List<Suporte> listarTodosSuportes() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        SuporteDAO suporteDAO = new SuporteDAO(con);
        List<Suporte> resultado = suporteDAO.listarTodos(); // CERTO
        ConnectionFactory.closeConnection();
        return resultado;
    }
}
