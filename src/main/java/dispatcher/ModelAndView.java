package dispatcher;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ModelAndView {

    @Setter
    private String viewType;
    private final Map<String, Object> model;

    public ModelAndView(String viewType) {
        this.viewType = viewType;
        this.model = new HashMap<>();
    }

    public void addAttribute(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
    }

    public Object getAttribute(String attributeName) {
        return model.get(attributeName);
    }
}