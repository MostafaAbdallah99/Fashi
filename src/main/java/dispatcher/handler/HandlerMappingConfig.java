package dispatcher.handler;


public class HandlerMappingConfig {
    private static final String CONTEXT_PATH = "/iti_ecommerce_app";
    private static final String APPLICATION_PATH = "/";
    public static HandlerMapping createHandlerMapping() {
        HandlerMapping handlerMapping = new HandlerMapping();
        return handlerMapping;
    }
}
