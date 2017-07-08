package com.flavio.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;

	public String logout(){
		System.out.println("logout");
		return "acesso";
	}
	
	public String login(){
		System.out.println("login");
		return "sucesso";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
