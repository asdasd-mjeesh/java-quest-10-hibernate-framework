package service;

import dto.ProducerDto;
import dto.ProductDto;
import entity.Producer;
import entity.Product;
import lombok.RequiredArgsConstructor;
import mapper.ProducerMapper;
import mapper.ProductMapper;
import repository.ProducerRepository;
import repository.ProductRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Store {
    private static final ProducerMapper producerMapper = new ProducerMapper();
    private static final ProductMapper productMapper = new ProductMapper();
    private final ProducerRepository producerRepository;
    private final ProductRepository productRepository;

    public ProducerDto saveProducer(Producer producer) {
        return producerMapper.mapFrom(producerRepository.save(producer));
    }

    public ProductDto saveProduct(Product product) {
        return productMapper.mapFrom(productRepository.save(product));
    }

    public boolean deleteProduct(Long id) {
        var maybeProduct = productRepository.findById(id);
        maybeProduct.ifPresent(product -> productRepository.delete(id));
        return maybeProduct.isPresent();
    }

    public List<ProductDto> getProducts() {
        return productMapper.mapFrom(productRepository.findAll());
    }

    public List<Producer> getProducers() {
        return producerRepository.findAll();
    }

    // supported method for a and b
    public List<ProductDto> getAllProductsWithName(String name) {
        return productMapper.mapFrom(productRepository.findAll(name));
    }

    // a
    public List<ProductDto> getAllSortedByShelfLifeWithName(String name) {
        var productsWithName = getAllProductsWithName(name);
        return productsWithName.stream()
                .sorted(Comparator.comparing(ProductDto::shelfLife).reversed())
                .collect(Collectors.toList());
    }

    // b
    public List<ProductDto> getProductsWithNameAndCostALess(String name, int maxCost) {
        var productsWithName = getAllProductsWithName(name);
        return productsWithName.stream()
                .filter(product -> product.cost() <= maxCost)
                .collect(Collectors.toList());
    }

    // c
    public List<ProductDto> getAllWithShelfLifeALong(LocalDate minShelfLife) {
        var products = getProducts();
        return products.stream()
                .filter(product -> product.shelfLife().isAfter(minShelfLife))
                .collect(Collectors.toList());
    }

    // d
    public List<ProductDto> getAllSortedByPrice() {
        var products = getProducts();
        return products.stream()
                .sorted(Comparator.comparing(ProductDto::price)
                        .thenComparing(ProductDto::cost))
                .collect(Collectors.toList());
    }
}
