package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import persistence.dto.CustomerDTO;
import services.impl.CustomerServiceImpl;
import services.interfaces.CustomerService;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    CustomerService customerService = new CustomerServiceImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Admin filter");
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session != null) {
            CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
            if (customer != null) {
                boolean isAdmin = customerService.getCustomerById(customer.id()).isAdmin();
                if (isAdmin) {
                    chain.doFilter(request, response);
                } else {
                    System.out.println("Unauthorized access request");
                    System.out.println(customer.isAdmin());
                    ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");
                }
            } else {
                System.out.println("No customer in session");
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");
            }
        } else {
            System.out.println("No session exists");
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");
        }
    }
}