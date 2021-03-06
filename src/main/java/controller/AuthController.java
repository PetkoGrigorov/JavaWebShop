package controller;

import config.RouteMap;
import framework.annotation.MVCRoute;
import framework.annotation.MVCRouteController;
import framework.controllerSystem.WebController;
import model.system.AuthUser;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AuthController extends WebController {

    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        display(req, resp, "home.jsp");

        System.out.println("AuthController.index");
    }

    @MVCRoute(path = "/auth/login", method = "GET")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (AuthUser.isUserAuthenticated()) {
            redirectAbsolutePath(resp, RouteMap.PRODUCT_LIST);
            return;
        }

        display(req, resp, "login.jsp");

//        Database.getInstance().update("users", new HashMap<String, Object>(){{
//            put("username", "klint");
//            put("fname", "Kiko");
//            put("lname", "Loom");
//            put("age", 32);
//        }}).where(new Database.WhereClause("username", Database.WhereOperator.EQUAL, "loop", "users"))
//                .andWhere(new Database.WhereClause("age", Database.WhereOperator.GREATER, 21, "users"))
//                .orWhere(new Database.WhereClause("role", Database.WhereOperator.LOWER, 5, "user_role")).printQuery();

    }

    @MVCRoute(path = "/auth/login", method = "POST")
    public void post_login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (AuthUser.isUserAuthenticated()) {
            redirectAbsolutePath(resp, RouteMap.PRODUCT_LIST);
            return;
        }

        String userEmail = req.getParameter("user_email");
        String userPassword = req.getParameter("user_pass");
        
        System.out.println("email: " + userEmail);
        System.out.println("pass: " + userPassword);

        AuthUser.authenticateUser(userEmail, userPassword);

        if (!AuthUser.isUserAuthenticated()) {
            req.setAttribute("message", "Invalid email or password!");
            setSessionAttrib(req, "logged_name", null);
        } else {
            setSessionAttrib(req, "logged_name", AuthUser.getUserFullName());
        }

        this.login(req, resp);
    }

    @MVCRoute(path = "/auth/logout", method = "GET")
    public void logout(HttpServletRequest req, HttpServletResponse resp) {

        AuthUser.destroyUser();
        req.getSession().invalidate();
        redirectAbsolutePath(resp, RouteMap.HOME);
    }

    @MVCRoute(path = "/auth/registration", method = "GET")
    public void registration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (AuthUser.isUserAuthenticated()) {
            redirectAbsolutePath(resp, RouteMap.PRODUCT_LIST);
            return;
        }

        display(req, resp, "registration.jsp");

//        resp.sendRedirect("/JavaWebShop_war_exploded/registration.jsp");


    }

    @MVCRoute(path = "/auth/registration", method = "POST")
    public void post_registration(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {

        if (AuthUser.isUserAuthenticated()) {
            redirectAbsolutePath(resp, RouteMap.PRODUCT_LIST);
            return;
        }

        String username = req.getParameter("user_name");
        String pass = req.getParameter("user_pass");
        String passRepeat = req.getParameter("user_pass_repeat");
        String email = req.getParameter("user_email");
        String fName = req.getParameter("user_fname");
        String lName = req.getParameter("user_lname");

        System.out.println("------------------------------");
        System.out.println("user_name: " + username);
        System.out.println("user_pass: " + pass);
        System.out.println("user_email: " + email);
        System.out.println("user_fname: " + fName);
        System.out.println("user_lname: " + lName);
        System.out.println("------------------------------");

        User.create(username, pass, email, fName, lName);

        AuthUser.authenticateUser(email, pass);

        if (!AuthUser.isUserAuthenticated()) {
            req.getSession().setAttribute("logged_name", "Registration Fail");
            this.registration(req, resp);
        } else {
            req.getSession().setAttribute("logged_name", AuthUser.getUserFullName());
            redirectAbsolutePath(resp, RouteMap.PRODUCT_LIST);
        }

//        this.registration(req, resp);

    }

}
