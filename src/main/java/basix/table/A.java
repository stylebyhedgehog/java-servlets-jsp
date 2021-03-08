package basix.table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/A")
public class A extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String message="";
        response.setContentType("text/html");
        response.setCharacterEncoding("Windows-1251");
        if (response.getStatus()==512){
            message="введите число";
        }

        try {
            PrintWriter out = response.getWriter();
            out.println(
                    "<h2>"+message+"</h2>"
                    +"<form action=B method=GET>"
                    + "<input type=text name=rows value=\"Строки\"><br>"
                    + "<input type=text name=columns value=\"Столбцы\">"
                    + "<input type=submit name=s1 value=Ok>"
                    + "<input type=hidden name=h1 value=\"hiddenValue\">");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
