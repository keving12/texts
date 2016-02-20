package com.kg.services;

import com.kg.dao.MessageDAO;
import com.kg.dao.mongo.MongoMessageDAO;
import com.kg.entities.Message;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by kevingracie on 26/01/2016.
 */

@Path("/messages")
public class MessageService {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMessages() {
        MessageDAO messageDAO = new MongoMessageDAO();
        List<Message> messages = messageDAO.getAllMessages();
        System.out.println(messages.size());
        Response response = Response.ok(new GenericEntity<List<Message>>(messages){}).build();

        return response;
    }

}
