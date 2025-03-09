package mdimuro.ecommerce.microservices.inventory_service.domain.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private String ID;
    private String name;
    private Float price;
    private Integer quantity;
    private String photoUrl;
    private LocalDate date;
    private String article_ID;
    private String user_ID;

}