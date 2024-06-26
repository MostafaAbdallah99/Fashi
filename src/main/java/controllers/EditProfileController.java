package controllers;

import controllers.mapping.Views;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/edit-profile")
public class EditProfileController extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello from EditProfileController doGet");
        HttpSession session = req.getSession();
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("customer");

        if (customerDTO != null) {
            CustomerDTO customerDTOFromDB = customerService.getCustomerById(customerDTO.id());
            session.setAttribute("customer", customerDTOFromDB);
            System.out.println("User: " + customerDTOFromDB.customerName());
            req.getRequestDispatcher("edit-profile.jsp").forward(req, resp);

        }
        else {
            req.getRequestDispatcher(Views.LOGIN.getViewName()).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        String email = req.getParameter("email");
        String job = req.getParameter("job");
        String birthdayParam = req.getParameter("birthday");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        if (birthdayParam != null && !birthdayParam.isEmpty()) {
            try {
                birthday = formatter.parse(birthdayParam);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String streetNo = req.getParameter("streetNo");
        String streetName = req.getParameter("streetName");
        String creditLimitParam = req.getParameter("creditLimit");
        BigDecimal creditLimit = null;
        if (creditLimitParam != null && !creditLimitParam.isEmpty()) {
            creditLimit = new BigDecimal(creditLimitParam);
        }

        HttpSession session = req.getSession();
        CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");

        // Create a new CustomerDTO object with the updated values
        CustomerDTO updatedCustomer = new CustomerDTO(
                customer.id(),
                customerName,
                birthday,
                customer.password(),
                job,
                email,
                creditLimit, // updated creditLimit
                city,
                country,
                streetNo,
                streetName,
                customer.interests(),
                customer.cart(),
                false
        );

        if(customerService.isEmailExists(email) && !email.equals(customer.email())) {
            req.setAttribute("emailExists", "Email already exists. Please use another email.");
            req.getRequestDispatcher("edit-profile.jsp").forward(req, resp);
        }
        else if(customerService.isUsernameExists(customerName) && !customerName.equals(customer.customerName())) {
            req.setAttribute("usernameExists", "Username already exists. Please use another username.");
            req.getRequestDispatcher("edit-profile.jsp").forward(req, resp);
        } else {
            boolean isUpdated = customerService.updateCustomer(updatedCustomer);

            if (isUpdated) {
                session.setAttribute("customer", updatedCustomer);
                resp.sendRedirect("update-success.html");
            } else {
                resp.sendRedirect("error.jsp");
            }
        }
    }
}