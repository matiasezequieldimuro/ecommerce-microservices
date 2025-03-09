package mdimuro.ecommerce.microservices.inventory_service.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleInboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleOutboundDTO;
import mdimuro.ecommerce.microservices.inventory_service.application.useCases.AddArticlesUseCase;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.utils.GlobalLogger;
import mdimuro.ecommerce.microservices.inventory_service.presentation.dto.ArticleRequestDTO;

@RestController
@RequestMapping(path = "/api/articles")
public class ArticleController {
    
    private AddArticlesUseCase addArticlesUseCase;

    public ArticleController(AddArticlesUseCase addArticlesUseCase) {
        this.addArticlesUseCase = addArticlesUseCase;
    }

    @PostMapping()
    public ResponseEntity<List<ArticleOutboundDTO>> addArticlesToInventory(@Valid @RequestBody List<ArticleRequestDTO> articles) {
        GlobalLogger.getInstance().info(">>> Article Controller - addArticlesToInventory");
        List<ArticleInboundDTO> data = articles.stream().map(ArticleRequestDTO::toArticleInboundDTO).toList();
        var res = this.addArticlesUseCase.execute("mdimuro", data); // TODO: get user_ID from token
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
