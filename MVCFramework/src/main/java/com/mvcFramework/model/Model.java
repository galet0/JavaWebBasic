package com.mvcFramework.model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class Model {

    private HttpServletRequest request;

    private Map<String, Object> attributes;

    public Model(HttpServletRequest request) {
        //Initialize fields
        this.request = request;
        this.attributes = new HashMap<>();
    }

    public void addAttribute(String key, Object value) {
        //Add the parameters and then pass them to the view
        this.attributes.put(key, value);
        sendParametersToView();
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    private void sendParametersToView(){
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            this.request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}

