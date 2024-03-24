package persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import persistence.repository.interfaces.UserRepository;
import persistence.repository.repositories.UserRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "customer_name")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "credit_limit", precision = 15, scale = 2)
    private BigDecimal creditLimit;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "street_no", nullable = false)
    private String streetNo;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Column(name = "interests", nullable = false)
    private String interests;

    @OneToOne(mappedBy = "customer")
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new LinkedHashSet<>();

    @Column(name = "isAdmin", nullable = false, columnDefinition = "boolean default false")
    private Boolean isAdmin;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;


}