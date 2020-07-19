package Entities;

import com.google.appengine.api.datastore.Key;

public class candidate{
	long id;
	private String firstname;
	private String surname;
	private String faculty;
	private long costedVote;

	public candidate() {
	}

	public candidate(String fname, String sname, String fclty) {
		this.setFirstname(fname);
		this.setSurname(sname);
		this.setFaculty(fclty);
	}

	public long getId() {
		return id;
	}

	public void setID(long a) {
		this.id = a;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public long getCostedVote() {
		return costedVote;
	}

	public void setCostedVote(long costedVote) {
		this.costedVote = costedVote;
	}
}
