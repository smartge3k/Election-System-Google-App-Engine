package com.example.appengine.java8;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import Entities.Election;

public class ReminderEmail extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public ReminderEmail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Logger log = Logger.getLogger(createelection.class.getName());
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("Election", 1); //to check if election have been added
		Entity elect = null;
		try {
			Transaction txn = datastore.beginTransaction();
			elect = datastore.get(txn, key);
			txn.commit();
		} catch (EntityNotFoundException e1) {
			request.getRequestDispatcher("/reminderfailed.jsp").forward(request, response);
			e1.printStackTrace();
		}
		if (elect != null) { //check if election exists then take action otherwise do not do anything
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime startTime = ((Date) elect.getProperty("StartTime")).toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
		
			if (now.isAfter(startTime)) //check if 24 (1440 minutes) hours are left then shoot an email to everybody
			{
				log.info("Time OK");
				//Code to shoot email to all voters to inform that 24 hours are left
				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);
				Query eq = new Query("Voters");
				List<Entity> results = datastore.prepare(eq).asList(FetchOptions.Builder.withDefaults());
				if (results != null) {
					for (Entity result : results) {
						try {
							Message msg = new MimeMessage(session);
							msg.setFrom(new InternetAddress("m.usman5991@gmail.com", "Election Management Committee"));
							msg.addRecipient(
							      Message.RecipientType.TO, new InternetAddress((String) result.getProperty("Email"), "Dear Voter")
							);
							msg.setSubject("Love");
							msg.setText(
							      "Hello, \n This email is to remind that 24 hours are left in the "
							            + "Election process. Pleaes visit the following below given link containing your token after Date: "
							            + startTime.toString() + "\n" + "Link : "
							            + "http://usman-264309.appspot.com/costvote?token=" + (long) result.getKey().getId()
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
						request.getRequestDispatcher("/remindersent.jsp").forward(request, response);
					}
				} else {
					request.getRequestDispatcher("/reminderfailed.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("/reminderfailed.jsp").forward(request, response);
			}
		}
		request.getRequestDispatcher("/admin/dashboard").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		doGet(request, response);
	}
}
