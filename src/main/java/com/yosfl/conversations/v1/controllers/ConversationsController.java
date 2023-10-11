package com.yosfl.conversations.v1.controllers;

import com.yosfl.conversations.v1.entities.dtos.ConversationInitiateDTO;
import com.yosfl.conversations.v1.entities.dtos.MessageDTO;
import com.yosfl.conversations.v1.entities.dtos.MessageInputDTO;
import com.yosfl.conversations.v1.services.ConversationsRepository;
import com.yosfl.exceptions.ObjectNotFoundException;
import com.yosfl.exceptions.PronosNotFoundException;
import com.yosfl.secure.JwtTokenNeeded;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("v1/conversations")
@Produces(MediaType.APPLICATION_JSON)
@JwtTokenNeeded
public class ConversationsController {

    @Inject
    ConversationsRepository conversationsRepository;

    @GET
    @Path("")
    public Response getConversations(@Context HttpHeaders headers){
        try {
            return Response.ok().entity(
                            conversationsRepository.getAll(headers.getHeaderString("email")))
                    .build();
        } catch (ObjectNotFoundException e) {
            throw new PronosNotFoundException(e.getMessage());
        }
    }

    @GET
    @Path("/{idConversation}/messages")
    public Response getMessages(@PathParam("idConversation") String idConversation,
            @Context HttpHeaders headers){
        try {
            return Response.ok().entity(
                            conversationsRepository.getAllMessages(
                                    Long.parseLong(idConversation),
                                    headers.getHeaderString("email")))
                    .build();
        }catch(NumberFormatException nfe){
            Log.error("L'id de la conversation pour ajout de message n'est pas numérique!! "+idConversation);
            return Response.status(Response.Status.BAD_REQUEST).entity("id of conversation must be numeric").build();
        } catch (ObjectNotFoundException e) {
            throw new PronosNotFoundException(e.getMessage());
        }
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public Response newConversation(@Valid ConversationInitiateDTO input,
                                    @Context HttpHeaders headers){

        conversationsRepository.createConversation(input, headers.getHeaderString("email"));

        return Response.ok().build();
    }


    @POST
    @Path("/{id}/messages")
    @Consumes("application/json")
    public Response addMessage(@PathParam("id") String idConversation,
                               @Valid MessageInputDTO input,
                               @Context HttpHeaders headers){
        try{
            MessageDTO dto = conversationsRepository.createMessage(input, Long.parseLong(idConversation),
                    headers.getHeaderString("email"));
            Log.info(headers.getHeaderString("email")+
                    " a ajouté un message à la conversation "+idConversation);
            return Response.ok(dto).build();
        }catch(NumberFormatException nfe){
            Log.error("L'id de la conversation pour ajout de message n'est pas numérique!! "+idConversation);
            return Response.status(Response.Status.BAD_REQUEST).entity("id of conversation must be numeric").build();
        } catch (ObjectNotFoundException e) {
            throw new PronosNotFoundException(e.getMessage());
        }
    }

    @DELETE
    @Path("/{id}/messages/{idmessage}")
    @Consumes("application/json")
    public Response delMessage(@PathParam("id") String idConversation,
                               @PathParam("idmessage") String idMessage,
                               @Context HttpHeaders headers) {
        try {
            return (conversationsRepository.deleteMessage(
                    idConversation,
                    idMessage,
                    headers.getHeaderString("email")) ? Response.status(Response.Status.NO_CONTENT).build():
                    Response.status(Response.Status.NOT_FOUND).build()
            );
        }catch(NumberFormatException nfe){
            return Response.status(Response.Status.BAD_REQUEST).entity("id conversation ou id message non num").build();
        }
        catch (ObjectNotFoundException e) {
            throw new PronosNotFoundException(e.getMessage());
        }
    }
}
