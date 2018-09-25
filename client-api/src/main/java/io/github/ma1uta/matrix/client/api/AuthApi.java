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

package io.github.ma1uta.matrix.client.api;

import io.github.ma1uta.matrix.EmptyResponse;
import io.github.ma1uta.matrix.RateLimit;
import io.github.ma1uta.matrix.Secured;
import io.github.ma1uta.matrix.client.model.auth.LoginRequest;
import io.github.ma1uta.matrix.client.model.auth.LoginResponse;
import io.github.ma1uta.matrix.client.model.auth.SupportedLoginResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * A client can obtain access tokens using the /login API.
 * <br>
 * Note that this endpoint does not currently use the user-interactive authentication API.
 */
@Api(
    value = "Authentication",
    description = "A client can obtain access tokens using the /login API"
)
@Path("/_matrix/client/r0")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthApi {

    /**
     * Authentication types.
     */
    class AuthType {

        protected AuthType() {
            // singleton.
        }

        /**
         * The client submits a username and secret password, both sent in plain-text.
         */
        public static final String PASSWORD = "m.login.password";

        /**
         * The user completes a Google ReCaptcha 2.0 challenge.
         */
        public static final String RECAPTCHA = "m.login.recaptcha";

        /**
         * Authentication is supported via OAuth2 URLs. This login consists of multiple requests.
         */
        public static final String OAUTH2 = "m.login.oauth2";

        /**
         * Authentication is supported by authorising an email address with an identity server.
         */
        public static final String EMAIL_IDENTITY = "m.login.email.identity";

        /**
         * The client submits a login token.
         */
        public static final String TOKEN = "m.login.token";

        /**
         * Dummy authentication always succeeds and requires no extra parameters. Its purpose is to allow servers to not require any
         * form of User-Interactive Authentication to perform a request.
         */
        public static final String DUMMY = "m.login.dummy";

        /**
         * Bypassing registration flow for application service's users.
         */
        public static final String APPLICATION_SERVICE = "m.login.application_service";
    }

    /**
     * Identifier types.
     */
    class IdentifierType {

        protected IdentifierType() {
        }

        /**
         * Matrix user id (MXID).
         */
        public static final String USER = "m.id.user";

        /**
         * Third party user id.
         */
        public static final String THIRD_PARTY = "m.id.thirdparty";

        /**
         * User phone.
         */
        public static final String PHONE = "m.id.phone";
    }

    /**
     * Gets the homeserver's supported login types to authenticate users. Clients should pick one of these and supply it as the
     * type when logging in.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link SupportedLoginResponse}.
     * <p>Status code 200: The login types the homeserver supports.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param servletRequest Servlet request.
     * @param asyncResponse  Asynchronous response.
     */
    @ApiOperation(
        value = "Gets the homeserver's supported login types to authenticate users.",
        notes = "Clients should pick one of these and supply it as the type when logging in.",
        response = SupportedLoginResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The login types the homeserver supports."),
        @ApiResponse(code = 429, message = "This request was rate-limited.")
    })
    @GET
    @RateLimit
    @Path("/login")
    void supportedLoginTypes(
        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests.
     * <br>
     * If the client does not supply a device_id, the server must auto-generate one.
     * <br>
     * The returned access token must be associated with the device_id supplied by the client or generated by the server.
     * The server may invalidate any access token previously associated with that device.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * Return: {@link LoginResponse}.
     * <p>Status code 200: The user has been authenticated.</p>
     * <p>Status code 400: Part of the request was invalid. For example, the login type may not be recognised.</p>
     * <p>Status code 401: The login attempt failed. For example, the password may have been incorrect.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     *
     * @param loginRequest   JSON body request.
     * @param servletRequest Servlet request.
     * @param asyncResponse  Asynchronous response.
     */
    @ApiOperation(
        value = "Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests",
        notes = "If the client does not supply a device_id, the server must auto-generate one. "
            + "The returned access token must be associated with the device_id supplied by the client or generated by the server. "
            + "The server may invalidate any access token previously associated with that device",
        response = LoginResponse.class
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The user has been authenticated"),
        @ApiResponse(code = 400, message = "Part of the request was invalid. For example, the login type may not be recognised"),
        @ApiResponse(code = 401, message = "The login attempt failed. For example, the password may have been incorrect."),
        @ApiResponse(code = 429, message = "This request was rate-limited.")
    })
    @POST
    @RateLimit
    @Path("/login")
    void login(
        @ApiParam(
            value = "login request."
        ) LoginRequest loginRequest,

        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse
    );

    /**
     * Invalidates an existing access token, so that it can no longer be used for authorization.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * The access token used in the request was successfully invalidated.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The access token used in the request was succesfully invalidated.</p>
     *
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @ApiOperation(
        value = "Invalidates an existing access token, so that it can no longer be used for authorization.",
        response = EmptyResponse.class,
        authorizations = @Authorization("Authorization")
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The access token used in the request was succesfully invalidated")
    })
    @POST
    @Secured
    @Path("/logout")
    void logout(
        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );

    /**
     * Invalidates all access tokens for a user, so that they can no longer be used for authorization. This includes the access token
     * that made this request.
     * <br>
     * This endpoint does not require UI authorization because UI authorization is designed to protect against attacks where the
     * someone gets hold of a single access token then takes over the account. This endpoint invalidates all access tokens for the
     * user, including the token used in the request, and therefore the attacker is unable to take over the account in this way.
     * <br>
     * <b>Requires auth</b>: Yes.
     * <br>
     * Return: {@link EmptyResponse}.
     * <p>Status code 200: The user's access tokens were succesfully invalidated.</p>
     *
     * @param servletRequest  Servlet request.
     * @param asyncResponse   Asynchronous response.
     * @param securityContext Security context.
     */
    @ApiOperation(
        value = "Invalidates all access tokens for a user, so that they can no longer be used for authorization. "
            + "This includes the access token that made this request",
        notes = "This endpoint does not require UI authorization because UI authorization is designed to protect against attacks where the "
            + "someone gets hold of a single access token then takes over the account. This endpoint invalidates all access tokens for the "
            + "user, including the token used in the request, and therefore the attacker is unable to take over the account in this way.",
        response = EmptyResponse.class,
        authorizations = @Authorization("Authorization")
    )
    @ApiResponses( {
        @ApiResponse(code = 200, message = "The user's access tokens were succesfully invalidated")
    })
    @POST
    @Secured
    @Path("/logout/all")
    void logoutAll(
        @Context HttpServletRequest servletRequest,
        @Suspended AsyncResponse asyncResponse,
        @Context SecurityContext securityContext
    );
}
