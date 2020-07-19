package com.example.appengine.java8;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import Entities.Election;
import Entities.candidate;

public class createelection extends HttpServlet{
	private static final long serialVersionUID = 1L;
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Logger log = Logger.getLogger(createelection.class.getName());
		List<Election> elec = new ArrayList<>();
		Query eq = new Query("Election");
		List<Entity> results = datastore.prepare(eq).asList(FetchOptions.Builder.withDefaults());
		for (Entity result : results) {
			Election etemp = new Election();
			LocalDateTime startTime = ((Date) result.getProperty("StartTime")).toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			etemp.setStartTime(startTime);
			LocalDateTime EndTime = ((Date) result.getProperty("EndTime")).toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
			etemp.setEndTime(EndTime);
			long a = result.getKey().getId();
			etemp.setName((String) result.getProperty("Name"));
			etemp.setId(a);
			elec.add(etemp);
		}
		request.setAttribute("electionlist", elec);
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
			//dsfsdf
		}
		request.setAttribute("candidatelist", div);
		request.getRequestDispatcher("/mainelection.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		doGet(request, response);
	}
}
