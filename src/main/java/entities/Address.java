package entities;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;

    private String country;

    private String streetNo;

    private String streetName;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
