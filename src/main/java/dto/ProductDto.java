package dto;

import java.time.LocalDate;

public record ProductDto(Long id,
                         String name,
                         Integer cost,
                         LocalDate shelfLife,
                         Integer count,
                         Integer price,
                         ProducerDto producer) {

    @Override
    public String toString() {
        String producerStr = "";
        if (producer != null) {
            producerStr = " производитель: " + producer.name();
        }

        return " id: " + id + "\n" +
                " название: " + name + "\n" +
                " стоимость: " + cost + "\n" +
                " употребить до: " + shelfLife + "\n" +
                " количество: " + count + "\n" +
                " цена: " + price + "\n" +
                producerStr;
    }
}
