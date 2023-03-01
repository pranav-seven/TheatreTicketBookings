package com.conapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractLoginView {
	public BufferedReader reader;;
    protected String name;
    protected String username;
    protected String emailid;
    protected String password;
	
	protected AbstractLoginView()
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
    protected void startLogin()
    {
        char choice = 0;
        do{
            System.out.println("----------------------");
            System.out.println("Enter ");
            System.out.println("1 - User Login");
            // System.out.println("2 - Admin Login");
            System.out.println("2 - Sign up");
            System.out.println("0 - exit");
            try{
            	choice = reader.readLine().charAt(0);
	            switch(choice)
	            {
	                case '1': login('u');
	                        break;
	                // case '2': login('a');
	                // 		break;
	                case '2': signup();
	                        break;
	                case '0': System.out.println("Thanks for using our site!");
	                        break;
	                default: System.out.println("Invalid choice, try again.");
	            }
            }catch(IOException ioe)
            {}
        }while(choice!='0');
    }
    
    abstract protected void login(char type);
    abstract protected void signup();

    protected String getEmailId()
    {
        System.out.print("Enter email id: ");
        try{
            return reader.readLine();
        }catch(IOException ioe)
        {}
        return null;
    }

    protected String getUsername()
    {
        System.out.print("Enter username: ");
        try{
            return reader.readLine();
        }catch(IOException ioe)
        {}
        return null;
    }

    
    protected String getPassword()
    {
        System.out.print("Enter password: ");
        try{
            return reader.readLine();
        }catch(IOException ioe)
        {}
        return null;
    }
    
    public void signupSuccessful()
    {
        System.out.println("Added successfully!");
    }
}
