package dispatcher.handler;


import controllers.HomeController;
import dispatcher.ModelAndType;
import dispatcher.resolver.Contents;

public class HandlerMappingConfig {
    private static final String CONTEXT_PATH = "/iti_ecommerce_app";
    private static final String APPLICATION_PATH = "/";
    public static HandlerMapping createHandlerMapping() {
        HandlerMapping handlerMapping = new HandlerMapping();
        handlerMapping.addMapping(CONTEXT_PATH + APPLICATION_PATH, ((request, response) -> new ModelAndType(Contents.EX.getViewName())));
        return handlerMapping;
    }
}
