package services.impl;

import java.util.List;

public class OutOfStockException extends RuntimeException {
    private final List<Long> outOfStockProducts;

    public OutOfStockException(List<Long> outOfStockProducts) {
        super("Some products are out of stock");
        this.outOfStockProducts = outOfStockProducts;
    }

    public List<Long> getOutOfStockProducts() {
        return outOfStockProducts;
    }
}