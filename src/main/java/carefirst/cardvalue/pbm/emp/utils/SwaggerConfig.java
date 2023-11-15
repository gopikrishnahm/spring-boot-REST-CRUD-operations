package carefirst.cardvalue.pbm.emp.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI employeeMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Employee Information")
                        .description("This API will detail the different REST endpoints associated with Employee Entity ")
                        .version("1.0"));
    }
}
