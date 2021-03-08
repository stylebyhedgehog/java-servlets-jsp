package basix.simpleOfSimple;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//http://localhost:8080/servletsT1_war_exploded/basix/servlet5ex?number=17
@WebServlet("/basix/servlet5ex")
public class ExampleServlet4 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (request.getParameter("number").matches("\\d+")){
            int number = Integer.parseInt(request.getParameter("number"));
            for (int i = 0; i < number; i++) {
                out.println("Hello, world!");
            }
        }
        else {
            out.println("¬ведите число");
        }

    }
}
