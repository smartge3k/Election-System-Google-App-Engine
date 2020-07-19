package com.example.appengine.java8;

import java.io.IOException;
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
import com.google.appengine.api.datastore.Transaction;

public class assignvote extends HttpServlet{
	private static final long serialVersionUID = 1L;
	final Logger log = Logger.getLogger(createelection.class.getName());

	public assignvote() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOfCandidate = (String) request.getParameter("cand_id");
		String Votertoken = (String) request.getParameter("token");
		Key CandKey = KeyFactory.createKey("Candidate", Long.parseLong(idOfCandidate));
		Key voterKey = KeyFactory.createKey("Voters", Long.parseLong(Votertoken));
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity Cand = null;
		Entity Voter = null;
		try {
			Voter = datastore.get(voterKey);
			Cand = datastore.get(CandKey);
		} catch (EntityNotFoundException e) {
			request.getRequestDispatcher("/errorcostingvote.jsp").forward(request, response);
			e.printStackTrace();
		}
		Long checkVoted = Long.parseLong(String.valueOf(Voter.getProperty("checkVoted")));
		if (checkVoted != 0) {
			request.getRequestDispatcher("/errorcostingvote.jsp").forward(request, response);
		} else {
			Voter.setProperty("checkVoted", 1);
			datastore.put(Voter); //updating value of checkvote to make it not elligble for the vote next Time
			Long currentVotes = Long.parseLong(String.valueOf(Cand.getProperty("Votes")));
			currentVotes = currentVotes + 1;
			Cand.setProperty("Votes", currentVotes);
			datastore.put(Cand);
		}
		request.getRequestDispatcher("/thankyouforvoting.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		doGet(request, response);
	}
}
