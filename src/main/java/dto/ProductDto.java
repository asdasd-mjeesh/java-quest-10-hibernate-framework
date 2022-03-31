package dto;

import java.time.LocalDate;

public record ProductDto(Long id,
                         String name,
                         Integer cost,
                         LocalDate shelfLife,
                         Integer count,
                         Integer price,
                         ProducerDto producer) {
}
