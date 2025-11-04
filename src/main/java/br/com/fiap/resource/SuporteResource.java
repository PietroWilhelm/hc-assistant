package br.com.fiap.resource;

import br.com.fiap.bo.SuporteBO;

import br.com.fiap.to.Suporte;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/suporte")
public class SuporteResource {
    SuporteBO suporteBO = new SuporteBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        Response.ResponseBuilder response;
        List<Suporte> lista;

        try {
            lista = suporteBO.listarTodosSuportes();

            if (lista != null && !lista.isEmpty()) {
                response = Response.ok(lista); // 200 OK
            } else {
                response = Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum suporte encontrado.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            response = Response.serverError()
                    .entity("Erro ao buscar suportes: " + e.getMessage());
        }

        return response.build();
    }
}
