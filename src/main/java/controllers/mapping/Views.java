package controllers.mapping;

import lombok.Getter;

@Getter
public enum Views {
    ADD_PRODUCT("/add-product.html"),
    EDIT_PRODUCT("/edit-product.html"),
    VIEW_PRODUCT("/products.html"),
    ERROR_PAGE("/error-404.jsp");

    private final String viewName;

    Views(String viewName) {
        this.viewName = viewName;
    }

}
