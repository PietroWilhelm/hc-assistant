package br.com.fiap.dao;

import br.com.fiap.to.Notificacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO implements IDAO {

    private Connection con;
    private Notificacao notificacao;

    public NotificacaoDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        notificacao = (Notificacao) object;
        String sql = "INSERT INTO HC_NOTIFICACAO (DS_MENSAGEM, DT_ENVIO, DS_STATUS, ID_PACIENTE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, notificacao.getMensagem());
            ps.setDate(2, Date.valueOf(notificacao.getDataEnvio()));
            ps.setString(3, notificacao.getStatus()); // deve ser 'enviada' ou 'lida'
            ps.setInt(4, notificacao.getIdPaciente());

            if (ps.executeUpdate() > 0) {
                return "Notificação inserida com sucesso!";
            }
            return "Erro ao inserir notificação!";
        } catch (SQLException e) {
            return "Erro de SQL ao inserir notificação: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object object) {
        notificacao = (Notificacao) object;
        String sql = "UPDATE HC_NOTIFICACAO SET DS_MENSAGEM=?, DT_ENVIO=?, DS_STATUS=?, ID_PACIENTE=? WHERE ID_NOTIFICACAO=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, notificacao.getMensagem());
            ps.setDate(2, Date.valueOf(notificacao.getDataEnvio()));
            ps.setString(3, notificacao.getStatus());
            ps.setInt(4, notificacao.getIdPaciente());
            ps.setInt(5, notificacao.getIdNotificacao());

            if (ps.executeUpdate() > 0) {
                return "Notificação atualizada com sucesso!";
            }
            return "Nenhuma notificação encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao atualizar notificação: " + e.getMessage();
        }
    }

    @Override
    public String excluir(Object object) {
        notificacao = (Notificacao) object;
        String sql = "DELETE FROM HC_NOTIFICACAO WHERE ID_NOTIFICACAO=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, notificacao.getIdNotificacao());
            if (ps.executeUpdate() > 0) {
                return "Notificação excluída com sucesso!";
            }
            return "Nenhuma notificação encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao excluir notificação: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        notificacao = (Notificacao) object;
        String sql = "SELECT * FROM HC_NOTIFICACAO WHERE ID_NOTIFICACAO=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, notificacao.getIdNotificacao());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "ID Notificação: " + rs.getInt("ID_NOTIFICACAO") +
                        "\nMensagem: " + rs.getString("DS_MENSAGEM") +
                        "\nData de Envio: " + rs.getDate("DT_ENVIO").toLocalDate() +
                        "\nStatus: " + rs.getString("DS_STATUS") +
                        "\nID Paciente: " + rs.getInt("ID_PACIENTE");
            }
            return "Notificação não encontrada!";
        } catch (SQLException e) {
            return "Erro de SQL ao listar notificação: " + e.getMessage();
        }
    }

    @Override
    public List<Notificacao> listarTodosPorPaciente(int idPaciente) {
        List<Notificacao> notificacoes = new ArrayList<>();
        String sql = "SELECT * FROM HC_NOTIFICACAO WHERE ID_PACIENTE = ? ORDER BY DT_ENVIO DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notificacao n = new Notificacao();
                n.setIdNotificacao(rs.getInt("ID_NOTIFICACAO"));
                n.setMensagem(rs.getString("DS_MENSAGEM"));
                n.setDataEnvio(rs.getDate("DT_ENVIO").toLocalDate());
                n.setStatus(rs.getString("DS_STATUS"));
                n.setIdPaciente(rs.getInt("ID_PACIENTE"));
                notificacoes.add(n);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar notificações por paciente: " + e.getMessage());
        }
        return notificacoes;
    }
}
