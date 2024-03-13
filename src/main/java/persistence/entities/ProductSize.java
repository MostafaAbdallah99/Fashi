package persistence.entities;

import lombok.Getter;

@Getter
public enum ProductSize {
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("X-Large");
    private final String size;

    ProductSize(String size) {
        this.size = size;
    }
}
