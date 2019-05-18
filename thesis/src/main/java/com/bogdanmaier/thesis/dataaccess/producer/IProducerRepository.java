package com.bogdanmaier.thesis.dataaccess.producer;

import com.bogdanmaier.thesis.dataaccess.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProducerRepository extends JpaRepository<Producer, Long> {

    List<Producer> findAllByCountry (Country country);

    Producer findByName (String name);

}
