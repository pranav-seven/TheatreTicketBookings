package com.conapp.theatre.login;

import com.conapp.theatre.booking.BookingView;

import java.io.IOException;

import com.conapp.AbstractLoginView;
import com.conapp.theatre.dto.*;
public class LoginView extends AbstractLoginView implements LoginControllerToViewCaller{
	
    private LoginViewToControllerCaller loginController;

    LoginView()
    {
    	super();
        System.out.println("*** THIRAI THEE PIDIKKUM CINEMAS ***");
        loginController = new LoginController(this);
    }

    public static void main(String[] args)
    {
        LoginView loginView = new LoginView();
        loginView.startLogin();
    }

// super call happens    
//    protected void startLogin()
//    {
//        super.startLogin();
//    }

//Login *******************************
    @Override
    protected void login(char type)
    {
        username = super.getUsername();
        password = super.getPassword();
        loginController.checkLoginDetailsOnline(type, username, password);
    }

    public void loginSuccess(User user, char type)
    {
            System.out.println("--- Welcome "+user.getName()+" ---");
            if(type=='u')
                new BookingView(user, reader);
    }

    public void loginFailure(char type)
    {
        System.out.println("Username or password invalid!\nTry again");
        login(type);
    }
    
    //Signup *******************************
    @Override
    protected void signup()
    {
        emailid = getEmailId();
        username = getUsername();
        if(loginController.checkUsername(username))
        {
            try{
                System.out.print("Name: ");
                name = reader.readLine();
                do{
                    password = getPassword();
                    System.out.println("Re-enter password: ");
                    String rePassword = reader.readLine();
                    if(loginController.checkNewPassword(password, rePassword))
                    {
                        loginController.addUser(name, emailid, username, password);
                        break;
                    }
                    else
                        System.out.println("Passwords do not match");
                }while(true);
            }catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
        else
            System.out.println("Username already exists.");
    }

    //    super call happens
    //    public void signupSuccessful()
    //    {
    //        System.out.println("Added successfully!");
    //    }

    //input methods *****************
    protected String getEmailId()
    {
       String emailid = super.getEmailId();
       if(!loginController.isEmailidValid(emailid))
       {
            System.out.println("Enter a valid email id!");
            emailid = getEmailId();
       }
       return emailid;
    }
    
    protected String getUsername()
    {
       String username = super.getUsername();
       if(!loginController.isUsernameValid(username))
       {
            System.out.println("Enter a valid username!");
            System.out.println("(username must contain only letters (atleast one), numbers, dots or underscores)");
            username = getUsername();
       }
       return username;
    }

    protected String getPassword()
    {
        String password = super.getPassword();
        if(!loginController.isPasswordValid(password))
       {
            System.out.println("Enter a valid password!");
            System.out.println("(password must contain atleast eight characters, one uppercase letter, one lowercase letter and one digit)");
            password = getPassword();
       }
       return password;
    }


}
