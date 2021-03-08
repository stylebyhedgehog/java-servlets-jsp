package basix.bruceperry;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
хороший пример
переделан с примера Брюса Перри
используется только один сервлет,
но за счет использование и GET и POSE
doGet передает информацию в doPost.
Получается эффект разделения логики.
 */
@WebServlet("/formgetpost")
public class FirstServlet extends HttpServlet {

    private interface HTMLGenerator{
        class Form{
            private static String makeForm(HttpServletRequest request) {
                return "<html><head>"
                +"<title>Help Page</title></head><body>"
                +"<h2>Please submit your information</h2>"

                //make sure method="post" so that the servlet service method
                //calls doPost in the response to this form submit
                +
                        "<form method=\"post\" action =\"" + request.getContextPath() +
                                "/formgetpost\" >"
                +"<table border=\"0\"><tr><td valign=\"top\">"
                +"Your first name: </td>  <td valign=\"top\">"
                +"<input type=\"text\" name=\"firstname\" size=\"20\">"
                +"</td></tr><tr><td valign=\"top\">"
                +"Your last name: </td>  <td valign=\"top\">"
                +"<input type=\"text\" name=\"lastname\" size=\"20\">"
                +"</td></tr><tr><td valign=\"top\">"
                +"Your email: </td>  <td valign=\"top\">"
                +"<input type=\"text\" name=\"email\" size=\"20\">"
                +"</td></tr><tr><td valign=\"top\">"
                +"<input type=\"submit\" value=\"Submit Info\"></td></tr>"
                +"</table></form>"
                +"</body></html>";
            }
        }
    }


  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

      System.out.println("command: " + request.getParameter("command"));

      //set the MIME type of the response, "text/html"
      response.setContentType("text/html");

      //use a PrintWriter send text data to the client who has requested the servlet
      PrintWriter out = response.getWriter();

      out.println(HTMLGenerator.Form.makeForm(request));
  }

	 public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{

	//display the parameter names and values
	Enumeration paramNames = request.getParameterNames();

	String parName;//this will hold the name of the parameter from the HTML form

	boolean emptyEnum = false;
	if (! paramNames.hasMoreElements())
	    emptyEnum = true;


	//set the MIME type of the response, "text/html"
    response.setContentType("text/html");

    //use a PrintWriter send text data to the client who has requested the servlet
    PrintWriter out = response.getWriter();

	//Begin assembling the HTML content

         out.println("<html><head>");
    out.println("<title>Submitted Parameters</title></head><body>");

	if (emptyEnum){
	    out.println("<h2>Sorry, the request does not contain any parameters</h2>");
	} else {
	    out.println("<h2>Here are the submitted parameter values</h2>");
	}

	while(paramNames.hasMoreElements()){

	    parName = (String) paramNames.nextElement();
	    out.println("<strong>" + parName + "</strong> : " + request.getParameter(parName));
	    out.println("<br />");
	}//while

	out.println("</body></html>");

	}// doPost
}
