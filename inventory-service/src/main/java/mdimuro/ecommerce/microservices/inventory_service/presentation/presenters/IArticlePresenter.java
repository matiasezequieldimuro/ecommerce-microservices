package mdimuro.ecommerce.microservices.inventory_service.presentation.presenters;

import java.util.List;

import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleOutboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.domain.entities.Article;

public interface IArticlePresenter {
    
    public List<ArticleOutboundDTO> serveArticles(List<Article> article);
}
