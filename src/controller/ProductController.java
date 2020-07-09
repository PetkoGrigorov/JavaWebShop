package controller;

import framework.annotation.MVCRoute;
import framework.controllerSystem.WebController;
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
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProductController extends WebController {

    public void index(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.index");

    }

    @MVCRoute(path = "/product/list", method = "GET")
    public void list(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ArrayList<Product> productCollection = Product.fetchAll();
            req.setAttribute("productList", productCollection);
            display(req, resp, "product/list.jsp");
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

    @MVCRoute(path = "/product/details", method = "GET")
    public void singleProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = 0;
        if (hasQuery(req, "product_id")) {
            id = Integer.parseInt(getQueryValue(req, "product_id"));
        }

        try {
            Product product = Product.fetchProductByID(id);
            session(req, "product", product);
            display(req, resp, "product/single_product.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void create(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.create");
    }

    public void remove(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.remove");
    }

}
