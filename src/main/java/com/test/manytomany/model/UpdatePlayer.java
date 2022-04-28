package com.test.manytomany.model;

public class UpdatePlayer {

    private String login;
    private String password;
    private String newpassword;
    private String repeatnewpassword;

    public String getRepeatnewpassword() {
        return repeatnewpassword;
    }

    public void setRepeatnewpassword(String repeatnewpassword) {
        this.repeatnewpassword = repeatnewpassword;
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

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
