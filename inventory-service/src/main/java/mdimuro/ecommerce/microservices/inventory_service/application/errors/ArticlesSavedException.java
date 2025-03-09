package mdimuro.ecommerce.microservices.inventory_service.application.errors;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArticlesSavedException extends RuntimeException {

    public ArticlesSavedException(String message) {
        super(message);
    }

}