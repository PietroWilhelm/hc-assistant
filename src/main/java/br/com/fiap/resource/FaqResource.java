package br.com.fiap.resource;

import br.com.fiap.bo.FaqBO;

import br.com.fiap.to.Faq;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/faq")
public class FaqResource {

    FaqBO faqBO = new FaqBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        Response.ResponseBuilder response;
        List<Faq> resultado;
        try {
            resultado = faqBO.listarTodosFaqs();
            if (resultado != null) {
                response = Response.ok(resultado); // 200 OK
            } else {
                response = Response.status(404).entity("Nenhum FAQ encontrado"); // 404 Not Found
            }

        } catch (ClassNotFoundException | SQLException e) {
            response = Response.serverError().entity("Erro ao buscar FAQs: " + e.getMessage()); // 500
        }

        return response.build();
    }


}
