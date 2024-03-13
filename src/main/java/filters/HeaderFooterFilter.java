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
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();

        // Include the header
      RequestDispatcher rq =request.getRequestDispatcher("/header.html");
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
        RequestDispatcher rs =request.getRequestDispatcher("/footer.html");
        rs.include(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code here
    }
}