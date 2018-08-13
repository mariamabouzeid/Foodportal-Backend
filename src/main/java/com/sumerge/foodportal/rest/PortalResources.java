package com.sumerge.foodportal.rest;

//import PortalRepository;

import com.sumerge.foodportal.boundary.PortalRepository;
import com.sumerge.foodportal.entity.Complaint;
import com.sumerge.foodportal.rest.model.OrderModel;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.net.URI;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RequestScoped
@Path("/secured")
@RolesAllowed("**")
public class PortalResources {

    private static final Logger LOGGER = Logger.getLogger(PortalResources.class.getName());

    @EJB
    private PortalRepository portal;

    @Context
    SecurityContext securityContext;

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

    @GET
    @Path("/user")
    public Response getUserId() {
        try {
            return Response.ok().
                    entity(portal.getUserId(securityContext.getUserPrincipal().getName())).
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
            portal.addComplaint(complaint);
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
