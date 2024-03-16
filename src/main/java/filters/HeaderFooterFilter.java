package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HeaderFooterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;


        String path = req.getRequestURI();
        if (path.startsWith("/iti_ecommerce_app/img/")||path.startsWith("/iti_ecommerce_app/css/")||path.startsWith("/iti_ecommerce_app/js/")) {
            // Skip the filter and proceed to the next element in the filter chain
            chain.doFilter(request, response);
        } else {
            PrintWriter out = response.getWriter();
            // Include the header
            RequestDispatcher rq = request.getRequestDispatcher("/header.jsp");
            rq.include(request, response);
//        out.println("<!DOCTYPE html>");
//        out.println("<html>");
//        out.println("<head>");
//        out.println("<title>Header</title>");
//        out.println("</head>");
//        out.println("<body>");
//        out.println("<header>");
//        out.println("<h1>Header</h1>");
//        out.println("</header>");
//        out.println("</body>");
//        out.println("</html>");
            System.out.println("HeaderFilter: done");

            // Pass the request along the filter chain
            chain.doFilter(request, response);

            // Include the footer after processing
            RequestDispatcher rs = request.getRequestDispatcher("/footer.jsp");
            rs.include(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code here
    }
}