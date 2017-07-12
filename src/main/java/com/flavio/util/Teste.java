package com.flavio.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;

public class Teste {

	private static final String GMAIL_USER = "eng.nor2@gmail.com";// "eng.norteng@gmail.com";
	private static final String GMAIL_PWD = "xxxxx";

	public Teste() throws AddressException {
		String[] emailList = {"null","flavio.henso@gmail.com", "flavio.henrique@norteng.com.br", "<null>", "null"};
		
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(GMAIL_USER, GMAIL_PWD);
			}
		});

		session.setDebug(true);
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("eng.nor2@gmail.com"));

			// Setando o endereco
			// message.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse("rodrigo.medeiros@norteng.com.br"));
			InternetAddress addressFrom = new InternetAddress("alerta@norteng.com.br");
			message.setFrom(addressFrom);

//			Address[] toUser = InternetAddress // Destinatário(s)
//					.parse("flavio.henso@gmail.com, rodrigo.medeiros@norteng.com.br, flavio.henrique@norteng.com.br, <null>, null");
			
			System.out.println(emailList.length);
			message.setRecipients(Message.RecipientType.TO, preencherDestinatario(emailList));

			// prioridade
			// msg.setHeader("X-Priority","1");
			// msg.setHeader("Priority","Urgent");
			// msg.setHeader("Importance","high");

			// Conteudo
			message.setSubject("Enviando email com JavaMail");// Assunto
			message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");

			//Multipart corpo = new MimeMultipart();
			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-Type", "application/pdf");

			// corpo do e-mail
			// MimeBodyPart texto = new MimeBodyPart();
			// texto.setContent(message, "text/html; charset=ISO-8859-1");
			// corpo.addBodyPart(texto);
			// message.setText(mensagem);

			/*
			 * try { //anexo if (arquivo!=null) { MimeBodyPart anexo = new
			 * MimeBodyPart(); anexo.setDataHandler( new DataHandler( new
			 * ByteArrayDataSource(ArquivoUtil.getBytesFromFile(arquivo) ,
			 * "aplication/xls"))); anexo.setFileName(arquivo.getName());
			 * corpo.addBodyPart(anexo); } } catch (Exception e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			Transport.send(message);

			// System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
		
	public InternetAddress[] preencherDestinatario(String recipients[]) throws AddressException{
		
		Collection<String> emails = new ArrayList<String>(Arrays.asList(recipients));
		
		for (Iterator<String> iterator = emails.iterator(); iterator.hasNext();) {
			String email = (String) iterator.next();
			if(email == null || email.contains("null")){
				iterator.remove();
			}
		}
		
		InternetAddress[] addressTo = new InternetAddress[emails.size()];
		for (int i = 0; i < emails.toArray().length; i++) {
			addressTo[i] = new InternetAddress((String) emails.toArray()[i]);
		}
		
		return addressTo;
    }
	
	public static void main(String[] args) {
		try {
			new Teste();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

}
