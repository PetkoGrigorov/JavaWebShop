package controller;

import framework.annotation.MVCRoute;
import framework.db.Database;
import framework.db.DatabaseOrm;
import framework.db.exceptoin.CustomOrmException;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {

    public void index(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.index");

    }

    @MVCRoute(path = "/product/list", method = "GET")
    public void list(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ArrayList<Product> productCollection = Product.fetchAll();
            ArrayList<String> productList = new ArrayList<>();
            for (Product element : productCollection) {
                productList.add(element.getTitle());
            }
            req.setAttribute("productList", productList);
            req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CustomOrmException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
