package main.REST;

import main.Boundary.PortalRepository;
import main.Entity.User;

import javax.annotation.security.PermitAll;
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
            portal.AddNewUser(user);
            return Response.created(new URI("")).build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e).
                    build();
        }
    }
}
