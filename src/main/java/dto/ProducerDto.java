package dto;

import entity.Product;

import java.util.List;

public record ProducerDto(Long id,
                          String name,
                          Integer contact,
                          List<ProductDto> productList) {
}
