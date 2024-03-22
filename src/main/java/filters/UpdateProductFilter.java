package filters;

import controllers.mapping.URLMapping;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "UpdateProductFilter", urlPatterns = {"/admin/updateProduct"})
public class UpdateProductFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String productId = servletRequest.getParameter("productID");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (productId == null) {
            response.sendRedirect(request.getContextPath() + URLMapping.ADMIN_PRODUCT.getUrl());
        } else{
            try {
                Long.parseLong(productId);
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + URLMapping.ADMIN_PRODUCT.getUrl());
            }
        }
    }
}
