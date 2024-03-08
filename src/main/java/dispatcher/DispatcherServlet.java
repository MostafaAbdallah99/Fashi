package dispatcher;

import dispatcher.handler.HandlerMapping;
import dispatcher.handler.HandlerMappingConfig;
import jakarta.servlet.http.*;

public class DispatcherServlet extends HttpServlet {
    private final HandlerMapping handlerMapping;

    public DispatcherServlet() {
        handlerMapping = HandlerMappingConfig.createHandlerMapping();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        Controller controller = handlerMapping.getController(requestURI);
        if(controller != null) {
            try {
                ModelAndView modelAndView = controller.handleRequest(request, response);
                if (modelAndView != null) {
                    ViewResolver.renderView(modelAndView, request, response);
                }
            } catch (Exception e) {


            }
        }
        else {



        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}