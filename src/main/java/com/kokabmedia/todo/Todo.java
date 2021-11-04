package com.kokabmedia.todo;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Size;

/*
 * This is a model class for todos that will be created.
 * 
 * It will also act as a model class for values bounded to the HTML view file properties.
 */

public class Todo {

	private int id;
	private String user;
	
	private String desc;
	private Date targetDate;
	private boolean isDone;
	
	public Todo(){}
	
	public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	/*
	 * The purpose of this method is to returns a textual representation 
	 * of the object, instead of for example when objects are return with 
	 * a JSP view file.
	 */
	@Override
	public String toString() {
		return "ToString - Todo [id=" + id + ", user=" + user + ", desc=" + desc + ", targetDate=" + targetDate + ", isDone="
				+ isDone + "]";
	}
	
	/*
	 * The goal of the equals() method is to verify that two objects are equal in the represented data. 
	 * The standard equals() method of the object class does not look at the memory location of the 
	 * objects it only verifies the amount of instances created, it does not look into the underlying 
	 * data of the created objects. The overwritten equals() method makes sure that two equal instances 
	 * result with the same hashCode. 
	 * 
	 * The hashCode() method returns a unique number for each instance, the unique number is the memory 
	 * location of the object converted into a integer, the hashCode method lets the system identify an 
	 * object in the heap memory of other objects.
	 * 
	 * The hashCode() method and the equals() method stop the system from inserting a object if two java 
	 * objects represent the same object with the same memory location, they stop duplicated objects from 
	 * being represented as two unique objects.   
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		return id == other.id;
	}	
}
