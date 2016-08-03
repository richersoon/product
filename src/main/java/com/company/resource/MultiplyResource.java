package com.company.resource;


import com.company.dto.MultiplyDto;
import com.company.service.MultiplyService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Created by richersoon on 7/14/16.
 */
@Path("todolist/multiply")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MultiplyResource {

    @Inject
    private MultiplyService multiplyService;

    @GET
    public Response get(@NotNull @QueryParam("numA") Integer numA,
                        @NotNull @QueryParam("numB") Integer numB) {
        final MultiplyDto multiplyDto = multiplyService.multiplyService(numA, numB);

        final Response response = Response.status(Status.OK).entity(multiplyDto).build();
        return response;
    }
}
