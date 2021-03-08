/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image.complex;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ImageOutput")
public class BufferedImageOutputServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg ");
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(BufferedImageCreating.getImage(), "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
