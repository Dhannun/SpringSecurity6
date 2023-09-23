package click.touchit.security.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Muhammad Yunus Abu-Dukhan",
            email = "abudukhanyunus@touchit.click",
            url = "https://touchit.click"
        ),
        description = "API Security Docs [Java, SpringBoot, JWT]",
        title = "SpringSecurity :: API-Docs",
        version = "1.0.0"
    )
)
@SecurityScheme(
    name = "BearerAuth",
    description = "JWT Auth Required",
    scheme = "bearer",
    type = HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
