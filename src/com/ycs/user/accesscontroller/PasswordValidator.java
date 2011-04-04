package com.ycs.user.accesscontroller;

public class PasswordValidator {
	public boolean isValidUser(String user, String password){
				if(user.equals(password))return true;
				return false;
	}
}
