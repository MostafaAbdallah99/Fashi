package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/login.jsp"})
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((jakarta.servlet.http.HttpServletRequest) request).getSession(false);
        if (session != null && session.getAttribute("customer") != null) {
            ((jakarta.servlet.http.HttpServletResponse) response).sendRedirect(((jakarta.servlet.http.HttpServletRequest) request).getContextPath() + "/home.html");
        } else {
            chain.doFilter(request, response);
        }
    }
}
