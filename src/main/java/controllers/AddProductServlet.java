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
import services.impl.ProductService;
import utils.ByteStreamConverter;
import utils.FireStorageManager;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/admin/addProduct"})
@MultipartConfig
public class AddProductServlet extends HttpServlet {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher(Views.ADD_PRODUCT.getViewName());
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService productService = new ProductService();

        String category = request.getParameter("category");
        CategoryDTO categoryDTO = new CategoryDTO(categoryMap.get(category), category);

        String tag = request.getParameter("tag");
        TagDTO tagDTO = new TagDTO(tagMap.get(tag), tag);

        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        BigDecimal productPrice = new BigDecimal(request.getParameter("productPrice"));
        ProductSize productSize = ProductSize.valueOf(request.getParameter("productSize"));
        Integer productQuantity = Integer.parseInt(request.getParameter("stockQuantity"));

        Part filePart = request.getPart("fileInput");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();

        String imagePath;
        byte[] fileBytes = ByteStreamConverter.getInstance().convertToByteArray(fileContent);
        imagePath = FireStorageManager.getInstance().uploadFileToStorage(fileBytes, fileName);

        ProductDTO productDTO = new ProductDTO(null, productName, imagePath, productQuantity, productDescription, productPrice, productSize, categoryDTO, tagDTO, categoryDTO.categoryName(), false);

        if(productService.addProduct(productDTO)) {
            response.sendRedirect(request.getContextPath() + URLMapping.ADMIN_PRODUCT.getUrl());
        } else {
            response.sendRedirect(request.getContextPath() + Views.ERROR_PAGE.getViewName());
        }
    }
}
