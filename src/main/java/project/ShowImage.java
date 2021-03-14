package project;

import project.Model.Film;
import project.Repository.FilmDAO;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/png ");
        try (ServletOutputStream out = response.getOutputStream()) {
            FilmDAO filmDAO = new FilmDAO();
            Film film= filmDAO.readOne(Integer.parseInt(request.getParameter("id")));
            String path=film.getImage_path();

            File file = new File(path);
            BufferedImage imagePng = ImageIO.read(file);
            ImageIO.write(imagePng, "PNG", response.getOutputStream());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}