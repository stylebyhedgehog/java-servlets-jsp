package project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Parser {

    private  ResultSet resultSet;
    public Parser(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        this.resultSet =resultSet;
    }

    public  String toHtmlTable() throws SQLException {
        StringBuilder out= new StringBuilder("<table border=2 bgcolor=yellow>");
        while (resultSet.next()) {
            out.append("<tr> <td>")
                    .append(resultSet.getString("title"))
                    .append("</td>").append("<td>")
                    .append(resultSet.getString("description"))
                    .append("</td></tr>");
        }
        out.append("</table>");
        return out.toString();
    }

    public String toHtmlCombo() throws SQLException{
        StringBuilder s = new StringBuilder();
        s.append("<select>");

        while (resultSet.next()) {
            s.append("<option>");
            s.append(resultSet.getString("title"));
            s.append("</option>");
        }

        s.append("</select>");
        return s.toString();
    }

    public  ArrayList<Film> toObjectArrayList() throws SQLException {
        ArrayList<Film> arrayList =new ArrayList<Film>();
        Film film =new Film();
        while (resultSet.next()) {
            film.setId(Integer.parseInt(resultSet.getString("id")));
            film.setTitle(resultSet.getString("title"));
            film.setDescription(resultSet.getString("description"));
            arrayList.add(film);
        }
        return arrayList;
    }
}
