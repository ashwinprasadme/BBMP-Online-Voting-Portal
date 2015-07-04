
package com.aklc.pojo;

import java.sql.Date;
import java.sql.Time;

public class Election {

	private int id;
	private String descp;
	private String assembly;
	private Date commencedate;
	private Time begintime;
	private Time endtime;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getAssembly() {
		return assembly;
	}

	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}

	public Date getCommencedate() {
		return commencedate;
	}

	public void setCommencedate(Date commencedate) {
		this.commencedate = commencedate;
	}

	public Time getBegintime() {
		return begintime;
	}

	public void setBegintime(Time begintime) {
		this.begintime = begintime;
	}

	public Time getEndtime() {
		return endtime;
	}

	public void setEndtime(Time endtime) {
		this.endtime = endtime;
	}

}
