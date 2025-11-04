package br.com.fiap.bo;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.FaqDAO;
import br.com.fiap.to.Faq;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FaqBO {

    //  Cadastrar FAQ
    public String cadastrarFaq(Faq faq) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        FaqDAO faqDAO = new FaqDAO(con);
        String resultado = faqDAO.inserir(faq);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Atualizar FAQ
    public String atualizarFaq(Faq faq) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        FaqDAO faqDAO = new FaqDAO(con);
        String resultado = faqDAO.alterar(faq);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Excluir FAQ
    public String excluirFaq(Faq faq) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        FaqDAO faqDAO = new FaqDAO(con);
        String resultado = faqDAO.excluir(faq);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Buscar FAQ por ID
    public String buscarFaqPorId(int idFaq) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        Faq f = new Faq();
        f.setIdFaq(idFaq);
        FaqDAO faqDAO = new FaqDAO(con);
        String resultado = faqDAO.listarUm(f);
        ConnectionFactory.closeConnection();
        return resultado;
    }

    // Listar todos os FAQs
    public List<Faq> listarTodosFaqs() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConnection();
        FaqDAO faqDAO = new FaqDAO(con);
        List<Faq> lista = faqDAO.listarTodos();
        ConnectionFactory.closeConnection();
        return lista;
    }
}
