package project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDao {
    private final Connection connection;
    public ImageDao(final Connection connection) {
        this.connection = connection;
    }

    public ResultSet readOneById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLImage.GET_BY_ID.QUERY) ;
        statement.setInt(1, id);
        return statement.executeQuery();
    }

    private String saveImage(HttpServletRequest request) throws IOException, ServletException {
        String uploadPath = "C:\\Users\\VANSHIELD\\Desktop\\ss";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        Part part=request.getPart("file");
        String fileName =part.getSubmittedFileName();
        part.write(uploadPath + File.separator + fileName);
        return uploadPath + File.separator + fileName;
    }
    public int create(Integer id_film,HttpServletRequest request) throws SQLException, IOException, ServletException {
        PreparedStatement statement = connection.prepareStatement(SQLImage.INSERT.QUERY) ;
        statement.setInt(1,id_film);
        statement.setString(2,saveImage(request));
        statement.execute();
        ResultSet id_image= statement.getResultSet();
        int id=123;
        if (id_image.next()) {
            id = id_image.getInt(1);
        }
        return id;
    }
    enum SQLImage {
        GET_ALL("SELECT * FROM film"),
        GET_BY_ID("SELECT * FROM images WHERE id=?"),
        INSERT("INSERT INTO images VALUES (DEFAULT, (?), (?)) RETURNING id"),
        DELETE("DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?) RETURNING id"),
        UPDATE("UPDATE users SET password = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        SQLImage(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
