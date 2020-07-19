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
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import Entities.candidate;

public class deletecandidate extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public deletecandidate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("div_id") != null) {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			String temp = (String) request.getParameter("div_id");
			Key key = KeyFactory.createKey("Candidate", Long.parseLong(temp));
			datastore.delete(key);
		}
		response.sendRedirect("/admin/dashboard");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
