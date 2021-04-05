package listeners;

// обычный POJO - класс как структура данных
public class EmployeeDTO {
    String name;

    public EmployeeDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
