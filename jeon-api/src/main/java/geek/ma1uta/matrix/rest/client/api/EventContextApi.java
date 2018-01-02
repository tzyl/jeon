package geek.ma1uta.matrix.rest.client.api;

import geek.ma1uta.matrix.rest.client.model.eventcontext.EventContextResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/_matrix/client/r0/rooms")
public interface EventContextApi {

    @GET
    @Path("/{roomId}/context/{eventId}")
    EventContextResponse context(@PathParam("roomId") String roomId, @PathParam("eventId") String eventId);
}
