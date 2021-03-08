package project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDAO {

    private final Connection connection;
    public FilmDAO(final Connection connection) {
        this.connection = connection;
    }

    public ResultSet readOneById(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.GET_ONE.QUERY) ;
        statement.setInt(1, id);
        return statement.executeQuery();
    }
    public ResultSet readByQuantity(Integer quantity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.GET_BY_QUANTITY.QUERY) ;
        statement.setInt(1, quantity);
        return statement.executeQuery();
    }

    public ResultSet readAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.GET_ALL.QUERY) ;
        return statement.executeQuery();
    }

    public int create(HttpServletRequest request) throws SQLException, IOException, ServletException {
      //insert
        PreparedStatement statement = connection.prepareStatement(SQLFilm.INSERT.QUERY) ;
        statement.setString(1,request.getParameter("title"));
        statement.setString(2,request.getParameter("description"));
        statement.setInt(3,0);
        statement.execute();

        int id_film=0;
        ResultSet id_film_set = statement.getResultSet();

        //update переместить в метод
        if(id_film_set.next()) {
            id_film=id_film_set.getInt(1);
            ImageDao imageDao=new ImageDao(connection);
            int id_image=imageDao.create(id_film,request);
            PreparedStatement statement1 = connection.prepareStatement(SQLFilm.UPDATE.QUERY);
            statement1.setInt(1,id_image);
            statement1.setInt(2,id_film);
            statement1.execute();
        }
        return id_film;
    }

    public boolean update(Object model) {
        return false;
    }

    public boolean delete(Object model) {
        return false;
    }

    enum SQLFilm {
        GET_ONE("SELECT * FROM film WHERE id=?"),
        GET_ALL("SELECT * FROM film"),
        GET_BY_QUANTITY("SELECT * FROM film WHERE id<=?"),
        INSERT("INSERT INTO film VALUES (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?) RETURNING id"),
        UPDATE("UPDATE film SET images = (?) WHERE id = (?)");

        String QUERY;

        SQLFilm(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}