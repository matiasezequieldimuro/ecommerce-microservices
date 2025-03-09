package mdimuro.ecommerce.microservices.inventory_service.application.useCases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleInboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleOutboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.domain.dto.ArticleDTO;
import mdimuro.ecommerce.microservices.inventory_service.domain.entities.Article;
import mdimuro.ecommerce.microservices.inventory_service.domain.interfaces.IArticleGateway;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.utils.GlobalLogger;
import mdimuro.ecommerce.microservices.inventory_service.presentation.presenters.IArticlePresenter;

@Service
public class AddArticlesUseCase {
    
    private IArticleGateway articleGateway;
    private IArticlePresenter articlePresenter;

    public AddArticlesUseCase(IArticleGateway articleGateway, IArticlePresenter articlePresenter) {
        this.articleGateway = articleGateway;
        this.articlePresenter = articlePresenter;
    }

    public List<ArticleOutboundDTO> execute(String user_ID, List<ArticleInboundDTO> articles) {
        GlobalLogger.getInstance().info(">>> Add Articles Use Case - execute");
        List<ArticleDTO> data = articles.stream().map(a -> {
            var dto = a.toArticleDTO();
            dto.setDate(LocalDate.now());
            dto.setUser_ID(user_ID);
            return dto;
        }).toList();
        List<Article> res = this.articleGateway.addArticlesToInventory(user_ID, data);
        return this.articlePresenter.serveArticles(res);
    }
}
