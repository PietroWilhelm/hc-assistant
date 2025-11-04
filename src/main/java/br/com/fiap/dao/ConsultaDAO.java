package br.com.fiap.dao;

import br.com.fiap.to.Consulta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public String inserir(Consulta consulta) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO HC_CONSULTA (id_paciente, tp_consulta, ds_status, dt_hora_data) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, consulta.getIdPaciente());
            ps.setString(2, consulta.getTipo());
            ps.setString(3, consulta.getStatus());
            ps.setTimestamp(4, Timestamp.valueOf(consulta.getDataHora()));
            ps.executeUpdate();
            return "Consulta agendada com sucesso!";
        }
    }

    public String alterar(Consulta consulta) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE HC_CONSULTA SET tp_consulta=?, ds_status=?, dt_hora_data=? WHERE id_consulta=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, consulta.getTipo());
            ps.setString(2, consulta.getStatus());
            ps.setTimestamp(3, Timestamp.valueOf(consulta.getDataHora()));
            ps.setInt(4, consulta.getIdConsulta());
            ps.executeUpdate();
            return "Consulta atualizada com sucesso!";
        }
    }

    public String excluir(int idConsulta) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM HC_CONSULTA WHERE id_consulta=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idConsulta);
            ps.executeUpdate();
            return "Consulta cancelada com sucesso!";
        }
    }

    public List<Consulta> listarTodos() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM HC_CONSULTA";
        List<Consulta> consultas = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Consulta c = new Consulta();
                c.setIdConsulta(rs.getInt("id_consulta"));
                c.setIdPaciente(rs.getInt("id_paciente"));
                c.setTipo(rs.getString("tp_consulta"));
                c.setStatus(rs.getString("ds_status"));
                c.setDataHora(rs.getTimestamp("dt_hora_data").toLocalDateTime());
                consultas.add(c);
            }
        }
        return consultas;
    }

    public Consulta listarUm(int idConsulta) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM HC_CONSULTA WHERE id_consulta=?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idConsulta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Consulta c = new Consulta();
                c.setIdConsulta(rs.getInt("id_consulta"));
                c.setIdPaciente(rs.getInt("id_paciente"));
                c.setTipo(rs.getString("tp_consulta"));
                c.setStatus(rs.getString("ds_status"));
                c.setDataHora(rs.getTimestamp("dt_hora_data").toLocalDateTime());
                return c;
            }
        }
        return null;
    }
}
