package mvcIntelliJIdea.controller;

import mvcIntelliJIdea.model.DBManager;
import mvcIntelliJIdea.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SnakeController extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        int move = Integer.parseInt(request.getParameter("move"));
        RequestDispatcher rd = null;
        DBManager DBManager = new DBManager();
        DBManager.addMoveToUser((User)request.getSession().getAttribute("username"),move);
    }
}
