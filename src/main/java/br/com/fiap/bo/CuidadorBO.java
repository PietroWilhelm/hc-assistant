package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.CuidadorDAO;
import br.com.fiap.to.Cuidador;

import java.sql.Connection;
import java.util.ArrayList;

public class CuidadorBO {

    //  Listar todos os cuidadores
    public ArrayList<Cuidador> listarTodos() {
        Connection con = ConnectionFactory.getConnection();
        CuidadorDAO cuidadorDAO = new CuidadorDAO(con);
        ArrayList<Cuidador> lista = cuidadorDAO.listarTodos();
        ConnectionFactory.closeConnection();
        return lista;
    }

    //  Buscar cuidador por ID
    public Cuidador buscarPorId(int idCuidador) {
        Connection con = ConnectionFactory.getConnection();
        CuidadorDAO cuidadorDAO = new CuidadorDAO(con);
        Cuidador cuidador = cuidadorDAO.buscarPorId(idCuidador);
        ConnectionFactory.closeConnection();
        return cuidador;
    }

    //  Cadastrar cuidador
    public Cuidador save(Cuidador cuidador) {
        Connection con = ConnectionFactory.getConnection();
        CuidadorDAO cuidadorDAO = new CuidadorDAO(con);
        Cuidador novoCuidador = cuidadorDAO.inserir(cuidador);
        ConnectionFactory.closeConnection();
        return novoCuidador;
    }

    //  Atualizar cuidador
    public Cuidador update(Cuidador cuidador) {
        Connection con = ConnectionFactory.getConnection();
        CuidadorDAO cuidadorDAO = new CuidadorDAO(con);
        Cuidador atualizado = cuidadorDAO.alterar(cuidador);
        ConnectionFactory.closeConnection();
        return atualizado;
    }

    //  Excluir cuidador
    public boolean delete(int idCuidador) {
        Connection con = ConnectionFactory.getConnection();
        CuidadorDAO cuidadorDAO = new CuidadorDAO(con);
        boolean resultado = cuidadorDAO.excluir(idCuidador);
        ConnectionFactory.closeConnection();
        return resultado;
    }
}
