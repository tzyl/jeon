package geek.ma1uta.matrix.client.api;

import geek.ma1uta.matrix.client.model.sync.SyncRequest;
import geek.ma1uta.matrix.client.model.sync.SyncResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/_matrix/client/r0")
@JsonRest
public interface SyncApi {

    @GET
    @Path("/sync")
    SyncResponse sync(SyncRequest syncRequest);
}