package br.com.fiap.resource;
import br.com.fiap.bo.ConsultaBO;
import br.com.fiap.to.Consulta;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/consultas")
public class ConsultaResource {

    private final ConsultaBO consultaBo = new ConsultaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Consulta> resultado = null;
        Response.ResponseBuilder response;

        try {
            resultado = consultaBo.listarTodasConsultas();
            if (resultado != null && !resultado.isEmpty()) {
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        Consulta resultado = null;
        Response.ResponseBuilder response;

        try {
            resultado = consultaBo.buscarConsultaPorId(id);
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
    public Response save(Consulta consulta) {
        String resultado;
        Response.ResponseBuilder response;

        try {
            resultado = consultaBo.cadastrarConsulta(consulta);
            response = Response.created(null); // 201 - CREATED
            response.entity(resultado);
        } catch (Exception e) {
            response = Response.status(400).entity(e.getMessage()); // 400 - BAD REQUEST
        }

        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Consulta consulta, @PathParam("id") int id) {
        consulta.setIdConsulta(id);
        String resultado;
        Response.ResponseBuilder response;

        try {
            resultado = consultaBo.atualizarConsulta(consulta);
            response = Response.ok(); // 200 - OK
            response.entity(resultado);
        } catch (Exception e) {
            response = Response.status(400).entity(e.getMessage());
        }

        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Response.ResponseBuilder response;

        try {
            String resultado = consultaBo.excluirConsulta(id);
            if (resultado != null) {
                response = Response.status(204); // 204 - NO CONTENT
            } else {
                response = Response.status(404); // 404 - NOT FOUND
            }
        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage());
        }

        return response.build();
    }
}
