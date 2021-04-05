package project.auth;

import project.Repository.ClientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static java.util.Objects.nonNull;

@WebServlet("/login")
public class AuthPage extends HttpServlet {
    private String login;
    private String form(){
        return "  <form method=\"post\" action=\"login\">\n" +
                "<input type=\"text\" required placeholder=\"login\" name=\"login\"><br>\n" +
                "<input type=\"text\" required placeholder=\"password\" name=\"password\"><br><br>\n" +
                "<input class=\"button\" type=\"submit\" value=\"Войти\">\n" +
                "</form>";
    }

//    private boolean isLogged(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        final HttpSession session = req.getSession();
//        if (nonNull(session) &&
//                nonNull(session.getAttribute("login")) &&
//                nonNull(session.getAttribute("password"))) {
//            String login =  (String) session.getAttribute("login");
//            if (session.getAttribute("role").toString().equals("client")){
//                resp.sendRedirect(req.getContextPath() + "/greeting?login="+login);
//            };
//        }
//    }
    private boolean auth(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        boolean flag=false;
        String login=req.getParameter("login");
        String password=req.getParameter("password");
        ClientDAO clientDAO = new ClientDAO();
        if (clientDAO.userIsExist(login, password)) {
            final String role = clientDAO.getUserRole(login, password);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            this.login=login;
            flag=true;
        }
        return flag;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (auth(req,resp)){

            resp.sendRedirect(req.getContextPath() + "/greeting");
            }
            else {
                doGet(req,resp);

            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(form());
       }
}
