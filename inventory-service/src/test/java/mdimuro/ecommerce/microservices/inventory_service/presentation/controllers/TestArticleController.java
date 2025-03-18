package mdimuro.ecommerce.microservices.inventory_service.presentation.controllers;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import mdimuro.ecommerce.microservices.inventory_service.config.TestsMongoDBContainer;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.repositories.interfaces.IArticleRepositoryMongoDB;
import mdimuro.ecommerce.microservices.inventory_service.presentation.schemas.ArticleSchema;

@SpringBootTest // To load de app context - Instantiate dependencies
@AutoConfigureMockMvc
public class TestArticleController extends TestsMongoDBContainer {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IArticleRepositoryMongoDB articleRepositoryMongoDB;

    @BeforeEach
    void setUp() {
        var user_id = "mdimuro";
        
        var firstArticle = ArticleSchema.builder().article_ID("11111").name("First Article").photoUrl("").price(1000f).quantity(5).user_ID(user_id).date(LocalDate.now()).build();
        var secondArticle = ArticleSchema.builder().article_ID("22222").name("Second Article").photoUrl("").price(2000f).quantity(10).user_ID(user_id).date(LocalDate.now()).build();
        var thirdArticle = ArticleSchema.builder().article_ID("33333").name("Third Article").photoUrl("").price(3000f).quantity(15).user_ID("randomuser").date(LocalDate.now()).build();

        articleRepositoryMongoDB.save(firstArticle);
        articleRepositoryMongoDB.save(secondArticle);
        articleRepositoryMongoDB.save(thirdArticle);
    }

    @AfterEach
    void cleanUp() {
        articleRepositoryMongoDB.deleteAll();
    }

    @Test
    void retrieveArticlesFromInventory_UserHasSomeItems_UploadOnlyNewArticles() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
            .post("/api/articles")
            .contentType("application/json")
            .content("[{\"id\":\"67d98c1205ce6409dd4cd8d1\",\"name\":\"First Article\",\"price\":1000.0,\"quantity\":5,\"photoUrl\":\"\",\"date\":\"2025-03-18\",\"article_ID\":\"11111\",\"user_ID\":\"mdimuro\"},{\"id\":\"67d98c1205ce6409dd4cd8d2\",\"name\":\"Fourth Article\",\"price\":4000.0,\"quantity\":20,\"photoUrl\":\"\",\"date\":\"2025-03-18\",\"article_ID\":\"44444\",\"user_ID\":\"mdimuro\"}]")
        )
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantity").value(10))
        .andExpect(MockMvcResultMatchers.jsonPath("$[2].article_ID").value("44444"));
    }

    // Tests Nomenclature : [Method / Action] _ [InitialConditions] _ [ExpectedResults]
}
