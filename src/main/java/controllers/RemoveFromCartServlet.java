package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.CustomerDTO;
import services.CartService;

import java.io.IOException;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("customer") != null){
            CustomerDTO customer = (CustomerDTO) req.getSession().getAttribute("customer");
            int cartId = customer.id();
            int productId = Integer.parseInt(req.getParameter("productId"));
            if(new CartService().removeProductFromCart(cartId, productId))
                resp.setStatus(HttpServletResponse.SC_OK);
        }

        else{
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
