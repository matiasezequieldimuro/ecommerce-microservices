package mdimuro.ecommerce.microservices.inventory_service.infraestructure.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import mdimuro.ecommerce.microservices.inventory_service.config.TestsMongoDBContainer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import mdimuro.ecommerce.microservices.inventory_service.infraestructure.repositories.interfaces.IArticleRepositoryMongoDB;
import mdimuro.ecommerce.microservices.inventory_service.presentation.schemas.ArticleSchema;

@DataMongoTest
public class TestsArticleRepositoryMongoDB extends TestsMongoDBContainer {

    @Autowired
    private IArticleRepositoryMongoDB articleRepositoryMongoDB;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    void cleanUp() {
        articleRepositoryMongoDB.deleteAll();
    }

    @Test
    public void retrieveArticles_ExistsManyDocuments_GetOnlyUserItems() {
        // Arrange
        var user_id = "mdimuro";

        var firstArticle = ArticleSchema.builder().article_ID("11111").name("First Article").photoUrl("").price(1000f).quantity(5).user_ID(user_id).date(LocalDate.now()).build();
        var secondArticle = ArticleSchema.builder().article_ID("22222").name("Second Article").photoUrl("").price(2000f).quantity(10).user_ID(user_id).date(LocalDate.now()).build();
        var thirdArticle = ArticleSchema.builder().article_ID("33333").name("Third Article").photoUrl("").price(3000f).quantity(15).user_ID("randomuser").date(LocalDate.now()).build();

        mongoTemplate.insert(firstArticle);
        mongoTemplate.insert(secondArticle);
        mongoTemplate.insert(thirdArticle);

        // Act
        List<ArticleSchema> articles = articleRepositoryMongoDB.findAllByUserID(user_id);

        // Assert
        assertEquals(2, articles.size());
    }
}
