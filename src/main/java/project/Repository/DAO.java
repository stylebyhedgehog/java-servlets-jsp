package project.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO<Entity, Key> {
    boolean create(Entity model) throws SQLException;
    ResultSet readByQuantity(Integer quantity) throws SQLException;
    ResultSet readAll() throws SQLException;
    boolean update(Entity model);
    boolean delete(Entity model);
}