package mdimuro.ecommerce.microservices.inventory_service.presentation.gateways;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import mdimuro.ecommerce.microservices.inventory_service.application.errors.ArticlesSavedException;
import mdimuro.ecommerce.microservices.inventory_service.domain.dto.ArticleDTO;
import mdimuro.ecommerce.microservices.inventory_service.domain.entities.Article;
import mdimuro.ecommerce.microservices.inventory_service.domain.interfaces.IArticleGateway;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.repositories.interfaces.IArticleRepositoryMongoDB;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.utils.GlobalLogger;
import mdimuro.ecommerce.microservices.inventory_service.presentation.schemas.ArticleSchema;

@Component
public class ArticleGatewayMongoDB implements IArticleGateway {

    private IArticleRepositoryMongoDB articleRepository;

    public ArticleGatewayMongoDB(IArticleRepositoryMongoDB articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> addArticlesToInventory(String user_ID, List<ArticleDTO> articles) {
        GlobalLogger.getInstance().info(">>> Article Gateway MongoDB - addArticlesToInventory");
        List<Article> res = new ArrayList<>();
        List<ArticleSchema> inventory = this.articleRepository.findAllByUserID(user_ID);

        // Crear un mapa para acceso rápido a los artículos existentes
        Map<String, ArticleSchema> inventoryMap = new HashMap<>();
        for (ArticleSchema i : inventory) {
            inventoryMap.put(i.getArticle_ID(), i);
        }

        // Si ya existe en el inventario, actualizo sólo el stock.
        for (ArticleDTO a : articles) {
            ArticleSchema existingArticle = inventoryMap.get(a.getArticle_ID());
            System.out.println(existingArticle);
            if (existingArticle != null) {
                GlobalLogger.getInstance().info(">>> Article " + a.getName() + " already exists in inventory, adding new stock.");
                existingArticle.addNewStock(a.getQuantity());
            } else {
                GlobalLogger.getInstance().info(">>> New article " + a.getName() + " will be added to inventory.");
                ArticleSchema newArticle = ArticleSchema.builder()
                    .name(a.getName())
                    .price(a.getPrice())
                    .quantity(a.getQuantity())
                    .photoUrl(a.getPhotoUrl())
                    .date(a.getDate())
                    .article_ID(a.getArticle_ID())
                    .user_ID(a.getUser_ID())
                    .build();
                inventory.add(newArticle);
                inventoryMap.put(newArticle.getArticle_ID(), newArticle);
            }
        }
        try {
            this.articleRepository.saveAll(inventory);
            GlobalLogger.getInstance().info(">>> Articles have been saved to inventory");
            for (ArticleSchema article : inventory) {
                res.add(article.toArticle());
            }
            return res;
        } 
        catch (Exception e) {
            throw new ArticlesSavedException("Articles could not be saved to inventory: " + e.getMessage());
        }
    }

    @Override
    public List<Article> retrieveArticlesFromInventory(String user_ID) {
        GlobalLogger.getInstance().info(">>> Article Gateway MongoDB - retrieveArticlesFromInventory");
        List<ArticleSchema> inventory = this.articleRepository.findAllByUserID(user_ID);
        return inventory.stream().map(ArticleSchema::toArticle).toList();
    }

    @Override
    public Article updateArticleInInventory(String user_ID, String articleID, ArticleDTO article) {
        throw new UnsupportedOperationException("Unimplemented method 'updateArticleInInventory'");
    }

    @Override
    public void deleteArticleFromInventory(String user_ID, String articleID) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteArticleFromInventory'");
    }
    
}
