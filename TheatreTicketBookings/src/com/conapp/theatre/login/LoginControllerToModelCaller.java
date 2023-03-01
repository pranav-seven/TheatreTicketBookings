package com.conapp.theatre.login;

import java.util.List;
import com.conapp.theatre.dto.User;
import com.conapp.theatre.dto.Admin;

public interface LoginControllerToModelCaller {
    boolean checkUsername(String username);
    void addUser(String name, String emailid, String username, String password);
	List<Admin> getAdminsList();
    List<User> getUsersList();
}
