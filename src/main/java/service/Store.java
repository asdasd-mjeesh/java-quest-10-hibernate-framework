package service;

import dto.ProducerDto;
import dto.ProductDto;
import entity.Producer;
import entity.Product;
import mapper.ProducerMapper;
import mapper.ProductMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.ProducerRepository;
import repository.ProductRepository;

import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private final Session currentSession;

    private static final ProducerMapper producerMapper = new ProducerMapper();
    private static final ProductMapper productMapper = new ProductMapper();
    private final ProducerRepository producerRepository;
    private final ProductRepository productRepository;

    public Store(SessionFactory sessionFactory) {
        currentSession = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                ((proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args)));
        producerRepository = new ProducerRepository(currentSession);
        productRepository = new ProductRepository(currentSession);
    }

    public ProducerDto saveProducer(String name, int contact) {
        try {
            currentSession.beginTransaction();
            Producer producer = Producer.builder()
                    .name(name)
                    .contact(contact)
                    .build();
            Producer savedProducer = producerRepository.save(producer);

            return producerMapper.fullMap(savedProducer);
        } finally {
            currentSession.getTransaction().commit();
        }
    }

    public ProductDto saveProduct(String name, int cost, LocalDate shelfLife, int count, Long producerId) {
        try {
            currentSession.beginTransaction();
            
            Product product = new Product(name, cost, shelfLife, count);
            Producer producer = producerRepository.findById(producerId).get();
            producer.addProduct(product);

            return productMapper.fullMap(product);
        } finally {
            currentSession.getTransaction().commit();
        }
    }

    public boolean deleteProduct(Long id) {
        try {
            currentSession.beginTransaction();
            var maybeProduct = productRepository.findById(id);
            maybeProduct.ifPresent(product -> productRepository.delete(maybeProduct.get()));
            return maybeProduct.isPresent();
        } finally {
            currentSession.getTransaction().commit();
        }
    }

    public boolean deleteProducer(Long id) {
        try {
            currentSession.beginTransaction();
            var maybeProducer = producerRepository.findById(id);
            maybeProducer.ifPresent(product -> producerRepository.delete(maybeProducer.get()));
            return maybeProducer.isPresent();
        } finally {
            currentSession.getTransaction().commit();
        }
    }

    public List<ProductDto> getProducts() {
        try {
            currentSession.beginTransaction();
            List<Product> products = productRepository.findAll();
            return productMapper.fullMap(products);
        } finally {
            currentSession.getTransaction().commit();
        }
    }

    public List<ProducerDto> getProducers() {
        try {
            currentSession.beginTransaction();
            List<Producer> producers = producerRepository.findAll();
            return producerMapper.fullMap(producers);
        } finally {
            currentSession.getTransaction().commit();
        }
    }

    // supported method for a and b
    public List<ProductDto> getAllProductsWithName(String name) {
        try {
            currentSession.beginTransaction();
            List<Product> products = productRepository.findAll(name);
            return productMapper.fullMap(products);
        } finally {
            currentSession.getTransaction().commit();
        }
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
