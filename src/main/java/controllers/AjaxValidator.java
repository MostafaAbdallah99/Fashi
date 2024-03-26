package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;

@WebServlet("/validator")
public class AjaxValidator extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");

        if(email != null) {
            validateEmail(email, resp);
        } else if(username != null) {
            validateUsername(username, resp);
        }
    }

    private void validateEmail(String email, HttpServletResponse resp) throws IOException {
        CustomerService customerService = new CustomerServiceImpl();
        if(customerService.isEmailExists(email)) {
            resp.getWriter().write("unavailable");
            resp.getWriter().flush();
        }
    }

    private void validateUsername(String username, HttpServletResponse resp) throws IOException {
        CustomerService customerService = new CustomerServiceImpl();
        if(customerService.isUsernameExists(username)) {
            resp.getWriter().write("unavailable");
            resp.getWriter().flush();
        }
    }
}

