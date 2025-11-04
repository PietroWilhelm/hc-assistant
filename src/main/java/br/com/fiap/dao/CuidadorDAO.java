package br.com.fiap.dao;

import br.com.fiap.to.Cuidador;
import java.sql.*;
import java.util.ArrayList;

public class CuidadorDAO {

    private Connection con;

    public CuidadorDAO(Connection con) {
        this.con = con;
    }

    //  Listar todos os cuidadores
    public ArrayList<Cuidador> listarTodos() {
        ArrayList<Cuidador> cuidadores = new ArrayList<>();
        String sql = "SELECT * FROM HC_CUIDADOR";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cuidador cuidador = new Cuidador();
                cuidador.setIdCuidador(rs.getInt("id_cuidador"));
                cuidador.setNome(rs.getString("nm_completo"));
                cuidador.setTelefone(rs.getString("nr_telefone"));
                cuidador.setIdPaciente(rs.getInt("id_paciente"));
                cuidadores.add(cuidador);
            }

            System.out.println(" Cuidadores listados com sucesso! Total: " + cuidadores.size());

        } catch (SQLException e) {
            System.out.println(" Erro ao listar cuidadores: " + e.getMessage());
        }

        return cuidadores;
    }

    // Buscar cuidador por ID
    public Cuidador buscarPorId(int id) {
        Cuidador cuidador = null;
        String sql = "SELECT * FROM HC_CUIDADOR WHERE id_cuidador = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cuidador = new Cuidador();
                cuidador.setIdCuidador(rs.getInt("id_cuidador"));
                cuidador.setNome(rs.getString("nm_completo"));
                cuidador.setTelefone(rs.getString("nr_telefone"));
                cuidador.setIdPaciente(rs.getInt("id_paciente"));
                System.out.println(" Cuidador encontrado com sucesso: " + cuidador.getNome());
            } else {
                System.out.println(" Nenhum cuidador encontrado com o ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println(" Erro ao buscar cuidador: " + e.getMessage());
        }

        return cuidador;
    }

    // Inserir cuidador
    public Cuidador inserir(Cuidador cuidador) {
        String sql = "INSERT INTO HC_CUIDADOR (NM_COMPLETO, NR_TELEFONE, ID_PACIENTE) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getTelefone());
            ps.setInt(3, cuidador.getIdPaciente());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cuidador.setIdCuidador(rs.getInt(1));
            }

            System.out.println(" Cuidador inserido com sucesso: " + cuidador.getNome());
            return cuidador;

        } catch (SQLException e) {
            System.out.println(" Erro ao inserir cuidador: " + e.getMessage());

            return null;
        }
    }



    //  Atualizar cuidador
    public Cuidador alterar(Cuidador cuidador) {
        String sql = "UPDATE HC_CUIDADOR SET nm_completo=?, nr_telefone=?, id_paciente=? WHERE id_cuidador=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getTelefone());
            ps.setInt(3, cuidador.getIdPaciente());
            ps.setInt(4, cuidador.getIdCuidador());

            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println(" Cuidador atualizado com sucesso: " + cuidador.getNome());
                return cuidador;
            } else {
                System.out.println(" Nenhum cuidador encontrado para atualização (ID: " + cuidador.getIdCuidador() + ")");
            }

        } catch (SQLException e) {
            System.out.println(" Erro ao atualizar cuidador: " + e.getMessage());
        }

        return null;
    }

    // Excluir cuidador
    public boolean excluir(int id) {
        String sql = "DELETE FROM HC_CUIDADOR WHERE id_cuidador=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("Cuidador excluído com sucesso (ID: " + id + ")");
                return true;
            } else {
                System.out.println(" Nenhum cuidador encontrado para exclusão (ID: " + id + ")");
            }
        } catch (SQLException e) {
            System.out.println(" Erro ao excluir cuidador: " + e.getMessage());
        }
        return false;
    }
}
