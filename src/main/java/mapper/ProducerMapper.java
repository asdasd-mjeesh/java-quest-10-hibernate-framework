package mapper;

import dto.ProducerDto;
import entity.Producer;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProducerMapper implements Mapper<Producer, ProducerDto> {


    @Override
    public ProducerDto mapFrom(Producer object) {
        return new ProducerDto(
                object.getId(),
                object.getName(),
                object.getContact(),
                object.getProducts()
        );
    }

    @Override
    public List<ProducerDto> mapFrom(List<Producer> collection) {
        List<ProducerDto> producersDto = new ArrayList<>(collection.size());
        for (Producer producer : collection) {
            producersDto.add(mapFrom(producer));
        }

        return producersDto;
    }
}
