package br.com.livro.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {

    public static void writeXML (HttpServletResponse response, String xml)
        throws IOException {

        if(xml != null) {
            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/xml;charset=UTF-8");
            printWriter.write(xml);
            printWriter.close();
        }
    }

    public static void writeJson(HttpServletResponse response, String json)
        throws IOException {

        if (json != null) {
            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json;charset=UTF-8");
            printWriter.write(json);
            printWriter.close();
        }
    }
}
