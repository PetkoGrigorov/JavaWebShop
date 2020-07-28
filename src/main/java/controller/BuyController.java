package controller;

import framework.annotation.Authenticated;
import framework.annotation.MVCRoute;
import framework.controllerSystem.WebController;
import model.system.AuthUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyController extends WebController {

    @MVCRoute(path = "/buy/pay", method = "GET")
    @Authenticated
    public void pay(HttpServletRequest req, HttpServletResponse resp) {
        display(req, resp, "purchase/pay.jsp");
    }


    public void pay_post() {

    }

}

