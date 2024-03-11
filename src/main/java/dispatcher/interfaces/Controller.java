package dispatcher.interfaces;

import dispatcher.ModelAndType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Controller {
    ModelAndType handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
