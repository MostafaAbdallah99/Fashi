package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.repository.utils.EmailHandler;
import services.*;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/reset-password-request")
public class ResetPasswordRequestServlet extends HttpServlet {
    private final CustomerService userService = new CustomerServiceImpl();
    private final EmailHandler emailHandler = new EmailHandler();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        String token = UUID.randomUUID().toString();

        userService.storeResetPasswordToken(email, token);

        System.out.println("Token: " + token);

        String resetPasswordLink = "http://localhost:8085/iti_ecommerce_app/reset-password?token=" + token;
        emailHandler.sendEmail(email, "Reset Password", "Click the following link to reset your password: " + resetPasswordLink);

        resp.sendRedirect("reset-password-request-sent.jsp");
    }
}