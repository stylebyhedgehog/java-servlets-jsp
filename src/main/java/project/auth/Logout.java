package project.auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class Logout  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        RequestDispatcher rd = req.getRequestDispatcher("login");
        rd.forward(req, resp);
    }
}
