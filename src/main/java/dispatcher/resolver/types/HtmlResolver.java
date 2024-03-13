package dispatcher.resolver.types;

import dispatcher.ModelAndType;
import dispatcher.interfaces.Resolver;
import dispatcher.resolver.Contents;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HtmlResolver implements Resolver {
    private static final String VIEW_PREFIX = "/";
    private static final String VIEW_SUFFIX = ".html";

    @Override
    public void render(ModelAndType modelAndType, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String htmlPath = VIEW_PREFIX + modelAndType.getViewType() + VIEW_SUFFIX;
        RequestDispatcher dispatcher = request.getRequestDispatcher(htmlPath);
        System.out.println("htmlPath = " + htmlPath);

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            dispatcher = request.getRequestDispatcher(VIEW_PREFIX + Contents.ERROR + VIEW_SUFFIX);
            dispatcher.forward(request, response);
        }
    }
}