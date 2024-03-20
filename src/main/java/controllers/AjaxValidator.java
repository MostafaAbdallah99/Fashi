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
    private final CustomerService customerService = new CustomerServiceImpl();

}

