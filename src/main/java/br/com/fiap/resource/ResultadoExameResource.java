package br.com.fiap.resource;

import br.com.fiap.bo.ResultadoExameBO;
import br.com.fiap.to.ResultadoExame;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/resultadoExame")
public class ResultadoExameResource {

    ResultadoExameBO bo = new ResultadoExameBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(ResultadoExame resultado) {
        try {
            String resp = bo.inserir(resultado);
            return Response.status(201).entity(resp).build();
        } catch (Exception e) {
            return Response.status(401).entity("Erro ao inserir resultado: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(ResultadoExame resultado) {
        try {
            String resp = bo.atualizar(resultado);
            return Response.status(200).entity(resp).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao atualizar resultado: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) {
        try {
            ResultadoExame resultado = new ResultadoExame();
            resultado.setIdResultado(id);
            String resp = bo.excluir(resultado);
            return Response.status(200).entity(resp).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao excluir resultado: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            String resp = bo.buscarPorId(id);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao buscar resultado: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/paciente/{idPaciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorPaciente(@PathParam("idPaciente") int idPaciente) {
        try {
            List<ResultadoExame> lista = bo.listarPorPaciente(idPaciente);
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao listar exames: " + e.getMessage()).build();
        }
    }
}
