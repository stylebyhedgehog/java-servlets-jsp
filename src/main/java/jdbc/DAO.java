package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<Entity, Key> {
    boolean create(Entity model);
    ArrayList<Entity> readByQuantity(Integer quantity) throws SQLException;
    ArrayList<Entity>  readAll() throws SQLException;
    boolean update(Entity model);
    boolean delete(Entity model);
}