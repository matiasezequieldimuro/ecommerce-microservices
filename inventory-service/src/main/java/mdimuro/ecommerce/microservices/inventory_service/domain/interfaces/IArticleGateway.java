package mdimuro.ecommerce.microservices.inventory_service.domain.interfaces;

import java.util.List;

import mdimuro.ecommerce.microservices.inventory_service.domain.dto.ArticleDTO;
import mdimuro.ecommerce.microservices.inventory_service.domain.entities.Article;

public interface IArticleGateway {
    List<Article> addArticlesToInventory(String user_ID, List<ArticleDTO> articles);
    List<Article> retrieveArticlesFromInventory(String user_ID);
    Article updateArticleInInventory(String user_ID, String articleID, ArticleDTO article);
    void deleteArticleFromInventory(String user_ID, String articleID);
}
