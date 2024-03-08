package dispatcher.handler;

import dispatcher.Controller;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
    private final Map<String, Controller> handlerMappings;
    public HandlerMapping() {
        handlerMappings = new HashMap<>();
    }
    void addMapping(String uri, Controller controller) {
        handlerMappings.put(uri, controller);
    }
    public Controller getController(String requestURI) {
        return handlerMappings.get(requestURI);
    }
}