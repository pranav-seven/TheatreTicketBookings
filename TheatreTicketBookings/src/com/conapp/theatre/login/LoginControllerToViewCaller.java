package com.conapp.theatre.login;

import com.conapp.theatre.dto.User;

public interface LoginControllerToViewCaller {
	void signupSuccessful();
	void loginSuccess(User user, char type);
	void loginFailure(char type);
}
