package controllers;

import dispatcher.ModelAndType;
import dispatcher.interfaces.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    @Override
    public ModelAndType handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndType("home");
    }
}