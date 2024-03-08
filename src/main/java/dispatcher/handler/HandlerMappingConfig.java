package dispatcher.handler;

import controllers.HomeController;

public class HandlerMappingConfig {
    private static final String CONTEXT_PATH = "/iti_ecommerce_app";
    private static final String APPLICATION_PATH = "/";
    public static HandlerMapping createHandlerMapping() {
        HandlerMapping handlerMapping = new HandlerMapping();

        handlerMapping.addMapping(CONTEXT_PATH + APPLICATION_PATH + "/login", new HomeController());
        handlerMapping.addMapping(CONTEXT_PATH + APPLICATION_PATH, new HomeController());
        return handlerMapping;
    }
}
