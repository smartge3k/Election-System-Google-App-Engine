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

public class editElection extends HttpServlet{
	private static final long serialVersionUID = 1L;
	final Logger log = Logger.getLogger(editElection.class.getName());

	public editElection() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("div_id") != null) {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			String temp = (String) request.getParameter("div_id");
			Key key = KeyFactory.createKey("Election", temp);
			//KeyFactory.stringToKey(temp);
			Entity elect = null;
			try {
				elect = datastore.get(key);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("div", elect);
			request.getRequestDispatcher("/editelection.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		doGet(request, response);
	}
}
