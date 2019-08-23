package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) throws SQLException {
        this.connection = connection;
        createTable();
    }

    private int execUpdate(String update, Object... parameters) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(update)) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
            return stmt.executeUpdate();
        }
    }

    @FunctionalInterface
    private interface FunctionR<T, R> {
        R apply(T t) throws SQLException;
    }

    private <T> T execQuery(String query, FunctionR<ResultSet, T> f, Object... parameters) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
            return f.apply(stmt.executeQuery());
        }
    }

    public void addUser(User user) throws SQLException {
        execUpdate(
                "insert into users (login, password, name) values (?, ?, ?)",
                user.getLogin(),
                user.getPassword(),
                user.getName()
        );
    }

    public void deleteUserById(Long id) throws SQLException {
        execUpdate("delete from users where id=?",
                id
        );
    }

    public void deleteAllUsers() throws SQLException {
        execUpdate("delete from users");
    }

    public void updateUser(User user) throws SQLException {
        execUpdate("update users set login=?, password=?, name=? where id=?",
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getId()
        );
    }

    public List<User> getAllUsers() throws SQLException {
        return execQuery(
                "select * from users",
                x -> {
                    ArrayList<User> list = new ArrayList<>();
                    while (x.next()) {
                        list.add(
                                new User(
                                        x.getLong("id"),
                                        x.getString("login"),
                                        x.getString("password"),
                                        x.getString("name")
                                        ));
                    }
                    return list;
                }
        );
    }

    public void createTable() throws SQLException {
        execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), name varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        execUpdate("DROP TABLE IF EXISTS users");
    }
}
