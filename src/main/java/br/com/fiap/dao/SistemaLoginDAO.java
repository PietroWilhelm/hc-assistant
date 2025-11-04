package br.com.fiap.dao;

import br.com.fiap.to.SistemaLogin;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaLoginDAO {
    private Connection con;

    public SistemaLoginDAO(Connection con) {
        this.con = con;
    }

    public void inserir(SistemaLogin login) throws SQLException {
        String sql = "INSERT INTO HC_AUTENTIFICACAO_LOGIN (TP_LOGIN, CD_VERIFICACAO, DT_ENVIO, STATUS, ID_PACIENTE) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login.getTipoLogin());
            stmt.setInt(2, login.getCodigoVerificacao());
            stmt.setDate(3, Date.valueOf(login.getDataEnvio()));
            stmt.setString(4, login.getStatus());
            stmt.setInt(5, login.getIdPaciente());
            stmt.executeUpdate();
        }
    }

    public List<SistemaLogin> listarTodos() throws SQLException {
        List<SistemaLogin> lista = new ArrayList<>();
        String sql = "SELECT * FROM HC_AUTENTIFICACAO_LOGIN ORDER BY ID_LOGIN";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SistemaLogin login = new SistemaLogin();
                login.setIdLogin(rs.getInt("ID_LOGIN"));
                login.setTipoLogin(rs.getString("TP_LOGIN"));
                login.setCodigoVerificacao(rs.getInt("CD_VERIFICACAO"));
                login.setDataEnvio(rs.getDate("DT_ENVIO").toLocalDate());
                login.setStatus(rs.getString("STATUS"));
                login.setIdPaciente(rs.getInt("ID_PACIENTE"));
                lista.add(login);
            }
        }
        return lista;
    }
}
