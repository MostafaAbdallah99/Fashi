package controllers;

import dispatcher.resolver.types.JsonResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.ProductDTO;
import services.ProductService;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ShopServlet: doGet");
        req.getRequestDispatcher("/shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ShopServlet: doPost");

        if(req.getParameter("category")==null && req.getParameter("priceMin")==null && req.getParameter("priceMax")==null){
            List<ProductDTO> products = new ProductService().getAllProducts();
            new JsonResolver().render(products, req, resp);
            return;
        }

            String category = req.getParameter("category");
            String priceMin = req.getParameter("priceMin");
            String priceMax = req.getParameter("priceMax");
            priceMax = priceMax.replace("$","");
            priceMin = priceMin.replace("$","");
            System.out.println("priceMin: "+priceMin);
            System.out.println("priceMax: "+priceMax);
            String tag = req.getParameter("tag");
            if(category.isEmpty())
                category= null;
            if(tag.isEmpty())
                tag = null;
            List<ProductDTO> products = new ProductService().getProductsByCategoryAndTagAndPriceRange(category,tag, Double.parseDouble(priceMin), Double.parseDouble(priceMax));
            System.out.println("got the products");
            System.out.println(products);
            new JsonResolver().render(products, req, resp);

    }

}


