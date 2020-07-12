package controller;

import framework.annotation.MVCRoute;
import framework.controllerSystem.WebController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyController extends WebController {

    @MVCRoute(path = "/buy/pay", method = "GET")
    public void pay(HttpServletRequest req, HttpServletResponse resp) {
        display(req, resp, "purchase/pay.jsp");
    }
}
