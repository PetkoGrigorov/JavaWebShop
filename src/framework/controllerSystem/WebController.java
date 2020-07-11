package framework.controllerSystem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public abstract class WebController {

    private String absolutePath = "http://localhost:8080/JavaWebShop_war_exploded";

    public void redirect(HttpServletResponse resp, String path) {
        try {
            resp.sendRedirect(this.absolutePath + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(HttpServletRequest req, HttpServletResponse resp, String path) {
        try {
            req.getRequestDispatcher("/" + path).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSessionAttrib(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
    }

    public Object getSessionAttrib(HttpServletRequest req, String key) {
        return req.getSession().getAttribute(key);
    }

//    id=1&price=1&index=3

    public HashMap<String, String> splitQueryMap(HttpServletRequest req) {
        HashMap<String, String> keyValueHash = new HashMap<>();
        String queryString = req.getQueryString();
        if (queryString == null) {
            return null;
        }
        String[] querySplit = req.getQueryString().split("&");
        for (String element : querySplit) {
            String[] splitArr = element.split("=");
            if (splitArr.length == 1) {
                keyValueHash.put(splitArr[0], splitArr[0]);
            }
            keyValueHash.put(splitArr[0], splitArr[1]);
        }
        return keyValueHash;
    }

    public String getQueryValue(HttpServletRequest req, String key) {
        return splitQueryMap(req).get(key);
    }

    public boolean hasQuery(HttpServletRequest req, String key) {
        HashMap<String, String> queryMap = splitQueryMap(req);
        return (queryMap == null) ? false : queryMap.containsKey(key);
    }


}
