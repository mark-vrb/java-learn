package org.markvarabyou.servlets;

import com.google.gson.Gson;
import org.markvarabyou.entities.User;
import org.markvarabyou.servlets.utilities.DaoFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = getIdFromRequest(request);
            DaoFactory daoFactory = new DaoFactory();
            if (id != null) {
                User user = daoFactory.getSqlUserDao().read(id);
                if (user == null) {
                    response.setStatus(404);
                    return;
                }
                sendJsonResult(response, user);
            } else {
                ArrayList<User> users = daoFactory.getSqlUserDao().read();
                sendJsonResult(response, users);
            }
        } catch (IOException e) {
            response.setStatus(500);
            e.printStackTrace();
        } catch (SQLException e) {
            response.setStatus(500);
            e.printStackTrace();
        }
    }

    private void sendJsonResult(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(object));
    }

    private Integer getIdFromRequest(HttpServletRequest request){
        String paramString = request.getParameter("id");
        Integer id = Integer.getInteger(paramString);
        return id;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response){

    }
}