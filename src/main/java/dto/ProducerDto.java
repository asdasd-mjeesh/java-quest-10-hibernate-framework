package dto;

import java.util.List;

public record ProducerDto(Long id,
                          String name,
                          Integer contact,
                          List<ProductDto> productList) {

    @Override
    public String toString() {
        return "id: " + id + " | " + name + " | +" + contact;
    }

    public String productsToString() {
        StringBuilder result = new StringBuilder();
        for (ProductDto productDto : productList) {
            result.append("-> ").append(productDto);
        }
        return result.toString();
    }
}
