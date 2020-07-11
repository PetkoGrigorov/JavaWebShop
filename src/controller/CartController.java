package controller;

import framework.annotation.MVCRoute;
import framework.controllerSystem.WebController;
import model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartController extends WebController {

    @MVCRoute(path = "/cart/list", method = "GET")
    public  void list(HttpServletRequest req, HttpServletResponse resp) {

        display(req, resp, "purchase/cart.jsp");

    }

    @MVCRoute(path = "/cart/add", method = "GET")
    public void addToCart(HttpServletRequest req, HttpServletResponse resp) {

        if (getSessionAttrib(req, "logged_name") == null) {
            redirect(resp, "/base/auth/login");
        } else {

            ArrayList<Product> cart = null;
            if (getSessionAttrib(req, "cart_list") == null) {
                cart = new ArrayList<>();
            } else {
                cart = (ArrayList<Product>) getSessionAttrib(req, "cart_list");
            }

            int id = 0;
            if (hasQuery(req, "product_id")) {
                id = Integer.parseInt(getQueryValue(req, "product_id"));
            }
            try {
                cart.add(Product.fetchProductByID(id));
                setSessionAttrib(req, "cart_list", cart);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            redirect(resp, "/base/product/list");
        }

    }

    @MVCRoute(path = "/cart/remove", method = "GET")
    public void removeProduct(HttpServletRequest req, HttpServletResponse resp) {

        ArrayList<Product> cart = (ArrayList<Product>) getSessionAttrib(req, "cart_list");
        int id = 0;
        if (hasQuery(req, "product_id")) {
            id = Integer.parseInt(getQueryValue(req, "product_id"));
        }
        if (id > 0) {
            for (Product product : cart) {
                if (Integer.parseInt(product.getProductId()) == id) {
                    cart.remove(product);
                    break;
                }
            }

            setSessionAttrib(req, "cart_list", cart);
        }
        display(req, resp, "purchase/cart.jsp");
//        redirect(resp, "/base/cart/list");
    }

}
