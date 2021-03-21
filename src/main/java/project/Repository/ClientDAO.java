package project.Repository;

import project.Model.Client;


import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {
    private Connection connection;

    public ClientDAO() throws SQLException, ClassNotFoundException {
        this.connection = DbConnector.getConnection();
    }

    public void create(Client client) throws SQLException, IOException, ServletException, ClassNotFoundException {
        //insert
        PreparedStatement statement = connection.prepareStatement(SQLClient.INSERT.QUERY) ;
        statement.setString(1,client.getLogin());
        statement.setString(2,client.getPassword());
        statement.setString(3,client.getRole());
        statement.execute();
    }
    public boolean userIsExist(String login, String password) throws SQLException {
        boolean exist=false;
        PreparedStatement statement = connection.prepareStatement(SQLClient.GET_ONE_BY_LOGIN_PASSWORD.QUERY) ;
        statement.setString(1, login);
        statement.setString(2,password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            exist=true;
        return exist;
    }
    public String getUserRole(String login, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQLClient.GET_ONE_BY_LOGIN_PASSWORD.QUERY) ;
        statement.setString(1, login);
        statement.setString(2,password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return resultSet.getString("role");
        return "null";
    }
    enum SQLClient {
        GET_ONE_BY_LOGIN_PASSWORD("SELECT * FROM client WHERE login=? and password=?"),
        GET_ONE_BY_ID("SELECT * FROM film WHERE id=?"),
        GET_ALL("SELECT * FROM film"),
        GET_BY_QUANTITY("SELECT * FROM film WHERE id<=?"),
        INSERT("INSERT INTO client VALUES (DEFAULT, (?), (?), (?))"),
        DELETE("DELETE FROM film WHERE id = (?) "),
        UPDATE("UPDATE film SET title = (?), description= (?)  WHERE id = (?)");

        String QUERY;

        SQLClient(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
