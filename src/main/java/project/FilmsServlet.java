package project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/test1")
public class FilmsServlet extends HttpServlet {

    private static void generateMainForm(PrintWriter out) {
        out.println(""
                + "<form action=test method=GET>"
                + "Вывести первые"
                + "<input name=quantity type=number/>"
                + "<input type=submit name=submitNumber value=OK>"
                + "</form>");
    }

    private void producer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // print form
        generateMainForm(out);

        //get connection -> get quantity from form -> resultset from db
        FilmDAO filmDAO=new FilmDAO(DbConnector.getConnection());
        int numberOfFilmsToExtract = Integer.parseInt(request.getParameter("quantity"));
        ResultSet resultSet=filmDAO.readByQuantity(numberOfFilmsToExtract);

        //parse restultset to htmltable & print
        Parser parser = new Parser(resultSet);
        out.println(parser.toHtmlTable());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            producer(request, response);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
