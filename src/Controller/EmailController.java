package Controller;

/*******************************************************************************************
 * Project: ReUnite
 *
 * Class: EmailController
 *
 * Author: Adeel M. Ateeque (112013Z)
 * 
 * Lecturer: Ms Lim Ai Hua
 * 
 * Purpose:  A controller class to send out email, both with and without attachment along
 * with plain-text/html content
 *     
 * Usage: Simply initialize an object of the class, and call the sendEmail method with the 
 * appropriate parameters
 * 
 * Honor Code: I pledge that this program represents my own program code. 
 * I received help from no one in designing, coding and debugging my program.
 *******************************************************************************************/
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailController
{
	private static MySQLController db = new MySQLController();

	public void sendEmail(String TEXTorHTML, String[] recipient, String subject, String content, File attachment, int eventID, String notificationType)
			throws Exception
	{
		String from = "reunite_sg@hotmail.com";
		String[] to = recipient;

		// Get system properties
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.host", "smtp.live.com");
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, null);
		System.out.println(session.getProperties());

		Message message = new MimeMessage(Session.getDefaultInstance(props, new SMTPAuthenticator()));
		message.setFrom(new InternetAddress(from));
		InternetAddress[] toAddress = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++)
		{
			toAddress[i] = new InternetAddress(to[i]);
		}
		message.setRecipients(Message.RecipientType.TO, toAddress);
		message.setSubject(subject);

		BodyPart messageBodyPart = new MimeBodyPart();

		if (TEXTorHTML.equalsIgnoreCase("text"))
		{
			messageBodyPart.setText(content);
		}
		else
		{
			messageBodyPart.setContent(content, "text/html");
		}

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		if (attachment != null)
		{
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachment.getAbsoluteFile());
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachment.getName());
			multipart.addBodyPart(messageBodyPart);
		}

		message.setContent(multipart);

		try
		{
			Transport.send(message);
			System.out.println("Email sent successfully to: ");
		}
		catch (SendFailedException sfe)
		{
			// Try to re-send the email to those it failed to be sent to at
			// first.
			Address[] failedRecipients = sfe.getValidUnsentAddresses();
			System.out.println("Failed to send Email to: ");
			for (int i = 0; i < failedRecipients.length; i++)
			{
				System.out.println(recipient[i]);
			}
			System.out.println("Retrying...");
			message.setRecipients(Message.RecipientType.TO, failedRecipients);
			Transport.send(message);
		}
		catch (Exception e)
		{
			System.out.println("Failed to send email");
			e.printStackTrace();
		}
		finally
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			Calendar cal = Calendar.getInstance();
			String date=dateFormat.format(cal.getTime());

			for (int i = 0; i < recipient.length; i++)
			{
				db.updateRequest("INSERT INTO Notification (sender, type, dateSent, recipient, message) VALUES ("+"'" + from +"'"+ ", " +"'"+ notificationType + "'"+", "
						+"'"+ date + "'"+", " +"'"+ recipient[i] + "'"+", " +"'"+ content + "'"+")");
				System.out.println(recipient[i]);
			}
		}
	}
}

class SMTPAuthenticator extends Authenticator
{
	public SMTPAuthenticator()
	{
		super();
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication()
	{
		String username = "reunite_sg@hotmail.com";
		String password = "Ameen123";
		if ((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0))
		{

			return new PasswordAuthentication(username, password);
		}

		return null;
	}
}
