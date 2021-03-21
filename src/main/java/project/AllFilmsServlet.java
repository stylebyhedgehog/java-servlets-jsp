package project;



import project.Model.Film;
import project.Repository.FilmDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/app/films")
public class AllFilmsServlet extends HttpServlet {

    public void getFilms(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        FilmDAO filmDAO=new FilmDAO();
        ArrayList<Film> filmArrayList=filmDAO.readAll();

        Parser parser = new Parser(filmArrayList);
        out.println(parser.toHtmlTable());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getFilms(req,resp);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
