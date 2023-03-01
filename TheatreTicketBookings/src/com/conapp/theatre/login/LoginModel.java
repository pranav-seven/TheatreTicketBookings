package com.conapp.theatre.login;

import com.conapp.theatre.dto.User;
import com.conapp.theatre.dto.Admin;
import com.conapp.theatre.repository.TheatreRepository;

import java.util.List;

public class LoginModel implements LoginControllerToModelCaller {
	private LoginModelToControllerCaller loginController;
	private TheatreRepository repo;

	LoginModel(LoginController controller)
	{
		loginController = controller;
		repo = TheatreRepository.getInstance();
	}

	public boolean checkUsername(String username)
	{
		List<User> list = repo.getUsers();
		for(User user : list)
		{
			if(user.getUsername().equals(username))
				return false;
		}
		return true;
	}

    public void addUser(String name, String emailid, String username, String password)
	{
		repo.getUsers().add(new User(name, emailid, username, password));
		loginController.signupSuccessful();
	}

    public List<Admin> getAdminsList()
    {
        return repo.getAdmins();
    }

    public List<User> getUsersList()
    {
        return repo.getUsers();
    }
}
