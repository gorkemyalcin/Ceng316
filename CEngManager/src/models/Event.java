package models;

/**
 * @author Gorkem
 *
 */
public class Event {

	

	private String title;
	private String date;
	private String url;
	private String time;
	private String location;
	private String content;
	
	public Event(String eventTitle, String date, String time, String url, String location, String content) {
		this.title = eventTitle;
		this.date = date;
		this.time = time;
		this.url = url;
		this.location = location;
		this.content = content;
	}
	
	public String toString() {
		return  "Title: "+ title + ", Date: " + date + ", Time: " + time + ", Location: " + location + ", Content: " + content +  ", URL: " + url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	
}
