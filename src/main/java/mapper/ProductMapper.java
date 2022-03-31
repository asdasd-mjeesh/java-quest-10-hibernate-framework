package mapper;

import dto.ProductDto;
import entity.Product;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductMapper implements Mapper<Product, ProductDto> {
    private final ProducerMapper producerReadMapper = new ProducerMapper();

    @Override
    public ProductDto mapFrom(Product object) {
        int price = 0;
        if (object.getCost() != null && object.getCount() != null) {
            price = object.getCost() * object.getCount();
        }

        return new ProductDto(
                object.getId(),
                object.getName(),
                object.getCost(),
                object.getShelfLife(),
                object.getCount(),
                price,
                Optional.ofNullable(object.getProducer())
                        .map(producerReadMapper::mapFrom)
                        .orElse(null)
        );
    }

    @Override
    public List<ProductDto> mapFrom(List<Product> collection) {
        List<ProductDto> productsDto = new ArrayList<>(collection.size());
        for (Product product : collection) {
            productsDto.add(mapFrom(product));
        }

        return productsDto;
    }
}
