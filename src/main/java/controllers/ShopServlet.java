package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.ProductDTO;
import services.impl.ProductService;
import utils.JsonResolver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ShopServlet: doPost");

        if (req.getParameter("category") == null && req.getParameter("priceMin") == null && req.getParameter("priceMax") == null) {
            int page = Integer.parseInt(req.getParameter("page"));
            int size = Integer.parseInt(req.getParameter("size"));
            List<ProductDTO> products = new ProductService().getProducts(page, size);
            Map<String, Object> totalPages = new ProductService().getTotalPages(size);
            Map<String, Object> map = Map.of("products", products, "totalPagesCount", totalPages);
            JsonResolver.render(map, resp);
            return;
        }

        String category = req.getParameter("category");
        String priceMin = req.getParameter("priceMin");
        String priceMax = req.getParameter("priceMax");
        priceMax = priceMax.replace("$", "");
        priceMin = priceMin.replace("$", "");
        String tag = req.getParameter("tag");
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));
        String searchQuery = req.getParameter("searchQuery");
        Map<String, Object> products  = new ProductService().getProductsByCategoryAndTagAndPriceRange(category, tag, Double.parseDouble(priceMin), Double.parseDouble(priceMax), page, size, searchQuery);
        System.out.println("got the products");
        System.out.println(products);
        JsonResolver.render(products, resp);

    }

}


