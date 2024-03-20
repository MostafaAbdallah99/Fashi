package controllers;

import dispatcher.ModelAndType;
import dispatcher.interfaces.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mappers.ProductMapper;
import persistence.dto.ProductDTO;
import persistence.entities.Product;
import persistence.repository.interfaces.ProductRepository;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.utils.TransactionUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopController implements Controller {
    private final ProductRepositoryImpl productRepository = new ProductRepositoryImpl();

    @Override
    public ModelAndType handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(product);
            productDTOS.add(productDTO);
        }

        // Check if the json parameter is present in the request
        String jsonParam = request.getParameter("json");

        // Create a new ModelAndType object
        ModelAndType modelAndType;

        // If the json parameter is present and its value is "true", set the viewType to "json"
        // Otherwise, set the viewType to "productList"
        if (jsonParam != null && jsonParam.equals("true")) {
            modelAndType = new ModelAndType("json");
            System.out.println("jsonParam = " + jsonParam);
        } else {
            modelAndType = new ModelAndType("shop");
        }

        // Add the list of products to the model

        modelAndType.addAttribute("products", productDTOS);

        // Return the ModelAndType object
        return modelAndType;*/
        return null;
    }
}
