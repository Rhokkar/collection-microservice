package at.fhv.teamc.jazzers.communication;

import at.fhv.jazzers.shared.dto.DigitalProductDTO;
import at.fhv.teamc.jazzers.ServiceRegistry;
import at.fhv.teamc.jazzers.application.api.CollectionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlist")
public class CollectionController {
    private final CollectionService collectionService = ServiceRegistry.collectionService();

    @GET
    @Path("/collection")
    @Produces(MediaType.APPLICATION_JSON)
    public Response collection(@QueryParam("username") @DefaultValue("") String username) {
        List<DigitalProductDTO> productsDTO = collectionService.getCollection(username);
        return Response.status(Response.Status.OK).entity(productsDTO).build();
    }

    @GET
    @Path("/collection/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addToCollection(@QueryParam("username") @DefaultValue("") String username) {
        List<DigitalProductDTO> productsDTO = collectionService.getCollection(username);
        return Response.status(Response.Status.OK).entity(productsDTO).build();
    }
}
