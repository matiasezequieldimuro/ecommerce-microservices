package mdimuro.ecommerce.microservices.inventory_service.presentation.presenters.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleOutboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.domain.entities.Article;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.utils.GlobalLogger;
import mdimuro.ecommerce.microservices.inventory_service.presentation.presenters.IArticlePresenter;

@Component
public class ArticlePresenter implements IArticlePresenter {

    @Override
    public List<ArticleOutboundDTO> serveArticles(List<Article> article) {
        GlobalLogger.getInstance().info(">>> Article Presenter - serveArticles");
        var res = new ArrayList<ArticleOutboundDTO>();
        for (Article a : article) {
            res.add(ArticleOutboundDTO
            .builder()
            .id(a.getID())
            .name(a.getName())
            .price(a.getPrice())
            .quantity(a.getQuantity())
            .photoUrl(a.getPhotoUrl())
            .date(a.getDate())
            .article_ID(a.getArticle_ID())
            .user_ID(a.getUser_ID())
            .build());
        }
        return res;
    }
    
}
