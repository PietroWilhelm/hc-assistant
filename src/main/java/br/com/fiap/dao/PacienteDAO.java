package br.com.fiap.dao;

import br.com.fiap.to.Paciente;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PacienteDAO {

    private Connection con;

    public PacienteDAO(Connection con) {
        this.con = con;
    }

    // Listar todos os pacientes
    public ArrayList<Paciente> listarTodos() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM HC_PACIENTE";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setNome(rs.getString("nm_completo"));
                paciente.setCpf(rs.getString("nr_cpf"));
                paciente.setDataNascimento(rs.getDate("dt_nascimento").toLocalDate());
                paciente.setTelefone(rs.getString("nr_telefone"));
                paciente.setEmail(rs.getString("ds_email"));
                pacientes.add(paciente);
            }

            System.out.println("Pacientes listados com sucesso! Total: " + pacientes.size());

        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }

        return pacientes;
    }

    // Buscar paciente por ID
    public Paciente buscarPorId(int id) {
        Paciente paciente = null;
        String sql = "SELECT * FROM HC_PACIENTE WHERE id_paciente = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setNome(rs.getString("nm_completo"));
                paciente.setCpf(rs.getString("nr_cpf"));
                paciente.setDataNascimento(rs.getDate("dt_nascimento").toLocalDate());
                paciente.setTelefone(rs.getString("nr_telefone"));
                paciente.setEmail(rs.getString("ds_email"));
                System.out.println(" Paciente encontrado com sucesso: " + paciente.getNome());
            } else {
                System.out.println(" Nenhum paciente encontrado com o ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println(" Erro ao buscar paciente: " + e.getMessage());
        }

        return paciente;
    }

    //Inserir
    public Paciente inserir(Paciente paciente) {
        String sql = "INSERT INTO HC_PACIENTE (nm_completo, nr_cpf, dt_nascimento, nr_telefone, ds_email) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID_PACIENTE"})) {
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setDate(3, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(4, paciente.getTelefone());
            ps.setString(5, paciente.getEmail());
            ps.executeUpdate();

            // Recuperar o ID gerado automaticamente
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    paciente.setIdPaciente(rs.getInt(1));
                }
            }

            System.out.println(" Paciente cadastrado com sucesso: " + paciente.getNome());
            return paciente;

        } catch (SQLException e) {
            System.out.println(" Erro ao inserir paciente: " + e.getMessage());
            return null;
        }
    }

    // Atualizar paciente
    public Paciente alterar(Paciente paciente) {
        String sql = "UPDATE HC_PACIENTE SET nm_completo=?, dt_nascimento=?, nr_telefone=?, ds_email=? WHERE id_paciente=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, paciente.getNome());
            ps.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(3, paciente.getTelefone());
            ps.setString(4, paciente.getEmail());
            ps.setInt(5, paciente.getIdPaciente());
            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println(" Paciente atualizado com sucesso: " + paciente.getNome());
                return paciente;
            } else {
                System.out.println(" Nenhum paciente encontrado para atualização (ID: " + paciente.getIdPaciente() + ")");
            }

        } catch (SQLException e) {
            System.out.println(" Erro ao atualizar paciente: " + e.getMessage());
        }
        return null;
    }

    //Excluir paciente
    public boolean excluir(int id) {
        String sql = "DELETE FROM HC_PACIENTE WHERE id_paciente=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println(" Paciente excluído com sucesso (ID: " + id + ")");
                return true;
            } else {
                System.out.println(" Nenhum paciente encontrado para exclusão (ID: " + id + ")");
            }
        } catch (SQLException e) {
            System.out.println(" Erro ao excluir paciente: " + e.getMessage());
        }
        return false;
    }

    public Paciente buscarPorCpfEDataNascimento(String cpf, LocalDate dataNascimento) {
        Paciente pacienteEncontrado = null;

        String sql = "SELECT * FROM HC_PACIENTE WHERE NR_CPF = ? AND DT_NASCIMENTO = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cpf);
            ps.setDate(2, java.sql.Date.valueOf(dataNascimento));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pacienteEncontrado = new Paciente();
                pacienteEncontrado.setIdPaciente(rs.getInt("ID_PACIENTE"));
                pacienteEncontrado.setNome(rs.getString("NM_COMPLETO"));
                pacienteEncontrado.setCpf(rs.getString("NR_CPF"));
                pacienteEncontrado.setDataNascimento(rs.getDate("DT_NASCIMENTO").toLocalDate());
                pacienteEncontrado.setTelefone(rs.getString("NR_TELEFONE"));
                pacienteEncontrado.setEmail(rs.getString("DS_EMAIL"));
            }

        } catch (SQLException e) {
            System.out.println("ERRO de sql: " + e.getMessage());
        }
        return pacienteEncontrado;
    }



}
