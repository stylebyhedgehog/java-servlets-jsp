package project.Repository;



import project.Model.Film;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmDAO {

    private final Connection connection;
    public FilmDAO() throws SQLException, ClassNotFoundException {
        this.connection = DbConnector.getConnection();
    }

    public Film readOne(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.GET_ONE_BY_ID.QUERY) ;
        statement.setInt(1, id);
        ResultSet resultSet= statement.executeQuery();
        Film film = null;
        if (resultSet.next()){
            film=new Film(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("image_path"));
        }
        return film;
    }
    public ArrayList<Film> readByQuantity(Integer quantity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.GET_BY_QUANTITY.QUERY) ;
        statement.setInt(1, quantity);
        ResultSet resultSet= statement.executeQuery();
        ArrayList<Film> films=new ArrayList<Film>();
        while (resultSet.next()){
            films.add(new Film(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("image_path")));
        }
        return films;
    }
    public ArrayList<Film> readAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.GET_ALL.QUERY) ;
        ResultSet resultSet= statement.executeQuery();
        ArrayList<Film> films=new ArrayList<Film>();
        while (resultSet.next()){
            films.add(new Film(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("image_path")));
        }
        return films;
    }

    public int create(Film film) throws SQLException, IOException, ServletException, ClassNotFoundException {
      //insert
        PreparedStatement statement = connection.prepareStatement(SQLFilm.INSERT.QUERY) ;
        statement.setString(1,film.getTitle());
        statement.setString(2,film.getDescription());
        statement.setString(3, film.getImage_path());
        statement.execute();

        int id_film=0;
        ResultSet id_film_set = statement.getResultSet();

        if(id_film_set.next()) {
            id_film=id_film_set.getInt(1);

        }
        return id_film;
    }

    public boolean update(Film film) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.UPDATE.QUERY) ;
        statement.setString(1,film.getTitle());
        statement.setString(2,film.getDescription());
        statement.setInt(3,film.getId());
        statement.execute();
        return true;
    }

    public boolean delete(Film film)throws SQLException  {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.DELETE.QUERY) ;
        statement.setInt(1,film.getId());
        statement.execute();
        return true;
    }

    enum SQLFilm {
        GET_ONE_BY_ID("SELECT * FROM film WHERE id=?"),
        GET_ALL("SELECT * FROM film"),
        GET_BY_QUANTITY("SELECT * FROM film WHERE id<=?"),
        INSERT("INSERT INTO film VALUES (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM film WHERE id = (?) "),
        UPDATE("UPDATE film SET title = (?), description= (?)  WHERE id = (?)");

        String QUERY;

        SQLFilm(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}