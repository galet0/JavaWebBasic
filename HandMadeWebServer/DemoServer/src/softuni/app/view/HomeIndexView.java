package softuni.app.view;


import softuni.server.Model;
import softuni.server.View;

public class HomeIndexView implements View{

    private final Model model;

    public HomeIndexView(Model model) {
        this.model = model;
    }

    @Override
    public String view() {
        return String.format("<html><body><h3>Welcome %s</h3></body></html>", (String)this.model.get("name"));
    }
}
