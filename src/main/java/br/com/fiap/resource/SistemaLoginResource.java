package br.com.fiap.resource;

import br.com.fiap.bo.SistemaLoginBO;

import br.com.fiap.to.SistemaLogin;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/login")
public class SistemaLoginResource {

    private SistemaLoginBO bo = new SistemaLoginBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(SistemaLogin login) {
        try {
            bo.inserir(login);
            return Response.status(Response.Status.CREATED).build(); // 201
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao cadastrar login: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            List<SistemaLogin> lista = bo.listarTodos();
            if (lista == null || lista.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Nenhum login encontrado").build();
            }
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar logins: " + e.getMessage()).build();
        }
    }
}
