/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix.identity.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.identity.model.validation.PublishRequest;
import io.github.ma1uta.matrix.identity.model.validation.PublishResponse;
import io.github.ma1uta.matrix.identity.model.validation.UnbindRequest;
import io.github.ma1uta.matrix.identity.model.validation.ValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Checking the validation of the 3pid ownership.
 */
@Path("/_matrix/identity/v2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ValidationApi {

    /**
     * Determines if a given 3pid has been validated by a user.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link ValidationResponse}.
     * <p>Status code 200: Validation information for the session.</p>
     * <p>Status code 400: The session has not been validated. If the session has not been validated, then errcode will be
     * M_SESSION_NOT_VALIDATED. If the session has timed out, then errcode will be M_SESSION_EXPIRED.</p>
     * <p>Status code 404: The Session ID or client secret were not found.</p>
     *
     * @param sid             Required. The Session ID generated by the requestToken call.
     * @param clientSecret    Required. The client secret passed to the requestToken call.
     * @param uriInfo         Request information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Determines if a given 3pid has been validated by a user.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Validation information for the session.",
                content = @Content(
                    schema = @Schema(
                        implementation = ValidationResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "he session has not been validated. If the session has not been validated, then errcode will be"
                    + " M_SESSION_NOT_VALIDATED. If the session has timed out, then errcode will be M_SESSION_EXPIRED.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "The Session ID or client secret were not found.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @GET
    @Path("/3pid/getValidated3pid")
    void validate(
        @Parameter(
            description = "The Session ID generated by the requestToken call.",
            required = true
        ) @QueryParam("sid") String sid,
        @Parameter(
            description = "The client secret passed to the requestToken call.",
            required = true
        ) @QueryParam("client_secret") String clientSecret,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Publish an association between a session and a Matrix user ID.
     * <br>
     * Future calls to /lookup for any of the session's 3pids will return this association.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link PublishResponse}.
     * <p>Status code 200: The association was published.</p>
     * <p>Status code 400: The association was not published. If the session has not been validated, then errcode will be
     * M_SESSION_NOT_VALIDATED. If the session has timed out, then errcode will be M_SESSION_EXPIRED.</p>
     * <p>Status code 404: The Session ID or client secret were not found.</p>
     *
     * @param request         JSON body request.
     * @param uriInfo         Request information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Publish an association between a session and a Matrix user ID.",
        description = "Future calls to /lookup for any of the session's 3pids will return this association.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The association was published.",
                content = @Content(
                    schema = @Schema(
                        implementation = PublishResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "The association was not published. If the session has not been validated, then errcode will be"
                    + " M_SESSION_NOT_VALIDATED. If the session has timed out, then errcode will be M_SESSION_EXPIRED.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "he Session ID or client secret were not found.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            )
        }
    )
    @POST
    @Path("/bind")
    void bind(
        @RequestBody(
            description = "JSON body request",
            required = true
        ) PublishRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Remove an association between a session and a Matrix user ID.
     * <br>
     * Future calls to /lookup for any of the session's 3pids will not return the removed association.
     * <br>
     * The identity server should authenticate the request in one of two ways:
     * <ul>
     * <li>The request is signed by the homeserver which controls the user_id.</li>
     * <li>The request includes the sid and client_secret parameters, as per /3pid/bind, which proves ownership of the 3PID.</li>
     * </ul>
     * If this endpoint returns a JSON Matrix error, that error should be passed through to the client requesting an unbind through
     * a homeserver, if the homeserver is acting on behalf of a client.
     * <br>
     * <b>Requires auth</b>:Yes.
     * <br>
     * Return: {@link io.github.ma1uta.matrix.EmptyResponse}.
     * <p>Status code 200: The association was successfully removed.</p>
     * <p>Status code 400: If the response body is not a JSON Matrix error, the identity server does not support unbinds.
     * If a JSON Matrix error is in the response body, the requesting party should respect the error.</p>
     * <p>Status code 403: The credentials supplied to authenticate the request were invalid. This may also be returned if
     * the identity server does not support the chosen authentication method (such as blocking homeservers from unbinding identifiers).</p>
     * <p>Status code 404: If the response body is not a JSON Matrix error, the identity server does not support unbinds.
     * If a JSON Matrix error is in the response body, the requesting party should respect the error.</p>
     * <p>Status code 501: If the response body is not a JSON Matrix error, the identity server does not support unbinds.
     * If a JSON Matrix error is in the response body, the requesting party should respect the error.</p>
     *
     * @param request         JSON body request.
     * @param uriInfo         Request information.
     * @param httpHeaders     Http headers.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @Operation(
        summary = "Remove an association between a session and a Matrix user ID.",
        description = "Future calls to /lookup for any of the session's 3pids will not return the removed association.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The association was successfully removed.",
                content = @Content(
                    schema = @Schema(
                        implementation = EmptyResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "If the response body is not a JSON Matrix error, the identity server does not support unbinds."
                    + " If a JSON Matrix error is in the response body, the requesting party should respect the error.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "403",
                description = "The credentials supplied to authenticate the request were invalid. This may also be returned if"
                    + " the identity server does not support the chosen authentication method"
                    + "(such as blocking homeservers from unbinding identifiers).",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "If the response body is not a JSON Matrix error, the identity server does not support unbinds."
                    + " If a JSON Matrix error is in the response body, the requesting party should respect the error.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
            @ApiResponse(
                responseCode = "501",
                description = "If the response body is not a JSON Matrix error, the identity server does not support unbinds."
                    + " If a JSON Matrix error is in the response body, the requesting party should respect the error.",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponse.class
                    )
                )
            ),
        }
    )
    @POST
    @Path("/unbind")
    void unbind(
        @RequestBody(
            description = "JSON body request",
            required = true
        ) UnbindRequest request,

        @Context UriInfo uriInfo,
        @Context HttpHeaders httpHeaders,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
