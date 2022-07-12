package com.example.course_project_5.models;

import com.example.course_project_5.helpers.PasswordHelper;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class User {
    private int id;
    private String login;
    private String password;
    private UserStatus status;
    private int level;

    public User(int id,
                String login,
                String password,
                UserStatus status,
                int level
    ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
        this.level = level;
    }
    public User(User old, String login) {
        this.id = old.getId();
        this.login = login;
        this.password = old.getPassword();
        this.status = old.getStatus();
        this.level = old.getLevel();
    }
    public User(User old, String password, Boolean encrypteed) {
        this.id = old.getId();
        this.login = old.getLogin();
        if (encrypteed) this.password = password;
        else {
            try {
                this.password = PasswordHelper.generateStorngPasswordHash(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        this.status = old.getStatus();
        this.level = old.getLevel();
    }
    public User(User old, Integer groupId) {
        this.id = old.getId();
        this.login = old.getLogin();
        this.password = old.getPassword();
        this.status = old.getStatus();
        this.level = groupId;
    }
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public int getLevel() {
        return level;
    }
    public enum UserStatus {
        Basic,
        Super
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", level=" + level +
                '}';
    }
}
