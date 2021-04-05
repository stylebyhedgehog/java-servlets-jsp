package listeners.config_context;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        value = "/example",
        initParams = {
                @WebInitParam(name = "email", value = "webmaster@domain.com", description = "Email from webmaster"),
                @WebInitParam(name = "phone", value = "xxxx/xx.xx.xx", description = "Phone webmaster")
        },
        description = "Initialization parameters example annotation: @WebInitParam")

public class MyServletConfigInitParamReader extends HttpServlet{

    private String email, phone;

    @Override
    public void init(ServletConfig config) throws ServletException {
        email = config.getInitParameter("email");
        phone = config.getInitParameter("phone");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<h2>Initialization parameters example annotation: @WebInitParam</h2>");
        out.write("<p><strong>E-mail: </strong>" + email + "</p>");
        out.write("<p><strong>Phone: </strong>" + phone + "</p>");
    }

}
