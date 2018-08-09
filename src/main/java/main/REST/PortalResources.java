package main.REST;

//import main.Boundary.PortalRepository;

import main.Boundary.PortalRepository;
import main.Entity.Complaint;
import main.Entity.OrderItems;
import main.Entity.User;
import main.REST.models.OrderModel;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RequestScoped
@Path("/")
public class PortalResources {

    private static final Logger LOGGER = Logger.getLogger(PortalResources.class.getName());

    @EJB
    private PortalRepository portal;

    @GET
    @Path("/menu")
    public Response viewMenuItems() {
        try {
            return Response.ok().
                    entity(portal.viewAllItems()).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @POST
    @Path("/complaints")
    public Response addNewComplaint(Complaint complaint) {
        try {
            portal.AddComplaint(complaint);
            return Response.created(new URI("")).build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @POST
    @Path("/checkout")
    public Response saveOrderItems(OrderModel order) {
        try {
            portal.saveUserItems(order);
            return Response.created(new URI("")).build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

}
