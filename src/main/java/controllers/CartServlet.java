package controllers;

import dispatcher.resolver.types.JsonResolver;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mappers.CustomerMapper;
import persistence.dto.CartItemDTO;
import persistence.dto.CustomerDTO;
import persistence.entities.Customer;
import services.impl.CartService;

import java.io.IOException;


@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("CartServlet doPost");
        String p = req.getParameter("productId").split("prdct-")[1];
        long productId = Long.parseLong(p);
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        if (req.getSession().getAttribute("customer") != null) {
            Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer((CustomerDTO) req.getSession().getAttribute("customer"));
            CartItemDTO cartItemDTO = new CartService().createCartItemForCustomer(customer, (int) productId, quantity);
            if (cartItemDTO != null)
                new JsonResolver().render(cartItemDTO, req, resp);
            else
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        } else {
            // If the user is not logged in
            CartItemDTO cartItemDTO = new CartService().createCartItemForGuest((int) productId, quantity);
            if (cartItemDTO != null)
                new JsonResolver().render(cartItemDTO, req, resp);
            else
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}



