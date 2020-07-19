package com.example.appengine.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class importvoters extends HttpServlet{
	private static final long serialVersionUID = 1L;
	final Logger log = Logger.getLogger(importvoters.class.getName());

	public importvoters() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/importvoters.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		// check if election exists
		Key key = KeyFactory.createKey("Election", 1);
		Entity elect = null;
		try {
			elect = datastore.get(key);
		} catch (EntityNotFoundException e1) {
			e1.printStackTrace();
		}
		if (elect != null) {
			LocalDateTime startTime = ((Date) elect.getProperty("StartTime")).toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			String tempemail = "";
			String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			Part getfile = request.getPart("Upload");
			InputStream getemails = getfile.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(getemails));
			if (getemails != null) {
				while ((tempemail = reader.readLine()) != null) {
					if (tempemail.matches(regex)) {
						long generatedLong = new Random().nextLong(); //creaete Random Key for the Each User
						sendEmail(tempemail, String.valueOf(generatedLong), startTime.toString()); //send email to the voter
						Entity Voters = new Entity("Voters", generatedLong);
						Voters.setProperty("Email", tempemail);
						Voters.setProperty("checkVoted", 0);
						Transaction txn = datastore.beginTransaction();
						datastore.put(txn, Voters);
						txn.commit();
						if (txn.isActive()) { //if trnasaction is still active (which means not committed properly then it rollbacks)
							txn.rollback();
						}
					} else {
						continue;
					}
				}
				request.getRequestDispatcher("/EmailsImported.jsp").forward(request, response);
			} else {
				String error = "File is either empty or Wrong File selected.";
				request.setAttribute("errormessage", error);
				request.getRequestDispatcher("/importvoters.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/notImportedEmailMessage.jsp").forward(request, response);
		}
	}

	protected void sendEmail(String Email, String key, String startTime) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("m.usman5991@gmail.com", "Election Management Committee"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(Email, "Dear Voter"));
			msg.setSubject("Love");
			msg.setText(
			      "Hello, \n This email is to remind that 24 hours are left in the "
			            + "Election process. Pleaes visit the following below given link containing your token after Date: "
			            + startTime + "\n" + "Link : " + "http://usman-264309.appspot.com/costvote?token=" + key
			            + "\n Thank you"
			);
			Transport.send(msg);
		} catch (AddressException e) {
			// ...
		} catch (MessagingException e) {
			// ...
		} catch (UnsupportedEncodingException e) {
			// ...
		}
	}
}
