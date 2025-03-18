package mdimuro.ecommerce.microservices.inventory_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mdimuro.ecommerce.microservices.inventory_service.domain.dto.ArticleDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInboundDTO {

    private String name;
    private Float price;
    private Integer quantity;
    private String photoUrl;
    private String article_ID;
    
    public ArticleDTO toArticleDTO() {
        return ArticleDTO.builder()
            .name(this.name)
            .price(this.price)
            .quantity(this.quantity)
            .photoUrl(this.photoUrl)
            .article_ID(this.article_ID)
            .build();
    }
}