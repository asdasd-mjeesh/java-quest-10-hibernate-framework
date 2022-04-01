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
    public ProductDto fullMap(Product object) {
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
                        .map(producerReadMapper::dontFullMap)
                        .orElse(null)
        );
    }

    @Override
    public List<ProductDto> fullMap(List<Product> collection) {
        List<ProductDto> productsDto = new ArrayList<>(collection.size());
        for (Product product : collection) {
            productsDto.add(fullMap(product));
        }

        return productsDto;
    }

    public static ProductDto dontFullMap(Product object) {
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
                null
        );
    }

    public static List<ProductDto> dontFullMap(List<Product> collection) {
        if (collection.isEmpty()) {
            return new ArrayList<>();
        }

        List<ProductDto> productsDto = new ArrayList<>(collection.size());
        for (Product product : collection) {
            productsDto.add(dontFullMap(product));
        }

        return productsDto;
    }
}
