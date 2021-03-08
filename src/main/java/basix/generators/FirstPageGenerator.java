package basix.generators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstPageGenerator {

    public static void mainForm(PrintWriter out, int cols) {

            out.println("mainForm:");
            out.println("<table border=3>");

            for (int i = 0; i < 10; i++) {
                out.println("<tr>");
                for (int j = 0; j < cols; j++) {
                    out.println("<td>");
                    out.println("i*j=" + i * j);
                    out.println("</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");


    }

}

