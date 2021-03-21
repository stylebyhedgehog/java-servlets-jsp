package project;


import project.Model.Film;

import java.util.ArrayList;

public class Parser {

    private  ArrayList<Film> films;
    public Parser(ArrayList<Film> films) {
        this.films =films;
    }

    public  String toHtmlTable()  {
        StringBuilder out= new StringBuilder("<table border=2");
        for (Film film : films){
            out.append("<tr> <td>")
                    .append("<a href=http://localhost:8081/app/film?id="+film.getId().toString()+">"+film.getTitle()+"</a>")
                    .append("</td>").append("<td>")
                    .append(film.getDescription())
                    .append("</td></tr>");
        }
        out.append("</table>");
        return out.toString();
    }

    public String toHtmlCombo(){
        StringBuilder out = new StringBuilder();
        out.append("<select>");
        for (Film film : films){
            out.append("<tr> <td>")
                    .append("<option>")
                    .append(film.getTitle())
                    .append("</option>");
        }
        out.append("</select>");
        return out.toString();
    }

    public  String toJson() {

        return "";
    }
}
