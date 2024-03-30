package exceptions;

import java.math.BigDecimal;

public class InsufficientCreditException extends RuntimeException {

    private final BigDecimal creditLimit;
    public InsufficientCreditException(BigDecimal creditLimit) {
        super("Insufficient credit limit");
        this.creditLimit = creditLimit;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }
}
