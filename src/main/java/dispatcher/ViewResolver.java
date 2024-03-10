package dispatcher;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public class ViewResolver {
    private static final String VIEW_PREFIX = "/";
    private static final String VIEW_SUFFIX = ".html";

    public static void renderJSON(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(modelAndView.getModel());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    public static void renderView(String viewName, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String htmlPath = VIEW_PREFIX + viewName + VIEW_SUFFIX;
        RequestDispatcher dispatcher = request.getRequestDispatcher(htmlPath);

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            dispatcher = request.getRequestDispatcher(VIEW_PREFIX + "error" + VIEW_SUFFIX);
            dispatcher.forward(request, response);
        }
    }
}