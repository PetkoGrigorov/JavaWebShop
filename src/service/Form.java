package service;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class Form {

    public static void href(JspWriter instance, String link, String title) {

        String href = "<a href=\"" + Util.getFullPath(link) + "\">" + title + "</a>";
        try {
            instance.print(href);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
