package controllers;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin", "/admin/*"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getParameter("action") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/products.html");
            dispatcher.forward(request, response);
        }
        else if(request.getParameter("action").equals("add")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add-product.html");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-product.html");
            dispatcher.forward(request, response);
        }
    }
}
