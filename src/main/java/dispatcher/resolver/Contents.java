package dispatcher.resolver;

import lombok.Getter;

@Getter
public enum Contents {
    HOME("home"),
    LOGIN("login"),
    REGISTER("register"),
    SHOP("shop"),
    CHECKOUT("check-out"),
    PRODUCT("product"),
    ADMIN("admin"),
    PROFILE("profile"),
    ERROR("error-404"),
    EX("ex"),
    JSON("json");

    private final String viewName;

    Contents(String viewName) {
        this.viewName = viewName;
    }

}
