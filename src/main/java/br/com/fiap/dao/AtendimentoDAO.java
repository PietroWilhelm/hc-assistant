package br.com.fiap.dao;

import br.com.fiap.to.Atendimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO implements IDAO {

    private Connection con;
    private Atendimento atendimento;

    public AtendimentoDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        atendimento = (Atendimento) object;
        String sql = "INSERT INTO HC_ATENDIMENTO (DT_ATENDIMENTO, ID_PACIENTE, ID_FAQ, ID_SUPORTE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(atendimento.getDataAtendimento()));
            ps.setInt(2, atendimento.getIdPaciente());
            ps.setInt(3, atendimento.getIdFaq());
            ps.setInt(4, atendimento.getIdSuporte());

            if (ps.executeUpdate() > 0) {
                return "Atendimento registrado com sucesso!";
            }
            return "Erro ao inserir atendimento!";
        } catch (SQLException e) {
            return "Erro de SQL ao inserir atendimento: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object object) {
        atendimento = (Atendimento) object;
        // Como a PK é composta, não podemos alterar os IDs de chave, apenas a data
        String sql = "UPDATE HC_ATENDIMENTO SET DT_ATENDIMENTO=? " +
                "WHERE ID_FAQ=? AND ID_SUPORTE=? AND ID_PACIENTE=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(atendimento.getDataAtendimento()));
            ps.setInt(2, atendimento.getIdFaq());
            ps.setInt(3, atendimento.getIdSuporte());
            ps.setInt(4, atendimento.getIdPaciente());

            if (ps.executeUpdate() > 0) {
                return "Atendimento atualizado com sucesso!";
            }
            return "Nenhum atendimento encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao atualizar atendimento: " + e.getMessage();
        }
    }

    @Override
    public String excluir(Object object) {
        atendimento = (Atendimento) object;
        String sql = "DELETE FROM HC_ATENDIMENTO WHERE ID_FAQ=? AND ID_SUPORTE=? AND ID_PACIENTE=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, atendimento.getIdFaq());
            ps.setInt(2, atendimento.getIdSuporte());
            ps.setInt(3, atendimento.getIdPaciente());

            if (ps.executeUpdate() > 0) {
                return "Atendimento excluído com sucesso!";
            }
            return "Nenhum atendimento encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao excluir atendimento: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        atendimento = (Atendimento) object;
        String sql = "SELECT * FROM HC_ATENDIMENTO WHERE ID_FAQ=? AND ID_SUPORTE=? AND ID_PACIENTE=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, atendimento.getIdFaq());
            ps.setInt(2, atendimento.getIdSuporte());
            ps.setInt(3, atendimento.getIdPaciente());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Atendimento:" +
                        "\nData: " + rs.getDate("DT_ATENDIMENTO").toLocalDate() +
                        "\nID Paciente: " + rs.getInt("ID_PACIENTE") +
                        "\nID FAQ: " + rs.getInt("ID_FAQ") +
                        "\nID Suporte: " + rs.getInt("ID_SUPORTE");
            }
            return "Atendimento não encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao listar atendimento: " + e.getMessage();
        }
    }

    //  listar atendimentos de um paciente
    public List<Atendimento> listarTodosPorPaciente(int idPaciente) {
        List<Atendimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM HC_ATENDIMENTO WHERE ID_PACIENTE=? ORDER BY DT_ATENDIMENTO DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Atendimento a = new Atendimento();
                a.setDataAtendimento(rs.getDate("DT_ATENDIMENTO").toLocalDate());
                a.setIdPaciente(rs.getInt("ID_PACIENTE"));
                a.setIdFaq(rs.getInt("ID_FAQ"));
                a.setIdSuporte(rs.getInt("ID_SUPORTE"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL ao listar atendimentos: " + e.getMessage());
        }
        return lista;
    }
}
