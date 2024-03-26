package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.CustomerDTO;
import persistence.entities.Customer;
import persistence.repository.interfaces.AdminRepo;
import persistence.repository.repositories.AdminRepoImpl;
import services.impl.AdminServiceImpl;
import services.impl.CustomerServiceImpl;
import utils.JsonResolver;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/order_history")
public class OrderHistoryController extends HttpServlet {

    private final AdminServiceImpl adminService;

    public OrderHistoryController() {
        this.adminService = new AdminServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> usersWithOrders = adminService.getUsersWithOrders();
        req.setAttribute("usersWithOrders", usersWithOrders);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user-orders.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        CustomerDTO customerDTO = customerService.getCustomerById(Integer.parseInt(userId));
        JsonResolver.render(customerDTO, resp);
    }
}
