package servlet;

import model.User;
import service.UserService;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


@WebServlet("/add/*")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("isAdd", true);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        if (login.isEmpty() && password.isEmpty() && name.isEmpty()) {
            req.getSession().setAttribute("isAdd", false);
        } else {
            UserService.getInstance().addUser(
                    new User(
                            login,
                            password,
                            name
                    )
            );
//            req.getSession().setAttribute("users", UserService.getInstance().getAllUsers());
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
