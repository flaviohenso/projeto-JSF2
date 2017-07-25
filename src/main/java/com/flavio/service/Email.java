package com.flavio.service;

import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@com.flavio.anotation.Email @RequestScoped
public class Email implements Mensagem {

	@Override
	public void enviar() {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("flavio.henso@gmail.com", "18deoutubro2014");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("flavio.henso@gmail.com")); // Remetente

			Address[] toUser = InternetAddress // Destinatário(s)
					.parse("flavio.henso@gmail.com, flavio.henrique@norteng.com.br");

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Enviando email com JavaMail");// Assunto
			message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}	
}
