package mdimuro.ecommerce.microservices.inventory_service.presentation.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleInboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleOutboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.application.useCases.AddArticlesUseCase;
import mdimuro.ecommerce.microservices.inventory_service.application.useCases.GetArticlesUseCase;
import mdimuro.ecommerce.microservices.inventory_service.application.useCases.RemoveArticleUseCase;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.utils.GlobalLogger;
import mdimuro.ecommerce.microservices.inventory_service.presentation.dto.ArticleRequestDTO;

@RestController
@RequestMapping(path = "/api/articles")
public class ArticleController {
    
    private AddArticlesUseCase addArticlesUseCase;
    private GetArticlesUseCase getArticlesUseCase;
    private RemoveArticleUseCase removeArticleUseCase;

    public ArticleController(AddArticlesUseCase addArticlesUseCase, GetArticlesUseCase getArticlesUseCase, RemoveArticleUseCase removeArticleUseCase) {
        this.addArticlesUseCase = addArticlesUseCase;
        this.getArticlesUseCase = getArticlesUseCase;
        this.removeArticleUseCase = removeArticleUseCase;
    }

    @PostMapping()
    public ResponseEntity<List<ArticleOutboundDTO>> addArticlesToInventory(@Valid @RequestBody List<ArticleRequestDTO> articles) {
        GlobalLogger.getInstance().info(">>> Article Controller - addArticlesToInventory");
        List<ArticleInboundDTO> data = articles.stream().map(ArticleRequestDTO::toArticleInboundDTO).toList();
        var res = this.addArticlesUseCase.execute("mdimuro", data); // TODO: get user_ID from token
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ArticleOutboundDTO>> getArticlesFromInventory() {
        GlobalLogger.getInstance().info(">>> Article Controller - getArticlesFromInventory");
        var res = this.getArticlesUseCase.execute("mdimuro");
        return new ResponseEntity<>(res, HttpStatus.OK); // TODO: get user_ID from token
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteArticleFromInventory(@Valid @NotNull @PathVariable(name = "id") String articleId) {
        GlobalLogger.getInstance().info(">>> Article Controller - deleteArticleFromInventory");
        this.removeArticleUseCase.execute(articleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
