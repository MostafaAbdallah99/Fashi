package controllers.mapping;

import lombok.Getter;

@Getter
public enum URLMapping {
    ADMIN_PRODUCT("/admin/product");

    private final String url;

    URLMapping(String url) {
        this.url = url;
    }
}
