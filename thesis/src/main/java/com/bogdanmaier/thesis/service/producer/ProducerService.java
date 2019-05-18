package com.bogdanmaier.thesis.service.producer;

import com.bogdanmaier.thesis.dataaccess.producer.IProducerRepository;
import com.bogdanmaier.thesis.dataaccess.producer.Producer;
import com.bogdanmaier.thesis.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {

    @Autowired
    private IProducerRepository producerRepository;

    @Autowired
    private ProducerMapper producerMapper;

    public ProducerDto create (ProducerDto producerDto) throws ServiceException {
        if (producerRepository.findByName(producerDto.getName()) != null) {
            throw new ServiceException("Producer with name " + producerDto.getName() + " already exists");
        }

        ProducerDto createdProducer = producerMapper.toService(producerRepository.save(producerMapper.toEnitity(producerDto)));
        return createdProducer;
    }

    public List<ProducerDto> findAll () {
        List<ProducerDto> returnValue = new ArrayList<>();

        List<Producer> producers = producerRepository.findAll();
        for (Producer producer : producers) {
            returnValue.add(producerMapper.toService(producer));
        }

        return returnValue;
    }

    public ProducerDto findById (Long id) {
        Optional<Producer> optional = producerRepository.findById(id);
        ProducerDto returnValue = null;

        if (optional.isPresent()) {
            returnValue = producerMapper.toService(optional.get());
        }

        return returnValue;
    }

    public void delete (ProducerDto producerDto) throws ServiceException{
        if (findById(producerDto.getId()) == null) {
            throw new ServiceException("Producer with id " + producerDto.getId() + " does not exists");
        }

        producerRepository.delete(producerMapper.toEnitity(producerDto));
    }

    public ProducerDto update (ProducerDto producerDto) throws ServiceException{

        Producer producer = producerRepository.findByName(producerDto.getName());
        if (producer != null && !producer.getName().toLowerCase().equals(producerDto.getName().toLowerCase())) {
            throw new ServiceException("Producer with name " + producerDto.getName() + " already exists");
        }

        ProducerDto updatedProducer = producerMapper.toService(producerRepository.save(producerMapper.toEnitity(producerDto)));

        return updatedProducer;
    }

}
