package controllers;

import com.google.gson.Gson;
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

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminProductServlet", urlPatterns = {"/admin/product", "/admin/product/*"})
@MultipartConfig
public class AdminProductServlet extends HttpServlet {
    private static Map<String, Integer> categoryMap;
    private static Map<String, Integer> tagMap;

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        categoryMap = (Map<String, Integer>) getServletContext().getAttribute("categoryMap");
        tagMap = (Map<String, Integer>) getServletContext().getAttribute("tagMap");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService productService = new ProductService();
        Part filePart = request.getPart("fileInput");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        String productName = request.getParameter("productName");
        Integer stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String productDescription = request.getParameter("productDescription");
        BigDecimal productPrice = new BigDecimal(request.getParameter("productPrice"));
        ProductSize productSize = ProductSize.valueOf(request.getParameter("productSize"));
        String category = request.getParameter("category");
        String tag = request.getParameter("tag");
        String imagePath;

        if(fileContent.available() != 0) {
            byte[] fileBytes = ByteStreamConverter.getInstance().convertToByteArray(fileContent);
            imagePath = FireStorageManager.getInstance().uploadFileToStorage(fileBytes, fileName);
        }
        else {
            ProductDTO product = productService.getProductById(Long.parseLong(request.getParameter("productID")));
            imagePath = product.productImage();
        }

        CategoryDTO categoryDTO = new CategoryDTO(categoryMap.get(category), category);
        TagDTO tagDTO = new TagDTO(tagMap.get(tag), tag);

        if(request.getParameter("action") != null) {
            ProductDTO productDTO = new ProductDTO(Integer.parseInt(request.getParameter("productID")), productName, imagePath, stockQuantity, productDescription, productPrice, productSize, categoryDTO, tagDTO, categoryDTO.categoryName());
            if(productService.updateProduct(productDTO)) {
                response.sendRedirect(getServletContext().getContextPath() + "/admin");
            }
        }
        else {
            ProductDTO productDTO = new ProductDTO(null, productName, imagePath, stockQuantity, productDescription, productPrice, productSize, categoryDTO, tagDTO, categoryDTO.categoryName());
            if(productService.addProduct(productDTO)) {
                response.sendRedirect(getServletContext().getContextPath() + "/admin");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductService productService = new ProductService();
        String productID = request.getParameter("productID");
        String action = request.getParameter("action");
        if(action != null && productID != null) {
            ProductDTO product = productService.getProductById(Long.parseLong(productID));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(product));
        }
        else if (productID != null) {
            ProductDTO productDTO = productService.getProductById(Long.parseLong(productID));
            if(productService.deleteProduct(Long.parseLong(productID))) {
                FireStorageManager.getInstance().deleteFileFromStorage(productDTO.productImage());
                response.sendRedirect(getServletContext().getContextPath() + "/admin");
            }
        } else {
            List<ProductDTO> products = productService.getAllProducts();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(products));
        }
    }
}
