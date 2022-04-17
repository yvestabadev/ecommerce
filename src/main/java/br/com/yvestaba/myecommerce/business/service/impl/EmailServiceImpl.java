package br.com.yvestaba.myecommerce.business.service.impl;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.yvestaba.myecommerce.business.service.EmailService;
import br.com.yvestaba.myecommerce.domain.Email;
import br.com.yvestaba.myecommerce.domain.EmailStatus;

@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;

	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(Email email) {
		try {
			mailSender.send(this.getSimpleMailMessage(email));
			email.setStatus(EmailStatus.SENT);
		} catch (MailException e) {
			email.setStatus(EmailStatus.ERROR);
		}
	}

	private SimpleMailMessage getSimpleMailMessage(Email email) {
		var simpleMail = new SimpleMailMessage();
		simpleMail.setFrom(email.getFrom());
		simpleMail.setTo(email.getTo());
		simpleMail.setSubject(email.getSubject());
		simpleMail.setText(email.getText());
		return simpleMail;
	}

}
