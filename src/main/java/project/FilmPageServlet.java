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

@WebServlet("/showFilm")
public class FilmPageServlet extends HttpServlet {
    private void producer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        FilmDAO filmDAO = new FilmDAO(DbConnector.getConnection());
        ResultSet resultSet= filmDAO.readOneById(Integer.parseInt(request.getParameter("id")));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("poihalo");
        while (resultSet.next()){
            out.println( resultSet.getString("title"));
            out.println( resultSet.getString("description"));
            //для изображения отдельный сервлет
            out.println("<img src=\"file:///C:/Users/VANSHIELD/Desktop/ss/3.jpg\"" +
                    "   alt=lorem>");
            out.println(resultSet.getInt("images"));
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            producer(request, response);
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
