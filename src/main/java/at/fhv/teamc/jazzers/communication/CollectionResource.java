package at.fhv.teamc.jazzers.communication;

import at.fhv.teamc.jazzers.domain.Collection;
import at.fhv.teamc.jazzers.domain.Product;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("collection")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionResource {

    private static final String CREDENTIAL_SERVICE_URI = "http://10.0.40.165:8080/jazzers-backend-1.0-SNAPSHOT/api/v1/login/customer";

    @GET
    @Transactional
    public Response getCollection(@QueryParam("username") @DefaultValue("") String username,
                                  @QueryParam("password") @DefaultValue("") String password) {

        if (isNotAuthorized(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        Collection collection = Collection.findByOwnerName(username);

        return Response.status(Response.Status.OK).entity(collection.products).build();
    }

    @GET
    @Path("ownership")
    public Response checkIfOwner(@QueryParam("username") @DefaultValue("") String username,
                                 @QueryParam("password") @DefaultValue("") String password,
                                 @QueryParam("productName") @DefaultValue("") String title) {

        if (isNotAuthorized(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        Collection collection = Collection.findByOwnerName(username);

        boolean isOwner = collection.products.stream().map(product -> product.title).collect(Collectors.toList()).contains(title);

        if (isOwner) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("add")
    @Transactional
    public Response addProductToCollection(@QueryParam("username") @DefaultValue("") String username,
                                           @QueryParam("password") @DefaultValue("") String password,
                                           @QueryParam("productId") @DefaultValue("") String productId,
                                           @QueryParam("title") @DefaultValue("") String title) {

        if (isNotAuthorized(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        Collection collection = Collection.findByOwnerName(username);

        Product product = Product.findByProductId(productId);

        if (product == null) {
            product = new Product();
            product.productId = productId;
            product.title = title;
        }

        collection.products.add(product);

        return Response.status(Response.Status.OK).build();
    }

    private boolean isNotAuthorized(String username, String password) {
        Client client = ClientBuilder.newClient();

        Response.Status loginStatus = client
                .target(CREDENTIAL_SERVICE_URI + "?username=" + username + "&password=" + password)
                .request(MediaType.APPLICATION_JSON).get()
                .getStatusInfo().toEnum();

        client.close();

        return !loginStatus.equals(Response.Status.OK);
    }
}