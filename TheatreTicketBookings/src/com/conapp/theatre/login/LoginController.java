package com.conapp.theatre.login;

import java.util.List;

import com.conapp.AbstractLoginController;
import com.conapp.theatre.dto.*;

public class LoginController extends AbstractLoginController implements LoginViewToControllerCaller, LoginModelToControllerCaller {
	private LoginControllerToViewCaller loginView;
	private LoginControllerToModelCaller loginModel;
    private User user;
	
	LoginController(LoginView view)
	{
		loginView = view;
		loginModel = new LoginModel(this);
	}

    //login

	public boolean checkUsername(String username)
	{
		return loginModel.checkUsername(username);
	}

    public void addUser(String name, String emailid, String username, String password)
	{
		loginModel.addUser(name, emailid, username, password);
	}

	public void signupSuccessful()
	{
		loginView.signupSuccessful();
	}

	public void checkLoginDetailsOnline(char type, String username, String password)
	{
        List<? extends User> list = null;
        if(type=='u')
            list = loginModel.getUsersList();
        else
            list = loginModel.getAdminsList();
        boolean isValid = false;
        for(User user : list)
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                this.user = user;
                isValid = true;
                break;
            }
        if(isValid)
            loginView.loginSuccess(user, type);
        else
            loginView.loginFailure(type);
        
	}

	public boolean checkNewPassword(String password, String rePassword)
	{
		return password.equalsIgnoreCase(rePassword);
	}

    public boolean isEmailidValid(String emailid)
    {
        return AbstractLoginController.isEmailIdValid(emailid);
    }

    public boolean isUsernameValid(String username)
    {
        return AbstractLoginController.isusernameValid(username);
    }

    public boolean isPasswordValid(String password)
    {
        return AbstractLoginController.ispasswordValid(password);
    }

}
