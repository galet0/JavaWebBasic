package softuni.app.controller;


import softuni.app.view.UsersDetailsView;
import softuni.server.http.response.HttpResponse;
import softuni.server.http.response.HttpResponseCode;
import softuni.server.http.response.ViewResponse;

public class UsersController {

    public HttpResponse details(String name){
        System.out.println(name);
        return new ViewResponse(HttpResponseCode.OK, new UsersDetailsView());
    }
}
