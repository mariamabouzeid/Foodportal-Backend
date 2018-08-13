package com.sumerge.foodportal.REST.Filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.putSingle("Access-Control-Allow-Headers", "x-range, range, x-requested-with, content-type," +
                " access-control-allow-origin, access-control-allow-methods, access-control-allow-headers," +
                " origin, accept, authorization");
        headers.putSingle("Access-Control-Allow-Origin", "*");
        headers.putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}