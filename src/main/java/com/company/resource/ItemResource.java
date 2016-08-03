package com.company.resource;


import com.company.dto.ItemCreateRequestDto;
import com.company.dto.ItemDto;
import com.company.dto.ItemUpdateRequestDto;
import com.company.service.ItemService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * Created by richersoon on 7/14/16.
 */
@Path("todolist/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

    @Inject
    private ItemService itemService;

    @POST
    public Response create(@Valid ItemCreateRequestDto requestDto, @Context UriInfo uriInfo) {
        final ItemDto item = itemService.create(requestDto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(item.getId());

        final Response response = Response.created(builder.build()).build();
        return response;
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        final ItemDto task = itemService.get(id);
        final Response response = Response.status(Status.OK).build();
        return response;
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, @Valid ItemUpdateRequestDto requestDto) {
        itemService.update(requestDto);
        final Response response = Response.status(Status.NO_CONTENT).build();
        return response;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        itemService.delete(id);
        final Response response = Response.status(Status.NO_CONTENT).build();
        return response;
    }
}
