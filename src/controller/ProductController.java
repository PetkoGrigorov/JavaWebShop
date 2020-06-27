package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController {

    public void index(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.index");

    }

    public void list(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Execute: ProductController.list");

    }

    public void create(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.create");
    }

    public void remove(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.remove");
    }

}
