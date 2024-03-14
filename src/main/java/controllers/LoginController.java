package controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.CustomerDTO;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;

public class LoginController extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        CustomerDTO customerDTO = customerService.login(email, password);
        if (customerDTO != null) {
            request.getSession().setAttribute("customer", customerDTO);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?message=wrong");
        }
    }
}