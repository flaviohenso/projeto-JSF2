package com.flavio.service;

import javax.enterprise.context.RequestScoped;

@com.flavio.anotation.Sms @RequestScoped
public class Sms implements Mensagem {

	@Override
	public void enviar() {
		System.out.println("Enviando por SMS!");

	}

}
