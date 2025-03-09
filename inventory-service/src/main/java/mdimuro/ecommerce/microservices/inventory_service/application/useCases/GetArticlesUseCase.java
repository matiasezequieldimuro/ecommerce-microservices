package mdimuro.ecommerce.microservices.inventory_service.application.useCases;

import java.util.List;

import org.springframework.stereotype.Service;

import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleOutboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.domain.entities.Article;
import mdimuro.ecommerce.microservices.inventory_service.domain.interfaces.IArticleGateway;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.utils.GlobalLogger;
import mdimuro.ecommerce.microservices.inventory_service.presentation.presenters.IArticlePresenter;

@Service
public class GetArticlesUseCase {
    
    private IArticleGateway articleGateway;
    private IArticlePresenter articlePresenter;

    public GetArticlesUseCase(IArticleGateway articleGateway, IArticlePresenter articlePresenter) {
        this.articleGateway = articleGateway;
        this.articlePresenter = articlePresenter;
    }

    public List<ArticleOutboundDTO> execute(String user_ID) {
        GlobalLogger.getInstance().info(">>> Get Articles Use Case - execute");
        List<Article> res = this.articleGateway.retrieveArticlesFromInventory(user_ID);
        return this.articlePresenter.serveArticles(res);
    }

}
