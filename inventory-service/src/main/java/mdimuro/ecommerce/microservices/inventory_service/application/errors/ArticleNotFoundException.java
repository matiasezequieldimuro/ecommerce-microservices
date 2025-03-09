package mdimuro.ecommerce.microservices.inventory_service.application.errors;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message) {
        super(message);
    }

}
