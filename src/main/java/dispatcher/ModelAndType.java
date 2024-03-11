package dispatcher;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ModelAndType {

    @Setter
    private String viewType;
    private final Map<String, Object> model;

    public ModelAndType(String viewType) {
        this.viewType = viewType;
        this.model = new HashMap<>();
    }

    public void addAttribute(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
    }

}