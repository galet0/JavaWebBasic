package servletDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/main")
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> weekdays = new ArrayList(){{
           add("Saturday");
           add("Sunday");
        }};
        req.setAttribute("weekdays", weekdays);
        req.getRequestDispatcher("beer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String click = req.getParameter("click");
        if(click != null){
            print(click, resp.getWriter());
        }
    }

    private void print(String value, PrintWriter writer) {
        writer.print(value);
    }
}
