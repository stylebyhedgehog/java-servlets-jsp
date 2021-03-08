package basix.generators;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try{
            int columns=Integer.parseInt(request.getParameter("columns"));

            try (PrintWriter out = response.getWriter()) {
                out.println("Таблица сгенерирована в классе FirstPageGenerator статическим методом <i>mainForm</i><br>"
                        + "Запрос в адресной строке вида: http://localhost:8080/servletsT1_war_exploded/MyServlet?columns=x задает число столбцов таблицы равным x.<br><br>");

                FirstPageGenerator.mainForm(out, columns);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
