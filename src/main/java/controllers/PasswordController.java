package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import persistence.dto.CustomerDTO;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;

@WebServlet("/password-change")
public class PasswordController extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");


        HttpSession session = req.getSession();
        CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");

        boolean isChanged = customerService.changePassword(customer.email(), oldPassword, newPassword);

        if (isChanged) {
            resp.sendRedirect("update-success.html");
        } else {
            req.setAttribute("error", "Failed to change password");
            req.getRequestDispatcher("edit-profile.jsp").forward(req, resp);
        }
    }
}