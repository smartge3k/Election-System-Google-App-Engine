package com.example.appengine.java8;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
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
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import Entities.Election;
import Entities.candidate;

public class ElectionResults extends HttpServlet{
	private static final long serialVersionUID = 1L;
	final Logger log = Logger.getLogger(createelection.class.getName());

	public ElectionResults() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey("Election", 1); //to check if election have been added
		List<candidate> div = new ArrayList<>();
		Entity elect = null;
		try {
			elect = datastore.get(key);
		} catch (EntityNotFoundException e1) {
			e1.printStackTrace();
		}
		if (elect == null) {
			request.getRequestDispatcher("/errorinelectionresults.jsp").forward(request, response);
		} else {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime startTime = ((Date) elect.getProperty("StartTime")).toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			LocalDateTime endTime = ((Date) elect.getProperty("EndTime")).toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			if (startTime.isBefore(now) && !endTime.isAfter(now)) //if the election time has started then  this link will be available
			{
				Query VotersEntity = new Query("Voters");
				List<Entity> totalVoters = datastore.prepare(VotersEntity).asList(FetchOptions.Builder.withDefaults());
				totalVoters.size(); //get all Voters and see the size to get total number of voters
				log.info("Total Voters " + String.valueOf(totalVoters.size()));
				request.setAttribute("totalVoters", totalVoters.size());
				Long totalCostedVote = 0L;
				Query q = new Query("Candidate");
				PreparedQuery totalCandidates = datastore.prepare(q);
				for (Entity result : totalCandidates.asIterable()) {
					candidate temp = new candidate();
					temp.setFirstname((String) result.getProperty("firstName"));
					temp.setSurname((String) result.getProperty("lastName"));
					temp.setFaculty((String) result.getProperty("faculty"));
					long a = result.getKey().getId();
					Long currentVotes = Long.parseLong(String.valueOf(result.getProperty("Votes")));
					totalCostedVote += currentVotes;
					temp.setID(a);
					temp.setCostedVote(currentVotes);
					div.add(temp);
				}
				request.setAttribute("totalCostedVote", totalCostedVote);
				log.info("  totalCostedVote" + totalCostedVote.toString());
				double percentage = (double) totalCostedVote / (double) totalVoters.size();
				log.info("  After Devision Percentage " + String.valueOf(percentage));
				percentage = percentage * (double) 100;
				request.setAttribute("percentage", percentage);
				request.setAttribute("cadidate", div);
				log.info("  percentage" + String.valueOf(percentage));
				request.getRequestDispatcher("/electionresults.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/errorinelectionresults.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		doGet(request, response);
	}
}
