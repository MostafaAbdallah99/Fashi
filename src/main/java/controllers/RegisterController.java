package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import persistence.dto.CartDTO;
import persistence.dto.CustomerDTO;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("customer") != null) {
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String birthdayStr = request.getParameter("birthday");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Adjust this pattern to match the format of the input date string
        Date birthday = null;
//        Date birthday = Date.parse(request.getParameter("birthday"));
        try {
            birthday = formatter.parse(birthdayStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String job = request.getParameter("job");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String streetNo = request.getParameter("streetNo");
        String streetName = request.getParameter("streetName");
        String interests = request.getParameter("interests");

        if (password.equals(confirmPassword)) {
            BigDecimal creditLimit = BigDecimal.ZERO;

            CartDTO cart = null;


            CustomerDTO customerDTO = new CustomerDTO(null, customerName, birthday, password, job, email, creditLimit, city, country, streetNo, streetName, interests, cart);
            CustomerDTO createdCustomer = customerService.signUp(customerDTO);
            if (createdCustomer != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("customer", createdCustomer);
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            } else {
                request.setAttribute("registerFailed", true);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("registerFailed", true);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    }