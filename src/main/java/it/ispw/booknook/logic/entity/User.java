package it.ispw.booknook.logic.entity;


//Singleton
public class User {

    private static User user = null;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private UserType type;

    private User() {}

    public static User getUser() {
        if (user == null)
            User.user = new User();
        return user;
    }

    public void setLogDetails(String username, String email, String password,UserType type) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setType(type);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public static boolean isLogged() {
        return user != null;
    }
}
