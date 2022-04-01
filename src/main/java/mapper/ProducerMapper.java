package mapper;

import dto.ProducerDto;
import entity.Producer;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProducerMapper implements Mapper<Producer, ProducerDto> {

    @Override
    public ProducerDto fullMap(Producer object) {
        return new ProducerDto(
                object.getId(),
                object.getName(),
                object.getContact(),
                ProductMapper.dontFullMap(object.getProducts())
        );
    }

    @Override
    public List<ProducerDto> fullMap(List<Producer> collection) {
        List<ProducerDto> producersDto = new ArrayList<>(collection.size());
        for (Producer producer : collection) {
            producersDto.add(fullMap(producer));
        }

        return producersDto;
    }

    public ProducerDto dontFullMap(Producer object) {
        return new ProducerDto(
                object.getId(),
                object.getName(),
                object.getContact(),
                null
        );
    }

    public List<ProducerDto> dontFullMap(List<Producer> collection) {
        List<ProducerDto> producersDto = new ArrayList<>(collection.size());
        for (Producer producer : collection) {
            producersDto.add(dontFullMap(producer));
        }

        return producersDto;
    }
}
