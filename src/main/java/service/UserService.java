package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static UserService instance;
    private static UserDAO dao;

    private UserService() {
        try {
            dao = new UserDAO(ConnectionHelper.getMySQLConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    public boolean addUser(User user) {
        try {
            dao.addUser(user);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteUser(Long id) {
        try {
            dao.deleteUserById(id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void deleteAllUsers() {
        try {
            dao.deleteAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(User user) {
        try {
            dao.updateUser(user);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<User> getAllUsers() {
        try {
            return dao.getAllUsers();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}
