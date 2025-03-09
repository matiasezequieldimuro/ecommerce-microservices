package mdimuro.ecommerce.microservices.inventory_service.application.useCases;

import org.springframework.stereotype.Service;

import mdimuro.ecommerce.microservices.inventory_service.domain.interfaces.IArticleGateway;
import mdimuro.ecommerce.microservices.inventory_service.infraestructure.utils.GlobalLogger;

@Service
public class RemoveArticleUseCase {
    
    private IArticleGateway articleGateway;

    public RemoveArticleUseCase(IArticleGateway articleGateway) {
        this.articleGateway = articleGateway;
    }

    public void execute(String articleID) {
        GlobalLogger.getInstance().info(">>> RemoveArticleUseCase - execute");
        this.articleGateway.deleteArticleFromInventory(articleID);
    }
}
