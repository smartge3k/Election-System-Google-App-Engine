package com.example.appengine.java8;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import Entities.candidate;

public class editcandidate extends HttpServlet{
	private static final long serialVersionUID = 1L;
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	final Logger log = Logger.getLogger(editcandidate.class.getName());

	public editcandidate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("div_id") != null) {
			//    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			String temp = (String) request.getParameter("div_id");
			log.info("Usman" + temp);
			Key key = KeyFactory.createKey("Candidate", Long.parseLong(temp));
			Entity cand = null;
			try {
				cand = datastore.get(key);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
			if (cand != null) {
				log.info("Here");
			}
			candidate candi = new candidate();
			candi.setFirstname((String) cand.getProperty("firstName"));
			candi.setSurname((String) cand.getProperty("lastName"));
			candi.setFaculty((String) cand.getProperty("faculty"));
			candi.setID(Long.parseLong(temp));
			request.setAttribute("candi", candi);
			request.getRequestDispatcher("/editcandiate.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		String temp = (String) request.getParameter("div_id");
		String fname = request.getParameter("div_fname").trim();
		String lname = request.getParameter("div_lname").trim();
		String fclty = request.getParameter("div_fclty").trim();
		Key key = KeyFactory.createKey("Candidate", Long.parseLong(temp));
		Entity ent = null;
		try {
			ent = datastore.get(key);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ent.setProperty("firstName", fname);
		ent.setProperty("lastName", lname);
		ent.setProperty("faculty", fclty);
		datastore.put(ent);
		//datastore.put
		response.sendRedirect("/admin/dashboard");
	}
}
