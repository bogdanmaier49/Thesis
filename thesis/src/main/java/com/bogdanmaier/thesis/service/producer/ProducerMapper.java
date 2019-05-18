package com.bogdanmaier.thesis.service.producer;

import com.bogdanmaier.thesis.dataaccess.producer.Producer;
import com.bogdanmaier.thesis.service.country.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerMapper {

    @Autowired
    private CountryMapper countryMapper;

    public Producer toEnitity (ProducerDto producerDto) {
        Producer producer = new Producer();
        producer.setId(producerDto.getId());
        producer.setName(producerDto.getName());
        producer.setCountry(countryMapper.toEntity(producerDto.getCountry()));
        return producer;
    }

    public ProducerDto toService (Producer producer) {
        ProducerDto producerDto = new ProducerDto();
        producerDto.setId(producer.getId());
        producerDto.setName(producer.getName());
        producerDto.setCountry(countryMapper.toService(producer.getCountry()));
        return producerDto;
    }

}
