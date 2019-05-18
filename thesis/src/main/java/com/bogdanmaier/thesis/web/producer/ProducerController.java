package com.bogdanmaier.thesis.web.producer;

import com.bogdanmaier.thesis.service.ServiceException;
import com.bogdanmaier.thesis.service.producer.ProducerDto;
import com.bogdanmaier.thesis.service.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProducerDto> create (@RequestBody  ProducerDto producerDto) {
        ResponseEntity<ProducerDto> returnValue = null;

        try {
            ProducerDto createdProducer = producerService.create(producerDto);
            returnValue = new ResponseEntity<>(createdProducer, HttpStatus.CREATED);
        } catch (ServiceException e) {
            System.out.println("Producer could not be created: " + e.getMessage());
            returnValue = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return returnValue;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProducerDto>> findAll () {

        List<ProducerDto> producerDtos = producerService.findAll();
        ResponseEntity<List<ProducerDto>> returnValue = new ResponseEntity<>(producerDtos, HttpStatus.OK);

        return returnValue;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProducerDto> findById (@PathVariable Long id) {
        ProducerDto producerDto = producerService.findById(id);
        ResponseEntity<ProducerDto> returnValue = new ResponseEntity<>(producerDto, HttpStatus.OK);
        return returnValue;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ProducerDto> delete (@PathVariable Long id) {
        ProducerDto producerDto = producerService.findById(id);
        ResponseEntity<ProducerDto> returnValue = null;

        try {
            producerService.delete(producerDto);
            returnValue = new ResponseEntity<>(producerDto, HttpStatus.OK);
        }catch (ServiceException e) {
            System.out.println("Producer could not be deleted: " + e.getMessage());
            returnValue = new ResponseEntity<>(producerDto, HttpStatus.BAD_REQUEST);
        }

        return returnValue;
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<ProducerDto> update (@RequestBody ProducerDto producerDto) {
        ResponseEntity<ProducerDto> returnValue = null;

        try {
            ProducerDto updatedProdcuer = producerService.update(producerDto);
            returnValue = new ResponseEntity<>(updatedProdcuer, HttpStatus.OK);
        }catch (ServiceException e) {
            System.out.println("Producer could not be updated: " + e.getMessage());
            returnValue = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return returnValue;
    }

}
