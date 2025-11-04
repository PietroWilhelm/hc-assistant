package br.com.fiap.resource;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.to.Paciente;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/pacientes")
public class PacienteResource {

    private PacienteBO pacienteBO = new PacienteBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<Paciente> resultado = pacienteBO.listarTodos();
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
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Paciente pacienteLogin) {
        try {
            if (pacienteLogin == null) {
                System.out.println(" Erro: corpo da requisição está vazio ou mal formatado.");
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Corpo da requisição inválido. Envie JSON com cpf e dataNascimento.")
                        .build();
            }

            System.out.println("🔍 Tentando login com CPF=" + pacienteLogin.getCpf() +
                    " e Data=" + pacienteLogin.getDataNascimento());

            Paciente resultado = pacienteBO.buscarPorCpfEDataNascimento(
                    pacienteLogin.getCpf(),
                    pacienteLogin.getDataNascimento()
            );

            if (resultado != null) {
                return Response.ok(resultado).build(); // 200 OK
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("CPF ou data de nascimento incorretos.")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("Erro interno ao processar login: " + e.getMessage())
                    .build();
        }
    }



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        Paciente resultado = pacienteBO.buscarPacientePorId(id);
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Paciente paciente) {
        try {
            Paciente resultado = pacienteBO.cadastrarPaciente(paciente);
            if (resultado != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(resultado)
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("CPF ou e-mail já cadastrados.")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao processar cadastro: " + e.getMessage())
                    .build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        boolean sucesso = pacienteBO.excluirPaciente(id);
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
    public Response update( Paciente paciente, @PathParam("id") int id) {
        paciente.setIdPaciente(id);
        Paciente resultado = pacienteBO.atualizarPaciente(paciente);
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
