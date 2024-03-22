package controllers;


import controllers.mapping.URLMapping;
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
        String action = request.getParameter("action");
        ProductService productService = new ProductService();
        if(action != null && action.equalsIgnoreCase("deleteProduct")) {
            try {
                Long productId = Long.parseLong(request.getParameter("productID"));
                if(productService.deleteProduct(productId)) {
                    response.sendRedirect(request.getContextPath() + URLMapping.ADMIN_PRODUCT.getUrl());
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + Views.ERROR_PAGE.getViewName());
            }
        }
        else {
            List<ProductDTO> products = productService.getAllProducts();
            JsonResolver.render(products, response);
        }
    }
}
