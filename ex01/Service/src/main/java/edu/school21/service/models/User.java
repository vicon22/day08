package edu.school21.service.models;

public class User {

    private long identifier;
    private String email;

    public User(long identifier, String email) {
        this.identifier = identifier;
        this.email = email;
    }

    public User() {
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", email='" + email + '\'' +
                '}';
    }
}
