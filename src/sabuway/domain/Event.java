package sabuway.domain;

import java.util.Date;

public class Event {
	private Date eventDate = null;
	private String eventOwn = "";
	private String eventName = "";
	private String eventDetail = "";
	
	public Event(String eventOwn, String eventName, String eventDetail){
		this.eventDate = new Date();
		this.eventOwn = eventOwn;
		this.eventName = eventName;
		this.eventDetail = eventDetail;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDetail() {
		return eventDetail;
	}

	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}

	public String getEventOwn() {
		return eventOwn;
	}

	public void setEventOwn(String eventOwn) {
		this.eventOwn = eventOwn;
	}

	@Override
	public String toString() {
		return eventDate + "\t" + eventOwn + "\t" + eventName + "\t" + eventDetail+"\n";
	}
	
	
}
