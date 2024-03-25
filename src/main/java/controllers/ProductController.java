package controllers;


import controllers.mapping.Views;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.ProductDTO;
import services.ProductService;
import utils.JsonResolver;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "ProductController", urlPatterns = {"/admin/product"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(Views.VIEW_PRODUCT.getViewName());
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService productService = new ProductService();
        List<ProductDTO> products = productService.getAllProducts();
        JsonResolver.render(products, response);
    }
}
