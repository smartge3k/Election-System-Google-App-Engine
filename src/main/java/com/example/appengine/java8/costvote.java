package com.example.appengine.java8;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import Entities.candidate;

public class costvote extends HttpServlet{
	private static final long serialVersionUID = 1L;
	final Logger log = Logger.getLogger(createelection.class.getName());

	public costvote() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("Election", 1); //to check if election have been added
		Entity elect = null;
		try {
			Transaction txn = datastore.beginTransaction();
			elect = datastore.get(txn, key);
			txn.commit();
		} catch (EntityNotFoundException e1) {
			e1.printStackTrace();
		}
		if (elect != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime startTime = ((Date) elect.getProperty("StartTime")).toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			LocalDateTime endTime = ((Date) elect.getProperty("EndTime")).toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			if (startTime.isBefore(now) && endTime.isAfter(now)) //if the election time has started then  this link will be available
			{
				log.info("Election has started!");
				//Show Candidates for the Voting
				List<candidate> div = new ArrayList<>();
				//candidate temp = new candidate();
				Query q = new Query("Candidate");
				PreparedQuery pq = datastore.prepare(q);
				for (Entity result : pq.asIterable()) {
					candidate temp = new candidate();
					temp.setFirstname((String) result.getProperty("firstName"));
					temp.setSurname((String) result.getProperty("lastName"));
					temp.setFaculty((String) result.getProperty("faculty"));
					long a = result.getKey().getId();
					temp.setID(a);
					div.add(temp);
				}
				request.setAttribute("candidatelist", div);
				request.getRequestDispatcher("/costvote.jsp").forward(request, response);
			} else {
				log.info("No election at this Time");
				request.getRequestDispatcher("/novoting.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/novoting.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		doGet(request, response);
	}
}
