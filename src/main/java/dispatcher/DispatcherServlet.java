package dispatcher;

import dispatcher.handler.HandlerMapping;
import dispatcher.handler.HandlerMappingConfig;
import dispatcher.interfaces.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private final HandlerMapping handlerMapping;

    public DispatcherServlet() {
        handlerMapping = HandlerMappingConfig.createHandlerMapping();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        Controller controller = handlerMapping.getController(requestURI);
        if(controller != null) {
            try {
                ModelAndView modelAndView = controller.handleRequest(request, response);
                if (modelAndView != null) {
                    ViewResolver.renderJSON(modelAndView, request, response);
                }
            } catch (Exception e) {


            }
        }
        else {
            if(requestURI.contains("html")) {
                RequestDispatcher dispatcher = request.getServletContext().getNamedDispatcher("default");
                dispatcher.forward(request, response);
            }
            else if(requestURI.equals("/iti_ecommerce_app/")) {
                try {
                    ViewResolver.renderView("main", request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}