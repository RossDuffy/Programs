import java.io.*;
import java.util.*;
public class ProcessEvents
{
  public static void main(String[]args) throws IOException
  {
    // create an ArrayList of objects of Event type from details in file.
	ArrayList<Event>  allEvents     = new ArrayList<Event>();
	File eventFile;
	String filename = "Events.txt";
	Scanner in;
	Event anEvent;
	String recordElements[];
	eventFile = new File(filename);
	if (eventFile.exists())
	{
	  in = new Scanner(eventFile);
	  while(in.hasNext())
	  {  
		recordElements = (in.nextLine().split(","));
		anEvent = new Event(Integer.parseInt(recordElements[0]),recordElements[1],recordElements[2]);
		allEvents.add(anEvent);
	  }
	  in.close();
	  if (allEvents.size() == 0)
		 System.out.println("No Festival/Event details to display");
	  else
	     generateReport(allEvents); // call method to generate report
	}
	else
	  System.out.println(filename + " does not exist");
  }
  
  public static void generateReport(ArrayList<Event> allEvents)
  {
	/* Order data on festivalID 
	
	   Next for each festival we need to get its events
	   Each time we have the events for a festival we call a method to
       display for a festival its events
    */	   
	ArrayList<String> tempList      = new ArrayList<String>();  
	ArrayList<String> tempEventList = new ArrayList<String>();
	String tempRecord;
	String previousFestivalID = "", currentFestivalID = "";
	int i, position;
	String eventsToProcess = "";
	String anEvent;
	for (i = 0; i < allEvents.size(); i++)
	{
	  tempRecord = allEvents.get(i).getFestivalID() + "," + allEvents.get(i).getEventID() + "," + allEvents.get(i).getEventName();
	  tempList.add(tempRecord);
	}
	Collections.sort(tempList);
	
	i = 0;
	anEvent             = tempList.get(i); 
	position            = anEvent.indexOf(",");
	currentFestivalID   = anEvent.substring(0,position);
	previousFestivalID  = currentFestivalID; 
	tempEventList.add(anEvent.substring(position + 1));
	for (i = 1; i < tempList.size(); i++)
	{
	  anEvent = tempList.get(i); 
	  position = anEvent.indexOf(",");
	  currentFestivalID = anEvent.substring(0,position);
	  if (previousFestivalID.equals(currentFestivalID))
		tempEventList.add(anEvent.substring(position + 1));
	  else
      {
		displayFestivalAndAssociatedEvents(previousFestivalID, tempEventList);
		tempEventList.clear();
		tempEventList.add(anEvent.substring(position + 1));
		previousFestivalID = currentFestivalID;
	  }
    }
  	displayFestivalAndAssociatedEvents(previousFestivalID, tempEventList);
  }
  
  public static void displayFestivalAndAssociatedEvents(String fID, ArrayList<String> events)
  {
	/* for a festival display its actual events 
	   first order events on eventID
	*/
	String anEvent[];
	System.out.println("Festival ID: " + fID);
    System.out.println("\t\t  Event ID    Event Title");
	
	Collections.sort(events);
	
	for (int i = 0; i < events.size(); i++)
	{
	  anEvent = (events.get(i)).split(",");
      System.out.print("\t\t  " + anEvent[0] + "\t      " + anEvent[1] + "\n");	  
	}	
	System.out.println();
  }	  
}