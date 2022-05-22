package at.fhv.teamc.jazzers.communication;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("collection")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionResource {

    private static final String CREDENTIAL_SERVICE_URI = "http://10.0.40.165:8080/jazzers-backend-1.0-SNAPSHOT/api/v1/login/customer";

    @GET
    public Response getCollection(@QueryParam("username") @DefaultValue("") String username,
                                  @QueryParam("password") @DefaultValue("") String password) {

        if (isNotAuthorized(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).build();


        // Product product = Product.findById(1);

        // Collection collection = Collection.findByOwnerName(username);



        // Collection collection = new Collection();
        // collection.ownerName = username;
        // collection.products = new ArrayList<>();
        // collection.persist();

        // System.out.println("Products: " + collection.products.toString());

        return Response.status(Response.Status.OK).build();

        // return Response.status(Response.Status.OK).entity(collection.products).build();
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