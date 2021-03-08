package basix.requestDispatcher.includeServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/iinfo")
public class IncludeInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("TestServlet says hi<br/>" +
                "And waiting for '?action=include' or '?action=forward' parameter input ...<br><br>");
        String action = request.getParameter("action");
        System.out.println("action="+action);
        if (action != null) {
            RequestDispatcher rd = request.getRequestDispatcher("info");
            if ("include".equalsIgnoreCase(action)) {
        System.out.println("action ...="+action);
                rd.include(request, response);
            } else if ("forward".equalsIgnoreCase(action)) {
                rd.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
