package model;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private String name;

    public User() {
    }

    public User(Long id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name);
    }


 /*   public String toString() {
        return "Login: " + login + '\n' +
                "Password: " + password + '\n' +
                "Name: " + name;
    }*/
}
