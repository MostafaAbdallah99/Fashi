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
    CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (email != null) {
            boolean isAvailable = customerService.isEmailExists(email);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{ \"available\": " + isAvailable + " }");
            return;
        }
        String userName = req.getParameter("username");
        System.out.println(userName);
        if (userName != null) {
            boolean isAvailable = customerService.isUsernameExists(userName);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            System.out.println("the res is " + isAvailable);
            resp.getWriter().write("{ \"available\": " + isAvailable + " }");
        }
    }
}

