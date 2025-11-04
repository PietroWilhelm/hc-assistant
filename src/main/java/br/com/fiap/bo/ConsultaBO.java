package br.com.fiap.bo;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.to.Consulta;

import java.sql.SQLException;
import java.util.List;

public class ConsultaBO {

    private final ConsultaDAO dao = new ConsultaDAO();

    public String cadastrarConsulta(Consulta consulta) throws SQLException, ClassNotFoundException {
        return dao.inserir(consulta);

    }

    public String atualizarConsulta(Consulta consulta) throws SQLException, ClassNotFoundException {
        return dao.alterar(consulta);
    }

    public String excluirConsulta(int idConsulta) throws SQLException, ClassNotFoundException {
        return dao.excluir(idConsulta);
    }

    public List<Consulta> listarTodasConsultas() throws SQLException, ClassNotFoundException {
        return dao.listarTodos();
    }

    public Consulta buscarConsultaPorId(int idConsulta) throws SQLException, ClassNotFoundException {
        return dao.listarUm(idConsulta);
    }
}
