package controllers;

import dispatcher.resolver.types.JsonResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.CartItemDTO;
import persistence.dto.CustomerDTO;
import services.impl.CartService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("customer") == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product is out of stock");
        } else {
            CustomerDTO customerDTO=(CustomerDTO) req.getSession().getAttribute("customer");
            System.out.println("customerDTO: "+customerDTO);
            new JsonResolver().render(customerDTO, req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cartId = Integer.parseInt(req.getParameter("cartId"));
        List<CartItemDTO> cartItems = new CartService().getCartItems(cartId);
        new JsonResolver().render(cartItems, req, resp);
    }
}
