package entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    public Product(String name, int cost, LocalDate shelfLife, int count) {
        this.name = name;
        this.cost = cost;
        this.shelfLife = shelfLife;
        this.count = count;
        this.price = cost * count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer cost;

    @Column(name = "shelf_life")
    private LocalDate shelfLife;

    private Integer count;

    private Integer price;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producer_id")
    private Producer producer;
}
