package com.yosfl.conversations.v1.services.usersservicesext;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1/users")
@RegisterRestClient
public interface ExtensionsUsersService {
    @GET
    @Path("/byemail")
    UserDTOExt getUserByEmail(@HeaderParam ("Authorization") String token,
                              @HeaderParam("email") String appUser,
                              @QueryParam("email") String email);
}
