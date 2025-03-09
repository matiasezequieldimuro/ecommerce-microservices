package mdimuro.ecommerce.microservices.inventory_service.infraestructure.repositories.interfaces;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mdimuro.ecommerce.microservices.inventory_service.presentation.schemas.ArticleSchema;

public interface IArticleRepositoryMongoDB extends CrudRepository<ArticleSchema, String> {
    
    @Query(value = "{ 'user_ID' : ?0 }")
    List<ArticleSchema> findAllByUserID(String user_ID);

}
