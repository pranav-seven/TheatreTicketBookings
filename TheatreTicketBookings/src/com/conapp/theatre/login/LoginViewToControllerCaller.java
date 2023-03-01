package com.conapp.theatre.login;

interface LoginViewToControllerCaller {
    void checkLoginDetailsOnline(char type, String username, String password);
    boolean isEmailidValid(String emailid);
    boolean isUsernameValid(String username);
    boolean isPasswordValid(String password);
    boolean checkUsername(String username);
    void addUser(String name, String emailid, String username, String password);
    boolean checkNewPassword(String password, String rePassword);
}
