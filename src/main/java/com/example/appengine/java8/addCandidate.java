package com.example.appengine.java8;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

import Entities.candidate;

public class addCandidate extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public addCandidate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/addcandidate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String fname = request.getParameter("div_fname").trim();
		String lname = request.getParameter("div_lname").trim();
		String faculty = request.getParameter("div_faculty").trim();
		Entity Candidate = new Entity("Candidate");
		Candidate.setProperty("firstName", fname);
		Candidate.setProperty("lastName", lname);
		Candidate.setProperty("faculty", faculty);
		Candidate.setProperty("Votes", 0);
		Transaction txn = datastore.beginTransaction();
		datastore.put(txn, Candidate);
		txn.commit();
		if (txn.isActive()) { //if trnasaction is still active (which means not committed properly then it rollback)
			txn.rollback();
		}
		response.sendRedirect("/admin/dashboard");
	}
}
