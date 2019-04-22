package com.bogdanmaier.thesis.dataaccess.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "DateAdded")
    private Date dateAdded;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Details", referencedColumnName = "Id")
    private UserDetails userDetails;
}
