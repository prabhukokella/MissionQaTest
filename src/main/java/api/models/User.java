package api.models;

public class User {

    public String name;
    public String job;
    public String email;
    public String password;

    // Default constructor (REQUIRED for API frameworks)
    public User() {}

    // Optional helper constructor
    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }
}