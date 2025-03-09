package mdimuro.ecommerce.microservices.inventory_service.domain.dto;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mdimuro.ecommerce.microservices.inventory_service.domain.entities.Article;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private String ID;
    private String name;
    private Float price;
    private Integer quantity;
    private String photoUrl;
    private LocalDate date;
    private String article_ID;
    private String user_ID;

    public Article toArticle() {
        return Article.builder()
            .ID(this.ID)
            .name(this.name)
            .price(this.price)
            .quantity(this.quantity)
            .photoUrl(this.photoUrl)
            .date(this.date)
            .article_ID(this.article_ID)
            .user_ID(this.user_ID)
            .build();
    }
}