package entity;


import java.sql.Date;

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

//
//@NamedQueries( {
//	@NamedQuery(name = "BaseData.findIMSIEvent", query = "select imsi, eventCauseID, causeCode from BaseData"),
//})

@Entity
public class BaseData {
	
	//every entity requires an id, and we can make it auto generated
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int baseDataID; 
	
	private int mccmncID, cellID, duration;
	private String TAC, failureClassID, neVersion, imsi, eventCauseID;
	private Date baseDate;
	
	
	public BaseData(){
		
	}
	

	public BaseData(String eventCauseID, int mccmncID, int cellID, int duration, String imsi, String failureClassID, String TAC, String neVersion, Date baseDate) {
		super();

		this.eventCauseID = eventCauseID;
		this.failureClassID = failureClassID;
		this.mccmncID = mccmncID;
		this.cellID = cellID;
		this.imsi = imsi;
		this.duration = duration;
		this.TAC = TAC;
		this.neVersion = neVersion;
		this.baseDate = baseDate;
	}


	public int getBaseDataID() {
		return baseDataID;
	}


	public void setBaseDataID(int baseDataID) {
		this.baseDataID = baseDataID;
	}


	public int getMccmncID() {
		return mccmncID;
	}


	public void setMccmncID(int mccmncID) {
		this.mccmncID = mccmncID;
	}


	public int getCellID() {
		return cellID;
	}


	public void setCellID(int cellID) {
		this.cellID = cellID;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getTAC() {
		return TAC;
	}


	public void setTAC(String tAC) {
		TAC = tAC;
	}


	public String getFailureClassID() {
		return failureClassID;
	}


	public void setFailureClassID(String failureClassID) {
		this.failureClassID = failureClassID;
	}


	public String getNeVersion() {
		return neVersion;
	}


	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}


	public String getImsi() {
		return imsi;
	}


	public void setImsi(String imsi) {
		this.imsi = imsi;
	}


	public String getEventCauseID() {
		return eventCauseID;
	}


	public void setEventCauseID(String eventCauseID) {
		this.eventCauseID = eventCauseID;
	}


	public Date getBaseDate() {
		return baseDate;
	}


	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}

	
	

}
