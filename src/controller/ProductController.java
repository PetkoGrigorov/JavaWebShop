package controller;

import framework.annotation.MVCRoute;
import framework.controllerSystem.WebController;
import framework.db.Database;
import model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController extends WebController {

    public void index(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.index");

    }

    @MVCRoute(path = "/product/list", method = "GET")
    public void list(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int productCount = getProductCount();
            setSessionAttrib(req, "product_count", productCount);
            int pageIndex = getPageIndex(req);
            int pageLimit = getPageLimit(req);
            int numberOfPages = (int) Math.ceil(productCount*1.0/pageLimit);
            if (pageIndex > numberOfPages) {
                pageIndex = numberOfPages;
            }
            if (pageIndex < 1) {
                pageIndex = 1;
            }
            int offsetCorrection = (pageIndex < 1) ? 0 : (pageIndex - 1);
            setSessionAttrib(req, "page_index", pageIndex);

            ArrayList<Product> productCollection = Product.fetchLimit(pageLimit, (offsetCorrection * pageLimit));
            req.setAttribute("productList", productCollection);
            setSessionAttrib(req, "page_limit", pageLimit);
            display(req, resp, "product/list.jsp");

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
            setSessionAttrib(req, "product", product);
            display(req, resp, "product/single_product.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private int getPageIndex(HttpServletRequest req) {
        int pageIndex = 1;
        if (getSessionAttrib(req, "page_index") != null) {
            pageIndex = Integer.parseInt((req.getSession().getAttribute("page_index")).toString());
        }
        pageIndex = (hasQuery(req, "page_index")) ? Integer.parseInt(getQueryValue(req,"page_index")) : pageIndex;
        return pageIndex;
    }

    private int getPageLimit(HttpServletRequest req) {
        int pageLimit = 10;
        if (getSessionAttrib(req, "page_limit") != null) {
            pageLimit = Integer.parseInt ((req.getSession().getAttribute("page_limit")).toString());
        }
        pageLimit = (hasQuery(req, "page_limit")) ? Integer.parseInt(getQueryValue(req,"page_limit")) : pageLimit;
        req.getSession().setAttribute("page_limit", pageLimit);
        return pageLimit;
    }

    private int getProductCount() {
        int productCount = 0;
        try {
            ResultSet resultSet = Database.getInstance().getDbStatement().executeQuery("SELECT COUNT(*) AS entry_count FROM products");
            while (resultSet.next()) {
                productCount = resultSet.getInt("entry_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCount;
    }

    public void create(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.create");
    }

    public void remove(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Execute: ProductController.remove");
    }

}
