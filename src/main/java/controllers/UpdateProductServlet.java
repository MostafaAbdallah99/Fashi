package controllers;

import controllers.mapping.URLMapping;
import controllers.mapping.Views;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import persistence.dto.CategoryDTO;
import persistence.dto.ProductDTO;
import persistence.dto.TagDTO;
import persistence.entities.ProductSize;
import services.ProductService;
import utils.ByteStreamConverter;
import utils.FireStorageManager;
import utils.JsonResolver;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;

@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/admin/updateProduct"})
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
    private static Map<String, Integer> categoryMap;
    private static Map<String, Integer> tagMap;

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        categoryMap = (Map<String, Integer>) getServletContext().getAttribute("categoryMap");
        tagMap = (Map<String, Integer>) getServletContext().getAttribute("tagMap");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(Views.EDIT_PRODUCT.getViewName());
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService productService = new ProductService();
        String action = request.getParameter("action");
        ProductDTO productDTO = productService.getProductById(Long.parseLong(request.getParameter("productID")));

        if(productDTO == null) {
            response.sendRedirect(request.getContextPath() + URLMapping.ADMIN_PRODUCT.getUrl());
        }
        else if(action != null && action.equalsIgnoreCase("fetchProduct")) {
            JsonResolver.render(productDTO, response);
        }
        else {
            String category = request.getParameter("category");
            CategoryDTO categoryDTO = new CategoryDTO(categoryMap.get(category), category);

            String tag = request.getParameter("tag");
            TagDTO tagDTO = new TagDTO(tagMap.get(tag), tag);

            Integer productId = Integer.parseInt(request.getParameter("productID"));
            String productName = request.getParameter("productName");
            String productDescription = request.getParameter("productDescription");
            BigDecimal productPrice = new BigDecimal(request.getParameter("productPrice"));
            ProductSize productSize = ProductSize.valueOf(request.getParameter("productSize"));
            Integer productQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            String isDeleted = request.getParameter("isDeleted");

            Part filePart = request.getPart("fileInput");
            String fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();
            String imagePath;

            if (fileContent.available() != 0) {
                byte[] fileBytes = ByteStreamConverter.getInstance().convertToByteArray(fileContent);
                imagePath = FireStorageManager.getInstance().uploadFileToStorage(fileBytes, fileName);
            } else {
                imagePath = productDTO.productImage();
            }

            ProductDTO updatedProductDTO = new ProductDTO(productId, productName, imagePath, productQuantity, productDescription, productPrice, productSize, categoryDTO, tagDTO, categoryDTO.categoryName(), isDeleted.equalsIgnoreCase("true"));

            if (productService.updateProduct(updatedProductDTO)) {
                response.sendRedirect(request.getContextPath() + URLMapping.ADMIN_PRODUCT.getUrl());
            } else {
                response.sendRedirect(request.getContextPath() + Views.ERROR_PAGE.getViewName());
            }
        }
    }
}
