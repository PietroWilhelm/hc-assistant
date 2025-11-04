package br.com.fiap.resource;

import br.com.fiap.bo.CuidadorBO;
import br.com.fiap.to.Cuidador;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cuidadores")
public class CuidadorResource {

    private CuidadorBO cuidadorBO = new CuidadorBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<Cuidador> resultado = cuidadorBO.listarTodos();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 - OK
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        Cuidador resultado = cuidadorBO.buscarPorId(id);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok(); // 200 - OK
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Cuidador cuidador) {
        Cuidador resultado = cuidadorBO.save(cuidador);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        boolean sucesso = cuidadorBO.delete(id);
        Response.ResponseBuilder response = null;
        if (sucesso) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Cuidador cuidador, @PathParam("id") int id) {
        cuidador.setIdCuidador(id);
        Cuidador resultado = cuidadorBO.update(cuidador);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }
}
