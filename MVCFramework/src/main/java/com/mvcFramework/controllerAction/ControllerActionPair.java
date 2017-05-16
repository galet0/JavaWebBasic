package com.mvcFramework.controllerAction;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ControllerActionPair {

    private Class controller;

    private Method actionMethod;

    private Map<String, String> pathVariable;

    public ControllerActionPair(Class controller, Method actionMethod) {
        this.controller = controller;
        this.actionMethod = actionMethod;
        this.pathVariable = new HashMap<>();
    }

    public Class getController() {
        return controller;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void addPathVariable(String key, String value){
        this.pathVariable.put(key, value);
    }

    public String getPathVariable(String key){
        return this.pathVariable.get(key);
    }
}
