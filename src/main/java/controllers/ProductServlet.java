package controllers;

import dispatcher.resolver.types.JsonResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.ProductDTO;
import services.impl.ProductService;

import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/product")

public class ProductServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProductServlet: doGet");
        req.getRequestDispatcher("/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("productId"));
        String p = req.getParameter("productId");
        p=p.split("prdct-")[1];
        System.out.println("this is the data after the split "+p);
        long productId = Long.parseLong(p);
        ProductDTO product = new ProductService().getProductById(productId);
        new JsonResolver().render(product, req, resp);

    }
}
