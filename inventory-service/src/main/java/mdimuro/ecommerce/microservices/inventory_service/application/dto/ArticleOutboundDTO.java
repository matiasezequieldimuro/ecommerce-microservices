package mdimuro.ecommerce.microservices.inventory_service.application.dto;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record ArticleOutboundDTO(
    String id,
    String name,
    Float price,
    Integer quantity,
    String photoUrl,
    LocalDate date,
    String article_ID,
    String user_ID
) {};
