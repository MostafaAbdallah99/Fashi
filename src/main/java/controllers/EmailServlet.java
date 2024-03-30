package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utils.EmailHandler;

import java.io.IOException;

@WebServlet("/email")
public class EmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");


        EmailHandler emailHandler = new EmailHandler();

        
        emailHandler.sendEmailContact(email, "Contact Form Submission from " + name, message);

        response.sendRedirect("contact.jsp"); // redirect back to the contact page
    }
}