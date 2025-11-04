package br.com.fiap.dao;

import br.com.fiap.to.ResultadoExame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultadoExameDAO implements IDAO {

    private Connection con;
    private ResultadoExame resultadoExame;

    public ResultadoExameDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public String inserir(Object object) {
        resultadoExame = (ResultadoExame) object;
        String sql = "INSERT INTO HC_RESULTADO_EXAME " +
                "(DT_RESULTADO, DS_RESULTADO, DS_FICHA_MEDICA, NM_INSTITUICAO, ID_PACIENTE) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(resultadoExame.getDataResultado()));
            ps.setString(2, resultadoExame.getDescricaoResultado());         // 'positivo','negativo','inconclusivo'
            ps.setString(3, resultadoExame.getFichaMedica());
            ps.setString(4, resultadoExame.getInstituicao());
            ps.setInt(5, resultadoExame.getIdPaciente());

            if (ps.executeUpdate() > 0) {
                return "Resultado de exame inserido com sucesso!";
            }
            return "Erro ao inserir resultado de exame!";
        } catch (SQLException e) {
            return "Erro SQL: " + e.getMessage();
        }
    }

    @Override
    public String alterar(Object object) {
        resultadoExame = (ResultadoExame) object;
        String sql = "UPDATE HC_RESULTADO_EXAME SET " +
                "DT_RESULTADO=?, DS_RESULTADO=?, DS_FICHA_MEDICA=?, NM_INSTITUICAO=?, ID_PACIENTE=? " +
                "WHERE ID_RESULTADO=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(resultadoExame.getDataResultado()));
            ps.setString(2, resultadoExame.getDescricaoResultado());
            ps.setString(3, resultadoExame.getFichaMedica());
            ps.setString(4, resultadoExame.getInstituicao());
            ps.setInt(5, resultadoExame.getIdPaciente());
            ps.setInt(6, resultadoExame.getIdResultado());

            if (ps.executeUpdate() > 0) {
                return "Resultado de exame atualizado com sucesso!";
            }
            return "Nenhum resultado de exame encontrado!";
        } catch (SQLException e) {
            return "Erro SQL: " + e.getMessage();
        }
    }

    @Override
    public String excluir(Object object) {
        resultadoExame = (ResultadoExame) object;
        String sql = "DELETE FROM HC_RESULTADO_EXAME WHERE ID_RESULTADO=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, resultadoExame.getIdResultado());
            if (ps.executeUpdate() > 0) {
                return "Resultado de exame excluído com sucesso!";
            }
            return "Nenhum resultado de exame encontrado!";
        } catch (SQLException e) {
            return "Erro SQL: " + e.getMessage();
        }
    }

    @Override
    public String listarUm(Object object) {
        resultadoExame = (ResultadoExame) object;
        String sql = "SELECT * FROM HC_RESULTADO_EXAME WHERE ID_RESULTADO=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, resultadoExame.getIdResultado());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "ID Resultado: " + rs.getInt("ID_RESULTADO") +
                        "\nData: " + rs.getDate("DT_RESULTADO").toLocalDate() +
                        "\nResultado: " + rs.getString("DS_RESULTADO") +
                        "\nFicha Médica: " + rs.getString("DS_FICHA_MEDICA") +
                        "\nInstituição: " + rs.getString("NM_INSTITUICAO") +
                        "\nID Paciente: " + rs.getInt("ID_PACIENTE");
            }
            return "Resultado de exame não encontrado!";
        } catch (SQLException e) {
            return "Erro SQL: " + e.getMessage();
        }
    }

    @Override
    public List<ResultadoExame> listarTodosPorPaciente(int idPaciente) {
        List<ResultadoExame> exames = new ArrayList<>();
        String sql = "SELECT * FROM HC_RESULTADO_EXAME WHERE ID_PACIENTE = ? ORDER BY DT_RESULTADO DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ResultadoExame r = new ResultadoExame();
                r.setIdResultado(rs.getInt("ID_RESULTADO"));
                r.setDataResultado(rs.getDate("DT_RESULTADO").toLocalDate());
                r.setDescricaoResultado(rs.getString("DS_RESULTADO"));
                r.setFichaMedica(rs.getString("DS_FICHA_MEDICA"));
                r.setInstituicao(rs.getString("NM_INSTITUICAO"));
                r.setIdPaciente(rs.getInt("ID_PACIENTE"));
                exames.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar exames por paciente: " + e.getMessage());
        }
        return exames;
    }
}
