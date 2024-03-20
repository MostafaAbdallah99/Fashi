package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.entities.Customer;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    private final CustomerService userService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");

        Customer user = userService.getUserByResetPasswordToken(token);

        if (user != null) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid password reset token");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String newPassword = req.getParameter("password");

        boolean isPasswordReset = userService.resetPassword(token, newPassword);

        if (isPasswordReset) {
            resp.sendRedirect("login.jsp");
        } else {
            req.setAttribute("error", "Failed to reset password");
            req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
        }
    }
}