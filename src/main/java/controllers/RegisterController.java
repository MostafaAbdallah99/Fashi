package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import persistence.dto.CartItemDTO;
import persistence.dto.CustomerDTO;
import services.impl.CartService;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        System.out.println("RegisterController doPost");
        String customerName = request.getParameter("customerName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String birthdayStr = request.getParameter("birthday");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        System.out.println(birthdayStr+" "+email+" "+customerName);
        try {
            birthday = formatter.parse(birthdayStr);
        } catch (ParseException e) {
            System.out.println("Error parsing date");
        }
        String job = request.getParameter("job");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String streetNo = request.getParameter("streetNo");
        String streetName = request.getParameter("streetName");
        String interests = request.getParameter("interests");
        String cartItemsJson = request.getParameter("cartItems");
        BigDecimal creditLimit = new BigDecimal(request.getParameter("cardLimit"));
        List<CartItemDTO> cartItems = new CartService().getCartItems(cartItemsJson);

        if (password.equals(confirmPassword)) {
            CustomerDTO customerDTO = new CustomerDTO(null, customerName, birthday, password, job, email, creditLimit, city, country, streetNo, streetName, interests, null, false);

            System.out.println("customerDTO: " + customerDTO);
            CustomerDTO createdCustomer = customerService.signUp(customerDTO, cartItems);
            System.out.println("createdCustomer: " + createdCustomer);
            if (createdCustomer != null) {
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("customer", createdCustomer);
                Cookie loginCookie = new Cookie("user_login", "true");
                response.addCookie(loginCookie);
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            } else {
                request.setAttribute("registerFailed", true);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }
}