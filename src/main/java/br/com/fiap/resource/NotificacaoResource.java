package br.com.fiap.resource;

import br.com.fiap.bo.NotificacaoBO;
import br.com.fiap.to.Notificacao;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/notificacao")
public class NotificacaoResource {

    NotificacaoBO notificacaoBO = new NotificacaoBO();

    @GET
    @Path("/paciente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("id") int idPaciente) {
        Response.ResponseBuilder response;
        List<Notificacao> resultado = null;

        try {
            resultado = notificacaoBO.listarNotificacoesPorPaciente(idPaciente);

            if (resultado != null && !resultado.isEmpty()) {
                response = Response.ok(resultado);
            } else {
                response = Response.status(404).entity("Nenhuma notificação encontrada");
            }

        } catch (ClassNotFoundException | SQLException e) {
            response = Response.serverError().entity("Erro de banco ao buscar notificações: " + e.getMessage());
        } catch (Exception e) {
            response = Response.serverError().entity("Erro inesperado: " + e.getMessage());
        }

        return response.build();
    }
}
