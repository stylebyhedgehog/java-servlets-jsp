package jdbc;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/test/film")
public class FilmPageServlet  extends HttpServlet {


    private void producer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        FilmDAO filmDAO=new FilmDAO(DbConnector.getConnection());
        int id = Integer.parseInt(request.getParameter("id"));
        Film film=filmDAO.readOne(id);
        out.println(film.getTitle());
        out.println(film.getDescription());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            producer(request, response);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   {

    }
}
