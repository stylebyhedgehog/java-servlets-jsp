package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements DAO {

    private final Connection connection;
    public FilmDAO(final Connection connection) {
        this.connection = connection;
    }

    public Film readOne(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLFilm.GET_ONE_BY_ID.QUERY) ;
        statement.setInt(1, id);
        ResultSet resultSet= statement.executeQuery();
        Film film = null;
        if (resultSet.next()){
           film=new Film(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"));
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
                    resultSet.getString("description")));
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
                    resultSet.getString("description")));
        }
        return films;
    }

    public boolean create(Object model) {
        return false;
    }

    public boolean update(Object model) {
        return false;
    }

    public boolean delete(Object model) {
        return false;
    }

    enum SQLFilm {
        GET_ALL("SELECT * FROM film"),
        GET_BY_QUANTITY("SELECT * FROM film WHERE id<=?"),
        GET_ONE_BY_ID("SELECT * FROM Film WHERE id=?"),
        INSERT("INSERT INTO film  VALUES (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM film WHERE id = (?)  RETURNING id"),
        UPDATE("UPDATE film SET title = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        SQLFilm(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}