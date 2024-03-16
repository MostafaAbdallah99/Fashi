package controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import persistence.dto.CustomerDTO;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("LoginController doGet");
        HttpSession session = request.getSession(false);
        if (session != null) {

            response.sendRedirect(request.getContextPath() + "/home.html");
        } else {
            response.sendRedirect("login.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        CustomerDTO customerDTO = customerService.login(email, password);
        if (customerDTO != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("customer", customerDTO);
            response.sendRedirect("home.html");
        } else {
            response.sendRedirect("/login.jsp?message=wrong");
        }
    }
}