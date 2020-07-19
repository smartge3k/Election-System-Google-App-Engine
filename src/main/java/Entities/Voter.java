package Entities;

public class Voter{
	private int id;
	private String email;
	private int checkVoted;

	public Voter() {
	}

	public Voter(String e, boolean flag) {
		this.email = e;
		this.checkVoted = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCheckVoted() {
		return checkVoted;
	}

	public void setCheckVoted(int checkVoted) {
		this.checkVoted = checkVoted;
	}
}
