package controller;

import config.PageMap;
import framework.controllerSystem.WebController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageNotFoundController {

    public static void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(PageMap.PREFIX + PageMap.PAGE_NOT_FOUND);
        System.out.println("Error 404");
    }

}
