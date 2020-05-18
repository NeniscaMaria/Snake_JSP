package mvcIntelliJIdea.controller;
import mvcIntelliJIdea.model.DBManager;
import mvcIntelliJIdea.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        DBManager dbManager = new DBManager();
        dbManager.logTimeEnd((User)request.getSession().getAttribute("username"));
        request.getSession().invalidate();
        response.sendRedirect("/login.jsp");
    }
}
