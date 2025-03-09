package mdimuro.ecommerce.microservices.inventory_service.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mdimuro.ecommerce.microservices.inventory_service.application.dto.ArticleInboundDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDTO {

    @NotNull @NotBlank
    private String name;
    @NotNull @Positive
    private Float price;
    @NotNull @Positive
    private Integer quantity;
    @NotNull @NotBlank @Pattern(regexp = "^(http|https)://.*$")
    private String photoUrl;
    @NotNull @NotBlank
    private String article_ID;

    public ArticleInboundDTO toArticleInboundDTO() {
        return ArticleInboundDTO.builder()
            .name(this.name)
            .price(this.price)
            .quantity(this.quantity)
            .photoUrl(this.photoUrl)
            .article_ID(this.article_ID)
            .build();
    }
}
