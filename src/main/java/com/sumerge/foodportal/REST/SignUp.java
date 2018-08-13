package com.sumerge.foodportal.REST;

import com.sumerge.foodportal.Boundary.PortalRepository;
import com.sumerge.foodportal.Entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RequestScoped
@Path("/")
public class SignUp {

    private static final Logger LOGGER = Logger.getLogger(SignUp.class.getName());

    @EJB
    private PortalRepository portal;

    @POST
    @Path("/addUser")
    public Response addNewUser(User user) {
        try {
            portal.addNewUser(user);
            return Response.created(new URI("")).build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e).
                    build();
        }
    }
}
