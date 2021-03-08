package basix.simpleOfSimple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//простейший пример, вывести 10 раз "Hello, World!" через response
//обратить внимание на алиас "/basix/servlet5"

@WebServlet("/basix/servlet5")
public class Servlet4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //int number = Integer.parseInt(req.getParameter("number"));
        PrintWriter out = response.getWriter();
        for (int i = 0; i < 10; i++) {
            out.println("Hello, world!");
        }
    }
}
