package com.mvcFramework.handlers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.controllerAction.ControllerActionPair;
import com.mvcFramework.interfaces.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HandlerMappingImpl implements HandlerMapping {

    @Override
    public ControllerActionPair findController(HttpServletRequest request) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String urlPath = request.getRequestURI();
        String projectPath = URLDecoder.decode(request.getServletContext().getResource("/WEB-INF/classes").getPath(), "UTF-8");
        String absolute = request.getServletContext().getRealPath(projectPath);
        List<Class> controllers = this.findAllControllers(absolute);
        ControllerActionPair controllerActionPair = null;

        for (Class controller : controllers) {
            Method[] methods = controller.getDeclaredMethods();
            for (Method method : methods) {
                String methodPath = this.findMethodPath(request, method);
                if(methodPath == null){
                    continue;
                }

                if(this.isPathMatching(urlPath, methodPath)){
                    controllerActionPair = new ControllerActionPair(controller, method);
                    this.addPathVariables(controllerActionPair, urlPath, methodPath);
                    return controllerActionPair;
                }
            }
        }
        return null;
    }

    private void addPathVariables(ControllerActionPair controllerActionPair, String urlPath, String methodPath) {
        String[] uriTokens = urlPath.split("/");
        String[] methodTokens = methodPath.split("/");
        //If there is path variable add it to the ControllerActionPair
        for (int i = 0; i < uriTokens.length; i++) {
            if(methodTokens[i].startsWith("{") && methodTokens[i].endsWith("}")){
                String key = methodTokens[i].replace("{","").replace("}","");
                String value = uriTokens[i];
                controllerActionPair.addPathVariable(key, value);
            }
        }
    }


    private boolean isPathMatching(String urlPath, String methodPath) {
        boolean isPathMatching = true;
        String[] uriTokens = urlPath.split("/");
        String[] methodTokens = methodPath.split("/");

        //If the lengths are different return false
        if(uriTokens.length != methodTokens.length){
            isPathMatching = false;
            return isPathMatching;
        }

        //If there is a path variable {some id} ignore and continue to check if the path is the same
        for (int i = 0; i < uriTokens.length; i++) {
            if(methodTokens[i].startsWith("{") && methodTokens[i].endsWith("}")){
                continue;
            }

            if(!uriTokens[i].equals(methodTokens[i])){
                isPathMatching = false;
                break;
            }
        }

        return isPathMatching;
    }


    private String findMethodPath(HttpServletRequest request, Method method) throws IllegalAccessException, InstantiationException {
        String value = null;
        switch (request.getMethod()) {
            case "POST":
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                if (postMapping != null) {
                    value = postMapping.value();
                }
                break;
            case "GET":
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping != null) {
                    value = getMapping.value();
                }
                break;
        }

        return value;
    }


    private List<Class> findAllControllers(String projectDirectory) throws ClassNotFoundException {
        List<Class> controllerClasses = new ArrayList<>();
        File directory = new File(projectDirectory);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Class currentClass = this.getClass(file);
                if (currentClass == null) {
                    continue;
                }

                if (currentClass.isAnnotationPresent(Controller.class)) {
                    controllerClasses.add(currentClass);
                }
            } else if (file.isDirectory()) {
                String subDirectory = file.getAbsolutePath();
                controllerClasses.addAll(findAllControllers(subDirectory));
            }
        }

        return controllerClasses;
    }

    private Class getClass(File file) throws ClassNotFoundException {
        String absolutePath = file.getAbsolutePath();
        String classPattern = "^(.+classes\\\\)(.+)(.class)$";
        Pattern pattern = Pattern.compile(classPattern);
        Matcher matcher = pattern.matcher(absolutePath);
        Class currentClass = null;
        if(matcher.find()) {
            String className = matcher.group(2).replace("\\", ".");
            if (!className.endsWith("DispatcherServlet")) {
                currentClass = Class.forName(className);
            }
        }

        return currentClass;
    }
}
