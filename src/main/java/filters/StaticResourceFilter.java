package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StaticResourceFilter implements Filter {
    private Set<String> STATIC_RESOURCE_EXTENSIONS;
    @Override
    public void init(FilterConfig filterConfig) {
        STATIC_RESOURCE_EXTENSIONS = new HashSet<>();
        STATIC_RESOURCE_EXTENSIONS.add(".html");
        STATIC_RESOURCE_EXTENSIONS.add(".css");
        STATIC_RESOURCE_EXTENSIONS.add(".js");
        STATIC_RESOURCE_EXTENSIONS.add(".png");
        STATIC_RESOURCE_EXTENSIONS.add(".jpg");
        STATIC_RESOURCE_EXTENSIONS.add(".jpeg");
        STATIC_RESOURCE_EXTENSIONS.add(".gif");
        STATIC_RESOURCE_EXTENSIONS.add(".svg");
        STATIC_RESOURCE_EXTENSIONS.add(".ico");
        STATIC_RESOURCE_EXTENSIONS.add(".woff");
        STATIC_RESOURCE_EXTENSIONS.add(".woff2");
        STATIC_RESOURCE_EXTENSIONS.add(".ttf");
        STATIC_RESOURCE_EXTENSIONS.add(".eot");
        STATIC_RESOURCE_EXTENSIONS.add(".otf");
        STATIC_RESOURCE_EXTENSIONS.add(".map");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        int lastDotIndex = requestURI.lastIndexOf('.');
        if (lastDotIndex != -1) {
            String extension = requestURI.substring(lastDotIndex);
            if (STATIC_RESOURCE_EXTENSIONS.contains(extension)) {
                RequestDispatcher dispatcher = request.getServletContext().getNamedDispatcher("default");
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}