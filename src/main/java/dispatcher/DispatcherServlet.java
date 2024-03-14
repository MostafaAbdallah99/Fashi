package dispatcher;

import dispatcher.handler.HandlerMapping;
import dispatcher.handler.HandlerMappingConfig;
import dispatcher.interfaces.Controller;
import dispatcher.resolver.Contents;
import dispatcher.resolver.ViewResolver;
import dispatcher.resolver.types.HtmlResolver;
import dispatcher.resolver.types.JsonResolver;
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
        System.out.println("requestURI = " + requestURI);
        Controller controller = handlerMapping.getController(requestURI);
        ViewResolver viewResolver = new ViewResolver();
        if (controller != null) {
            try {
                ModelAndType modelAndType = controller.handleRequest(request, response);
                if (modelAndType != null) {
                    if (modelAndType.getViewType().equals(Contents.JSON.getViewName())) {
                        viewResolver.setResolver(new JsonResolver());
                    } else {
                        viewResolver.setResolver(new HtmlResolver());
                    }
                }
                viewResolver.render(modelAndType, request, response);
            } catch (Exception e) {


            }
        } else {
            if (requestURI.contains("html")) {
                RequestDispatcher dispatcher = request.getServletContext().getNamedDispatcher("default");
                dispatcher.forward(request, response);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}