package Entities;

import java.time.LocalDateTime;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

public class Election{
	private String name;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long a) {
		this.id = a;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
}
