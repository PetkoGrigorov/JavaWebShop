package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageNotFoundController {

    public static void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/JavaWebShop_war_exploded/error404.jsp");
        System.out.println("Error 404");
    }

}
