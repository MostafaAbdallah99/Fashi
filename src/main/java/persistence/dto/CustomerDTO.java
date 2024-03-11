package persistence.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record CustomerDTO(
        Integer id,
        String customerName,
        LocalDate birthday,
        String password,
        String job,
        String email,
        BigDecimal creditLimit,
        String city,
        String country,
        String streetNo,
        String streetName,
        String interests,
        CartDTO cart
) {}