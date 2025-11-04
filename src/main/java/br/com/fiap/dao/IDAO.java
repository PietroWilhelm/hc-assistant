package br.com.fiap.dao;

import java.util.List;

public interface IDAO {
    public String inserir(Object object);
    public String alterar(Object object);
    public String excluir(Object object);
    public String listarUm(Object object);

    // Opcional: listagem geral (ex.: FAQ, Suporte)
    default List<?> listarTodos() {
        throw new UnsupportedOperationException("listarTodos() não suportado para este DAO.");
    }

    // Opcional: listagem por paciente (ex.: Consulta, Resultado, Notificação, Cuidador, Atendimento)
    default List<?> listarTodosPorPaciente(int idPaciente) {
        throw new UnsupportedOperationException("listarTodosPorPaciente() não suportado para este DAO.");
    }
}
