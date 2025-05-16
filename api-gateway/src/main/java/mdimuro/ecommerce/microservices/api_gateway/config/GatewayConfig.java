package mdimuro.ecommerce.microservices.api_gateway.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {
    
    @Bean
    public RouterFunction<ServerResponse> getInventoryServiceRoutes() {
        return GatewayRouterFunctions.route("inventory-service")
            .route(RequestPredicates.path("/api/articles"), HandlerFunctions.http("http://ecommerce-microservices-gateway-app-1:5001"))
            .build();
    }
}
