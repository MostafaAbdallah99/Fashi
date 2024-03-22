package controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import persistence.dto.CustomerDTO;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("customer") != null) {
            response.sendRedirect(request.getContextPath() + "/home.jsp");
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
            // Create a new cookie
            Cookie loginCookie = new Cookie("user_login", "true");
            loginCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(loginCookie);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?message=wrong");
        }
    }
}