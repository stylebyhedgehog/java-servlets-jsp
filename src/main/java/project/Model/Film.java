package project.Model;

import javax.servlet.http.HttpServletRequest;

public class Film  {
    private Integer id;
    private String title;
    private String description;
    private String image_path;
    public Film(Integer id, String title, String description, String image_path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image_path=image_path;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
