package entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class EventCause {
	
	//every entity requires an id, and we can make it auto generated
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int eventCauseID; 
	private int causeID, eventID;
	private String causeDescription;

	
	
	public EventCause(){
		
	}
	

	public EventCause(int causeID, int eventID, String causeDescription) {
		super();
		this.causeID = causeID;
		this.eventID = eventID;
		this.causeDescription = causeDescription;
	}


	public int getEventCauseID() {
		return eventCauseID;
	}


	public void setEventCauseID(int eventCauseID) {
		this.eventCauseID = eventCauseID;
	}


	public int getCauseID() {
		return causeID;
	}


	public void setCauseID(int causeID) {
		this.causeID = causeID;
	}


	public int getEventID() {
		return eventID;
	}


	public void setEventID(int eventID) {
		this.eventID = eventID;
	}


	public String getCauseDescription() {
		return causeDescription;
	}


	public void setCauseDescription(String causeDescription) {
		this.causeDescription = causeDescription;
	}


	

}
