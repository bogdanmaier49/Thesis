package com.bogdanmaier.thesis.dataaccess.producer;

import com.bogdanmaier.thesis.dataaccess.country.Country;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Producers")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "countryId", referencedColumnName = "id")
    private Country country;

}
