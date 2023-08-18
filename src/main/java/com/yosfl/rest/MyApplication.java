package com.yosfl.rest;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@OpenAPIDefinition(
        tags = {
                @Tag(name="Users", description="Operations related to users"),
                @Tag(name="Publications", description="Operations related to publications"),
                @Tag(name="Connections", description = "Operations related to the connections")
        },
        info = @Info(
                title="L'API des Pronos!!!",
                version = "0.0.9",
                contact = @Contact(
                        name = "API Pronos developer",
                        url = "",
                        email = "sfleury72@gmail.com")
                ),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecuritySchemes(
        @SecurityScheme(
                apiKeyName = "bearerAuth",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        )
)
@ApplicationPath("/shareskillsapi")
public class MyApplication extends Application {

}
