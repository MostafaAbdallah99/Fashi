package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import persistence.dto.CartDTO;
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
        String customerName = request.getParameter("customerName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String birthdayStr = request.getParameter("birthday");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Adjust this pattern to match the format of the input date string
        Date birthday = null;
//        Date birthday = Date.parse(request.getParameter("birthday"));
        System.out.println(birthdayStr+" "+email+" "+customerName);
        try {
            birthday = formatter.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String job = request.getParameter("job");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String streetNo = request.getParameter("streetNo");
        String streetName = request.getParameter("streetName");
        String interests = request.getParameter("interests");
        String cartItemsJson = request.getParameter("cartItems");
        List<CartItemDTO> cartItems = new CartService().getCartItems(cartItemsJson);

        if (password.equals(confirmPassword)) {
            if (customerService.isEmailExists(email)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"emailError\":\"Email already exists\"}");
            } else if (customerService.isUsernameExists(customerName)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"customerError\":\"Name already exists\"}");
            }
            else {

                BigDecimal creditLimit = BigDecimal.ZERO;

                CartDTO cart = null;

                CustomerDTO customerDTO = new CustomerDTO(null, customerName, birthday, password, job, email, creditLimit, city, country, streetNo, streetName, interests, cart, false);



                CustomerDTO createdCustomer = customerService.signUp(customerDTO, cartItems);
                if (createdCustomer != null) {
                    HttpSession oldSession = request.getSession(false);
                    if (oldSession != null) {
                        oldSession.invalidate();
                    }
                    HttpSession newSession = request.getSession(true);
                    newSession.setAttribute("customer", createdCustomer);
                    Cookie loginCookie = new Cookie("user_login", "true");
                    loginCookie.setMaxAge(60 * 60 * 24 * 365);
                    response.addCookie(loginCookie);
                    response.sendRedirect(request.getContextPath() + "/home.jsp");
                } else {
                    request.setAttribute("registerFailed", true);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }

        }
    }
}