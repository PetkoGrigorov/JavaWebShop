package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController {

    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

        System.out.println("Execute: HomeController.index");
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

        System.out.println("Execute: HomeController.list");
    }

    public void create() {
        System.out.println("Execute: HomeController.create");
    }

    public void remove() {
        System.out.println("Execute: HomeController.remove");
    }


}
