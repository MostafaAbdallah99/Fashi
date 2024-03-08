package dispatcher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;

public class ViewResolver {
    private static final String VIEW_PREFIX = "/";
    private static final String VIEW_SUFFIX = ".jsp";

    public static void renderView(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = modelAndView.getViewName();
        String htmlPath = VIEW_PREFIX + viewName + VIEW_SUFFIX;
        RequestDispatcher dispatcher = request.getRequestDispatcher(htmlPath);

        if (dispatcher != null) {
            request.setAttribute("model", modelAndView.getModel());
            dispatcher.forward(request, response);
        } else {
            dispatcher = request.getRequestDispatcher(VIEW_PREFIX + "error" + VIEW_SUFFIX);
            dispatcher.forward(request, response);
        }
    }
}