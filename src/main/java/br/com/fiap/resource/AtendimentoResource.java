package br.com.fiap.resource;

import br.com.fiap.bo.AtendimentoBO;
import br.com.fiap.to.Atendimento;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/atendimento")
public class AtendimentoResource {

    AtendimentoBO atendimentoBO = new AtendimentoBO();

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarAtendimentosPorPaciente(@PathParam("id") int idPaciente) {
        List<Atendimento> resultado = null;
        Response.ResponseBuilder response;
        try {
            resultado = atendimentoBO.listarAtendimentosPorPaciente(idPaciente);
            if (resultado != null) {
                response = Response.ok(); // 200 - OK
            } else {
                response = Response.status(404); // 404 - NOT FOUND
            }
        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage());
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirAtendimento(Atendimento atendimento) {
        AtendimentoBO atendimentoBO = new AtendimentoBO();
        String resultado; // <-- tipo corrigido
        Response.ResponseBuilder response;

        try {
            resultado = atendimentoBO.inserirAtendimento(atendimento);

            if (resultado != null) {
                response = Response.status(201); // CREATED
            } else {
                response = Response.status(400); // BAD REQUEST
            }

        } catch (Exception e) {
            resultado = "Erro ao inserir atendimento: " + e.getMessage();
            response = Response.serverError(); // 500
        }

        response.entity(resultado); // sempre retorna a string
        return response.build();
    }

}
