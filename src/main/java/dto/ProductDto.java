package dto;

import net.bytebuddy.dynamic.DynamicType;

import java.time.LocalDate;
import java.util.Optional;

public record ProductDto(Long id,
                         String name,
                         Integer cost,
                         LocalDate shelfLife,
                         Integer count,
                         Integer price,
                         ProducerDto producer) {

    @Override
    public String toString() {
        String producerStr;
        if (producer == null) {
            producerStr = "";
        } else {
            producerStr = "producer=" + producer.name();
        }


        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", shelfLife=" + shelfLife +
                ", count=" + count +
                ", price=" + price +
                ", " + producerStr +
                '}';
    }
}
