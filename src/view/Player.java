package view;
import java.io.Serializable;

public class Player implements Serializable{
/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private String name;
private int hour;
private int minute;
private int second;



public Player(String name, int hour, int minute, int second) {
	super();
	this.name = name;
	this.hour = hour;
	this.minute = minute;
	this.second = second;
}
public int getHour() {
	return hour;
}
public void setHour(int hour) {
	this.hour = hour;
}
public int getMinute() {
	return minute;
}
public void setMinute(int minute) {
	this.minute = minute;
}
public int getSecond() {
	return second;
}
public void setSecond(int second) {
	this.second = second;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
	public String toString() {
		
		return name+" "+hour+":"+minute+":"+second;
	}
}
