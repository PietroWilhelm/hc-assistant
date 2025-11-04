package br.com.fiap.dao;

import br.com.fiap.to.Suporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuporteDAO implements IDAO {

    private Connection con;
    private Suporte suporte;

    public SuporteDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        suporte = (Suporte) object;
        String sql = "INSERT INTO HC_SUPORTE (DS_TIPO, NR_CONTATO) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, suporte.getTipo());
            ps.setLong(2, Long.parseLong(suporte.getContato()));

            if (ps.executeUpdate() > 0) {
                return "Suporte inserido com sucesso!";
            }
            return "Erro ao inserir suporte!";
        } catch (SQLException e) {
            return "Erro de SQL ao inserir suporte: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Número de contato inválido! Deve conter apenas números.";
        }
    }

    @Override
    public String alterar(Object object) {
        suporte = (Suporte) object;
        String sql = "UPDATE HC_SUPORTE SET DS_TIPO=?, NR_CONTATO=? WHERE ID_SUPORTE=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, suporte.getTipo());
            ps.setLong(2, Long.parseLong(suporte.getContato()));
            ps.setInt(3, suporte.getIdSuporte());

            if (ps.executeUpdate() > 0) {
                return "Suporte atualizado com sucesso!";
            }
            return "Nenhum suporte encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao atualizar suporte: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Número de contato inválido! Deve conter apenas números.";
        }
    }

    @Override
    public String excluir(Object object) {
        suporte = (Suporte) object;
        String sql = "DELETE FROM HC_SUPORTE WHERE ID_SUPORTE=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, suporte.getIdSuporte());
            if (ps.executeUpdate() > 0) {
                return "Suporte excluído com sucesso!";
            }
            return "Nenhum suporte encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao excluir suporte: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        suporte = (Suporte) object;
        String sql = "SELECT * FROM HC_SUPORTE WHERE ID_SUPORTE=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, suporte.getIdSuporte());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "ID Suporte: " + rs.getInt("ID_SUPORTE") +
                        "\nTipo: " + rs.getString("DS_TIPO") +
                        "\nContato: " + rs.getLong("NR_CONTATO");
            }
            return "Suporte não encontrado!";
        } catch (SQLException e) {
            return "Erro de SQL ao listar suporte: " + e.getMessage();
        }
    }

    // Utilitário extra: listar todos os registros de suporte
    public List<Suporte> listarTodos() {
        List<Suporte> lista = new ArrayList<>();
        String sql = "SELECT * FROM HC_SUPORTE ORDER BY ID_SUPORTE";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Suporte s = new Suporte();
                s.setIdSuporte(rs.getInt("ID_SUPORTE"));
                s.setTipo(rs.getString("DS_TIPO"));
                s.setContato(String.valueOf(rs.getLong("NR_CONTATO")));
                lista.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL ao listar suportes: " + e.getMessage());
        }
        return lista;
    }
}
