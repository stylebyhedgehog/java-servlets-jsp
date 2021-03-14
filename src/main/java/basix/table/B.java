package basix.table;

import org.apache.commons.logging.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/B")
public class B extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger( B.class.getName() );
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("Windows-1251");
        try {
            PrintWriter out = response.getWriter();
//            try {
                int row = Integer.parseInt(request.getParameter("rows"));
                int col = Integer.parseInt(request.getParameter("columns"));
                out.println("" +
                        "Table: строк " + row + " столбцов " + col + getServletInfo().toString() + "\n" +
                        "<font name=tahoma size=6 bgcolor=White>" +
                        "<table border=2 bgcolor=yellow>" +
                        "");
                for (int i = 0; i < row; i++) {
                    out.println("<tr>");
                    for (int j = 0; j < col; j++) {
                        out.println("" +
                                "<td>i*j=" + i * j + "</td>" +
                                "");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");

            out.println("" +
                    "Table: строк " + row + " столбцов " + col + getServletInfo().toString() + "\n" +
                    "<font name=tahoma size=6 bgcolor=White>" +
                    "<table border=2 bgcolor=yellow>" +
                    "");
            for (int i = 0; i < col; i++) {
                out.println("<tr>");
                for (int j = 0; j < row; j++) {
                    out.println("" +
                            "<td>i*j=" + i * j + "</td>" +
                            "");
                }
                out.println("</tr>");
            }
            out.println("</table>");
//            } catch (NumberFormatException e) {
//                LOGGER.log(Level.SEVERE,"Задать числа в качестве строк и столбцов:", e);
//            }
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("columns").matches("\\d+")){
            processRequest(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/A");
            response.setStatus(512);
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
