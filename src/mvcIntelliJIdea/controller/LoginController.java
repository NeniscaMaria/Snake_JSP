package mvcIntelliJIdea.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcIntelliJIdea.model.DBManager;
import mvcIntelliJIdea.model.User;



public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        DBManager DBManager = new DBManager();
        String result = DBManager.authenticate(username, password);
        if (result.equals("success")) {
            rd = request.getRequestDispatcher("/snake.jsp");
            User user = new User(username, password);
            request.setAttribute("username", user);
            HttpSession session = request.getSession();
            session.setAttribute("username", user);
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }

}