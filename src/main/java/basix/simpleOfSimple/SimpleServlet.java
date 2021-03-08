package basix.simpleOfSimple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("Windows-1251");

        PrintWriter out = response.getWriter();
        String n = request.getParameter("name");
        String s = request.getParameter("surname");

        out.println("<b>Hello, " + n + " " + s + ".</b><br><br>");
        out.println("<b>Print form.</b><br><br>");
        out.println("<form action=SimpleServlet>"
                + "<input type=text name=name value=\"Name\"><br>"
                + "<input type=text name=surname value=\"Surname\">"
                + "<input type=submit name=s1 value=Ok>"
                + "<input type=hidden name=h1 value=\"hiddenValue\">");

        out.println("<br><b>"+"-------------------------"+"</b><br>");

        for (int i = 0; i < 10; i++) {
            out.println("<font size=" + i + ">");
            out.println("Print info.<br>");
            out.println("</font>");

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
