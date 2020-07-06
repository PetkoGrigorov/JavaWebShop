package framework.controllerSystem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class WebController {

    private String absolutePath = "http://localhost:8080/JavaWebShop_war_exploded";

    public void redirect(HttpServletResponse resp, String path) {
        try {
            resp.sendRedirect(this.absolutePath + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(HttpServletRequest req, HttpServletResponse resp, String path) {
        try {
            req.getRequestDispatcher("/" + path).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void session(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }
}
