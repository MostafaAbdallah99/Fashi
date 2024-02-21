package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Table(name = "address")
@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String street;
    private String city;
    private String governorate;
    private String country;

}
