package helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import models.Event;

public class EventHelper {
	Document doc3 = null;
	String url;
	String time;
	String date;
	String eventTitle;
	String location;
	String newString;
	String content;
	String array[] = new String[4];
	int eventId = 0;

	public Event getEventByTitle(String title) {
		List<Event> allEvents = getEvent();
		for (Event event: allEvents) {
			if (event.getTitle().equals(title)) {
				return event;
			}
		}
		return null;
	}
	
	
	public List<Event> getEventsByTitle(List<String> titles) {
		List<Event> result = new ArrayList<>();
		List<Event> allEvents = getEvent();
		for (Event event: allEvents) {
			for (String title: titles) {
				if (event.getTitle().equals(title)) {
					result.add(event);
				}
			}
		}
		return result;
	}
	
	public List<Event> getEvent() {

		List<Event> output = new ArrayList<>();

		List<String> mylist = eventUrlsFinder();

		for (int i = 0; i < mylist.size(); i++) {

			String url = mylist.get(i);

			try {

				doc3 = Jsoup.connect(url).get();

				Elements event = doc3.select("h2"); // take title from event

				Elements date_times = doc3.select("ul[class='event-info__datetime']");
				// take date and time from site
				Elements venues = doc3.select("p[class='event-info__venue']"); // take the adress form site

				Element contents = doc3.select("div[class='wpb_wrapper']").first();// take content
				content = contents.text(); // content

				for (Element eventName : event) {
					eventTitle = eventName.text();
				}
				for (Element date : date_times) {
					String nDate = date.text();
					parsingDate(nDate);
				}
				for (Element venu : venues) {
					location = venu.text();
				}


				Event eventObject = new Event(eventTitle, date, time, url, location, content); // create a event
																										// object
				output.add(eventObject);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return output;

	}

	private void parsingDate(String str) {

		StringTokenizer st = new StringTokenizer(str, ",");

		while (st.hasMoreTokens()) {

			newString = st.nextToken();

			if (newString.contains(" to")) {
				StringTokenizer a = new StringTokenizer(newString, " ");
				for (int i = 0; i < 4; i++) {
					array[i] = a.nextToken();
					;
				}
			} else {
				date = newString;
			}

		}

		date = date + " " + array[0];
		time = array[1] + " " + array[2] + " " + array[3];

	}

	private List<String> eventUrlsFinder() {
		Document doc = null;
		Document doc1 = null;
		List<String> listWithoutDuplicates = null;

		try {
			List<String> myArray = new ArrayList<String>();

			doc = Jsoup.connect("http://ceng.iyte.edu.tr").get();
			Elements links = doc.select("a[href]");

			for (Element link : links) {

				String nLink = link.attr("href");

				if (nLink.contains("events")) {
					doc1 = Jsoup.connect(nLink).get();
					Elements links2 = doc1.select("a");

					for (Element link2 : links2) {
						String nLink2 = link2.attr("href");

						if (nLink2.contains("event/")) {
							myArray.add(nLink2);

						}
					}
				}

			}
			LinkedHashSet<String> hashSet = new LinkedHashSet<>(myArray);
			listWithoutDuplicates = new ArrayList<>(hashSet);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listWithoutDuplicates;

	}

}