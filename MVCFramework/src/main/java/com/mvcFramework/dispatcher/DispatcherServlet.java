package com.mvcFramework.dispatcher;

import com.mvcFramework.controllerAction.ControllerActionPair;
import com.mvcFramework.handlers.HandlerActionImpl;
import com.mvcFramework.handlers.HandlerMappingImpl;
import com.mvcFramework.interfaces.Dispatcher;
import com.mvcFramework.interfaces.HandlerAction;
import com.mvcFramework.interfaces.HandlerMapping;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URLDecoder;


@WebServlet("/")
public class DispatcherServlet extends HttpServlet implements Dispatcher {

    private HandlerMapping handlerMapping;

    private HandlerAction handlerAction;

    public DispatcherServlet() {
        this.handlerMapping = new HandlerMappingImpl();
        this.handlerAction = new HandlerActionImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        print URI /beer
//        response.getWriter().println(request.getRequestURI());
//        print URL http://localhost:8080/beer
//        response.getWriter().println(request.getRequestURL());
        if(isResource(request)){
            this.sendResourceResponse(request, response);
            return;
        }
        this.handleRequest(request, response);

    }

    private void sendResourceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getRequestURI();
        String directory = URLDecoder.decode(request.getServletContext().getResource("/").getPath(), "UTF-8");
        File file = new File(directory + url);
        try(
                BufferedReader bfr= new BufferedReader(new FileReader(file))
        ) {
            String line;
            while ((line = bfr.readLine()) != null){
                response.getWriter().print(line);
            }
        }
    }

    private boolean isResource(HttpServletRequest request){
        boolean isResource = false;
        String url = request.getRequestURI();
        if(url.contains(".")){
            isResource = true;
        }

        return isResource;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.handleRequest(request, response);
    }



    @Override
    public ControllerActionPair dispatchRequest(HttpServletRequest request) {
        ControllerActionPair controllerActionPair = null;
        try {
            controllerActionPair = this.handlerMapping.findController(request);
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return controllerActionPair;
    }

    @Override
    public String dispatchAction(HttpServletRequest request, HttpServletResponse response, ControllerActionPair controllerActionPair) {
        String view = null;

        try {
            view = this.handlerAction.executeControllerAction(request, response, controllerActionPair);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException | NamingException e) {
            e.printStackTrace();
        }

        return view;
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerActionPair controllerActionPair = this.dispatchRequest(request);
        if(controllerActionPair != null) {
            String view = this.dispatchAction(request, response, controllerActionPair);
            try {
                if (view.startsWith("redirect:")) {
                    String redirectPath = view.replace("redirect:", "");
                    response.sendRedirect(redirectPath);
                } else {
                    request.getRequestDispatcher("/" + view + ".jsp").forward(request, response);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
