package project;

import project.Model.Film;
import project.Repository.FilmDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/add_film")
public class AddFilmServlet extends HttpServlet {

    private String saveImage(HttpServletRequest request) throws IOException, ServletException {
        String uploadPath = "C:\\Users\\VANSHIELD\\Desktop\\ss";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        Part part=request.getPart("file");
        String fileName =part.getSubmittedFileName();
        part.write(uploadPath + File.separator + fileName);
        return uploadPath + File.separator + fileName;
    }

    private void producer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, ServletException {
        // create film
        FilmDAO filmDAO=new FilmDAO();
        String title =request.getParameter("title");
        String description =request.getParameter("description");
        String path=saveImage(request);
        Film film=new Film(null,title,description,path);
        Integer id_film=filmDAO.create(film);
        //create image to film
        response.sendRedirect(request.getContextPath() + "/film?id="+id_film.toString());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(
                "<form method=\"post\" action=\"add_film\" enctype=\"multipart/form-data\">\n" +
                "    <input name=title type=\"text\"/>\n" +
                "    <input name=\"description\" type=\"text\"/>\n" +
                "    <input type=\"file\" name=\"file\" />\n" +
                "    <input type=\"submit\" value=\"Upload\" />\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            producer(request, response);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
