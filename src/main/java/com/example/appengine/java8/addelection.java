package com.example.appengine.java8;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

@WebServlet(name = "addelection", value = "/addelection")
public class addelection extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public addelection() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/addelection.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String sdate = request.getParameter("div_startdate").trim();
		String edate = request.getParameter("div_enddate").trim();
		String name = request.getParameter("div_name").trim();
		sdate = sdate.replace("T", " ");
		edate = edate.replace("T", " ");
		Date date1 = null;
		Date date2 = null;
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(edate);
			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Entity election = new Entity("Election", 1);
		election.setProperty("StartTime", date1);
		election.setProperty("EndTime", date2);
		election.setProperty("Name", name);
		Transaction txn = datastore.beginTransaction();
		datastore.put(txn, election);
		txn.commit();
		if (txn.isActive()) { //if trnasaction is still active (which means not committed properly then it rollbacks)
			txn.rollback();
		}
		response.sendRedirect("/admin/dashboard");
	}
}
