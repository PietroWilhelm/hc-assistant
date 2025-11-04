package br.com.fiap.dao;

import br.com.fiap.to.Faq;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaqDAO implements IDAO {

    private Connection con;
    private Faq faq;

    public FaqDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        faq = (Faq) object;
        String sql = "INSERT INTO HC_FAQ (ds_pergunta, ds_resposta) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, faq.getPergunta());
            ps.setString(2, faq.getResposta());

            if (ps.executeUpdate() > 0) {
                return "FAQ inserido com sucesso!";
            }
            return "Erro ao inserir FAQ!";
        } catch (SQLException e) {
            return "Erro de SQL ao inserir FAQ: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object object) {
        faq = (Faq) object;
        String sql = "UPDATE HC_FAQ SET ds_pergunta=?, ds_resposta=? WHERE id_faq=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, faq.getPergunta());
            ps.setString(2, faq.getResposta());
            ps.setInt(3, faq.getIdFaq());

            if (ps.executeUpdate() > 0) {
                return "FAQ atualizado com sucesso!";
            }
            return "Nenhum FAQ encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao atualizar FAQ: " + e.getMessage();
        }
    }

    @Override
    public String excluir(Object object) {
        faq = (Faq) object;
        String sql = "DELETE FROM HC_FAQ WHERE id_faq=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, faq.getIdFaq());
            if (ps.executeUpdate() > 0) {
                return "FAQ excluído com sucesso!";
            }
            return "Nenhum FAQ encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao excluir FAQ: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        faq = (Faq) object;
        String sql = "SELECT * FROM HC_FAQ WHERE id_faq=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, faq.getIdFaq());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "ID: " + rs.getInt("id_faq") +
                        "\nPergunta: " + rs.getString("ds_pergunta") +
                        "\nResposta: " + rs.getString("ds_resposta");
            }
            return "FAQ não encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao listar FAQ: " + e.getMessage();
        }
    }

    public List<Faq> listarTodos() {
        List<Faq> lista = new ArrayList<>();
        String sql = "SELECT * FROM HC_FAQ ORDER BY id_faq";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Faq f = new Faq();
                f.setIdFaq(rs.getInt("id_faq"));
                f.setPergunta(rs.getString("ds_pergunta"));
                f.setResposta(rs.getString("ds_resposta"));
                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL ao listar todos os FAQs: " + e.getMessage());
        }
        return lista;
    }
}
