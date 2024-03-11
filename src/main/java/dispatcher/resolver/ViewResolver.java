package dispatcher.resolver;

import dispatcher.ModelAndType;
import dispatcher.interfaces.Resolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;

@Setter
public class ViewResolver {
    private Resolver resolver;

    public void render(ModelAndType modelAndType, HttpServletRequest request, HttpServletResponse response) throws Exception {
        resolver.render(modelAndType, request, response);
    }
}