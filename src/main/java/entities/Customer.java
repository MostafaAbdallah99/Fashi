package entities;

import entities.Address;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String customerName;

    private Date birthday;

    private String password;

    private String job;

    private String email;

    private BigDecimal creditLimit = BigDecimal.ZERO;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Address address;

    private String interests;

}
