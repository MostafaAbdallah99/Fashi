package dispatcher.interfaces;

import dispatcher.ModelAndType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Resolver {
    void render(ModelAndType modelAndType, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
