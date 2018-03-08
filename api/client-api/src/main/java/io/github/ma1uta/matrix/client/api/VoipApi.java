package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.client.model.voip.VoipResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/_matrix/client/r0/voip")
@JsonRest
public interface VoipApi {

    @GET
    @Path("/turnServer")
    VoipResponse turnServer();
}