package dispatcher.resolver.types;

import com.google.gson.Gson;
import dispatcher.ModelAndType;
import dispatcher.interfaces.Resolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class JsonResolver implements Resolver {
    private final Gson gson = new Gson();

    @Override
    public void render(ModelAndType modelAndType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = gson.toJson(modelAndType.getModel());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    public void render(Object obj, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = gson.toJson(obj);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }


}