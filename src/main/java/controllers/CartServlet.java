package controllers;

import dispatcher.resolver.types.JsonResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.CartItemDTO;
import services.CartService;

import java.io.IOException;


@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CartServlet doPost");
        String p = req.getParameter("productId").split("prdct-")[1];
        int productId = Integer.parseInt(p);
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        boolean enoughProducts = new CartService().checkProductQuantity(productId, quantity);
        if (enoughProducts) {
            System.out.println("Enough products");
            CartItemDTO cartItemDTO = new CartService().createCartItemForGuest(productId, quantity);
            new JsonResolver().render(cartItemDTO, req, resp);
        } else {
            // Send an HTTP 400 error response
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product is out of stock");
        }


    }


}
