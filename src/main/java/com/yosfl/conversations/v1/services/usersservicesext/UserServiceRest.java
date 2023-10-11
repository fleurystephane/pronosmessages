package com.yosfl.conversations.v1.services.usersservicesext;

import com.yosfl.conversations.v1.entities.dtos.UserExt;
import com.yosfl.exceptions.ObjectNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Service user pour récupération de toutes les infos liées aux user à travers l'API REST.
 */
@ApplicationScoped
public class UserServiceRest implements UserService {
    @Inject
    @RestClient
    ExtensionsUsersService usersService;

    static String BEARER_TEC = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlY25pY2FsQGV4YW1wbGUuY29tIn0.Wt3KgNVlQKr7BR_Yhx9hHWh-oZjyf9luzkmOrXwjjow";
    static String EMAIL_TEC = "tecnical@example.com";

    @Override
    public UserExt getUserExtByEmail(String email) throws ObjectNotFoundException {
        UserDTOExt result = null;
        try{
            result = usersService.getUserByEmail(BEARER_TEC,EMAIL_TEC,email);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }

        if(result == null){
            throw new ObjectNotFoundException("Aucun user trouvé avec l'email = "+email);
        }
        UserExt user = new UserExt();
        user.setId(result.getId());
        user.setUsername(result.getUsername());
        user.setDisplayname(result.getDisplayName());
        return user;
    }
}
