package project;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;

public class Wrapper extends ServletRequestWrapper {
    public Wrapper(ServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return "Номер Фильма: "+ super.getParameter(name);
    }




}
