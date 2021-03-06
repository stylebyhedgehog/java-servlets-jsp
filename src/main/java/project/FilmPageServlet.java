package project;


import project.Model.Film;
import project.Repository.FilmDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/app/film")
public class FilmPageServlet extends HttpServlet {
    Film film;
    FilmDAO filmDAO;
    private void editForm(PrintWriter out) throws IOException, SQLException, ClassNotFoundException {
        out.println(
                "<form method=\"post\" action=\"film\" >\n" +
                        "    <input name=\"title\" type=\"text\" value=\""+this.film.getTitle()+"\"/>\n" +
                        "    <input name=\"description\" type=\"text\" value=\""+this.film.getDescription()+"\"/>\n" +
                        "    <input name=\"mtype\" type=\"submit\" value=\"update\" />\n" +
                        "</form>");
    }
    private void deleteForm(PrintWriter out) throws IOException, SQLException, ClassNotFoundException {

        out.println(
                "<form method=\"post\" action=\"film\" >\n" +
                      " <input name=\"mtype\" type=\"submit\" value=\"delete\" />\n" +
                        "</form>");
    }
    private void getFilmData(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        filmDAO = new FilmDAO();

        Wrapper wrapper=new Wrapper(request);
        out.println("<h1>"+wrapper.getParameter("id")+"</h1>");

        Film film= filmDAO.readOne(Integer.parseInt(request.getParameter("id")));
        this.film=film;
        out.println("<img src=\"/app/ShowImage?id="+request.getParameter("id")+"\">" );
        out.println("<h1>"+ film.getTitle()+"</h1>");
        out.println("<h1>"+ film.getDescription()+"</h1>");

        editForm(out);
        deleteForm(out);
    }

    private void updateFilmData(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        this.film.setTitle(request.getParameter("title"));
        this.film.setDescription(request.getParameter("description"));
        this.filmDAO.update(this.film);
    }

    private void deleteFilmData(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        this.filmDAO.delete(this.film);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            getFilmData(request, response);
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("mtype").equals("delete")){
                deleteFilmData(req);
                resp.sendRedirect(req.getContextPath() + "/app/films");
            }
            if (req.getParameter("mtype").equals("update")){
                updateFilmData(req);
                resp.sendRedirect(req.getContextPath() + "/app/film?id="+this.film.getId().toString());
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
