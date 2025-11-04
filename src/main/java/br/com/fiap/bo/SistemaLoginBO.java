package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.SistemaLoginDAO;
import br.com.fiap.to.SistemaLogin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SistemaLoginBO {
    private Connection con;

    public void inserir(SistemaLogin login) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        SistemaLoginDAO dao = new SistemaLoginDAO(con); // corrigido aqui
        dao.inserir(login);
        ConnectionFactory.closeConnection();
    }


    public List<SistemaLogin> listarTodos() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        SistemaLoginDAO dao = new SistemaLoginDAO(con);
        List<SistemaLogin> logins = dao.listarTodos();
        ConnectionFactory.closeConnection();
        return logins;
    }
}
