package image.uploadImageToServer;

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

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/ServletUploadFile")
public class ServletUploadFile extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "UPLOAD";

    private void saveToDb(){

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = "C:\\Users\\VANSHIELD\\Desktop\\ss";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

            String fileName;
            for (Part part : request.getParts()) {
                if (!((fileName = part.getSubmittedFileName()) == null)) {
                            part.write(uploadPath + File.separator + fileName);
                            try (PrintWriter out = response.getWriter()){

                        out.println("uploadPath: "+uploadPath);
                        out.println("fileName: "+fileName);
                        out.println("Success!");
                    }


                }
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        OLD

        ДОПОЛНИТЕЛЬНО ПОСМОТРЕТЬ СТАРУЮ РЕАЛИЗАЦИЮ:
        СМ. ПАПКУ fileupload/
        A_ -> B_ -> C_

        SAXPARSER VS JAXB
        APACHE COMMONS UPLOAD VS REQUEST.GETPARTS

        ---------------
        NEW

        ЕСТЬ XML ФАЙЛ - КАКОЙ-ТО
        1) ПОДГРУЗИТЬ ЕГО СМ. SENDPICTURE.JSP -> ServletUploadFile.java - REQUEST.GETPARTS
        2) РАСПАРСИТЬ, СМ. JAXB
        2b) ПРОМЕЖУТОЧНУЮ ИНФОРМАЦИЮ ХРАНИТЬ В DTO (В СТАРОЙ ВЕРСИИ ДЛЯ ЭТОГО ИСПОЛЬЗОВАЛСЯ TableiNFO)
        3) ВЫВЕСТИ ЧАСТЬ РАСПАРСЕННОЙ ИНФОРМАЦИИ НА СТРАНИЦУ - ОБЫЧНЫМ OUT.PRINTLN

        ----------------------





         */

    }
}
