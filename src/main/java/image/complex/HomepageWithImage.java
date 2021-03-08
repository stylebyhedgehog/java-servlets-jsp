/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.complex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomepageWithImage")
public class HomepageWithImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>HomepageWithImage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>HomepageWithImage</h1>"
                    + "<img src=\"ImageOutput\">");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
