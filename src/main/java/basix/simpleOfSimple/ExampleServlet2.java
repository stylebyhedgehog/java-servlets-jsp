package basix.simpleOfSimple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;

//задача - вытащить параметр из request
//убедиться, что без обработки NumberFormatException сервлет нормально не запускается, "вылетает"
//поработать с указанием параметра прямо в адресной строке: http://localhost:8080/servletsT1_war_exploded/Servlet2ex?numberName=3 и т.д.

//http://localhost:8080/servletsT1_war_exploded/servlet2ex?number=1&number=5
@WebServlet("/servlet2ex")
public class ExampleServlet2 extends HttpServlet {

    public interface UsefulNotes {
        String number="number";

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("Windows-1251");
        PrintWriter out = response.getWriter();
        String[] numArray = request.getParameterValues(UsefulNotes.number);
        int sum=0;
        StringBuilder displaySum= new StringBuilder();
        for (String s : numArray) {
            sum += Integer.parseInt(s);
            displaySum.append(s).append("+");
        }
        displaySum.deleteCharAt(displaySum.lastIndexOf("+"));
        displaySum.append("=").append(sum);
        out.println(displaySum);
        }

}
