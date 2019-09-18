

/*
-declare variables

-set up file readers
	-store in ArrayLists

-pass reader stuff to pre written methods

-count how many medals each country has
	-scan results array
	-store all countries that appear to list or array
	-

-arrange highest to lowest

-print leader boards

*/

import java.io.*;
import java.util.*;

public class Olympics {
	public static void main(String [] args)throws IOException{

		ArrayList<Country> countryArr = new ArrayList();
		ArrayList<Sport> sportArr = new ArrayList();
		ArrayList<Medal> medalArr = new ArrayList();
		ArrayList<Result> resultArr = new ArrayList();
		ArrayList<Tally> tallyArr = new ArrayList();
		String take, insert;


		FileReader countryFile = new FileReader("countries.txt");
		Scanner countryScan = new Scanner(countryFile);

		FileReader sportFile = new FileReader("sports.txt");
		Scanner sportScan = new Scanner(sportFile);

		FileReader medalFile = new FileReader("medals.txt");
		Scanner medalScan = new Scanner(medalFile);

		FileReader resultFile = new FileReader("results.txt");
		Scanner resultScan = new Scanner(resultFile);

		while(countryScan.hasNextLine()){
			take = countryScan.nextLine();
			String [] split = take.split(",");
			int cID = Integer.parseInt(split[0]);
			insert = split[1];
			Country country = new Country(cID, insert);
			countryArr.add(country);
		}
		//System.out.println(countryArr.get(5).getCountryName());
		while(sportScan.hasNextLine()){
			take = sportScan.nextLine();
			String [] split = take.split(",");
			int sID = Integer.parseInt(split[0]);
			insert = split[1];
			Sport sport = new Sport(sID, insert);
			sportArr.add(sport);
		}
		//System.out.println(sportArr.get(7).getSportID());
		while(medalScan.hasNextLine()){
			take = medalScan.nextLine();
			String [] split = take.split(",");
			int mID = Integer.parseInt(split[0]);
			insert = split[1];
			Medal medal = new Medal(mID, insert);
			medalArr.add(medal);
		}
		//System.out.println(medalArr.get(1).getMedalTypeID());
		while(resultScan.hasNextLine()){
			take = resultScan.nextLine();
			String [] split = take.split(",");
			int event = Integer.parseInt(split[0]);
			int sID = Integer.parseInt(split[1]);
			int cID = Integer.parseInt(split[2]);
			int mID = Integer.parseInt(split[3]);
			Result result = new Result(event, sID, cID, mID);
			resultArr.add(result);
		}
		//System.out.println(resultArr.get(6).getSportID());
		for(int i = 0; i < countryArr.size();i++){
			int gC = 0;
			int sC = 0;
			int bC = 0;
			for(int j = 0; j < resultArr.size(); j++){
				if(countryArr.get(i).getCountryID() == resultArr.get(j).getCountryID()){
					/*System.out.println(countryArr.get(i).getCountryID() + " " + resultArr.get(j).getCountryID());
					System.out.println(resultArr.get(j).getMedalTypeID());*/

					if((resultArr.get(j).getMedalTypeID()) == 1) {
						gC++;
						//System.out.println("Gold" + gC);
					}
					else if(resultArr.get(j).getMedalTypeID() == 2) {
						sC++;
						//System.out.println("Silver" + sC);
					}
					else if(resultArr.get(j).getMedalTypeID() == 3) {
						bC++;
						//System.out.println("Bronze" + bC);
					}
				}
			}
			Tally tally = new Tally(countryArr.get(i).getCountryName(), gC, sC, bC);
			tallyArr.add(tally);
			//System.out.println("T");
		}
		//System.out.println(tallyArr.get(5).getTotal() + " " + tallyArr.get(5).getCountryName());
	}

	//------------------------------------------------------------------------country

	public static class Country
	{
		private int countryID;
		private String countryName;
		Country(int aID, String aName)
		{
			countryID = aID;
			countryName = aName;
		}
		public int getCountryID()
		{
			return countryID;
		}
		public String getCountryName()
		{
			return countryName;
		}
	}

	//----------------------------------------------------------------------sport

	public static class Sport
	{
		private int sportID;
		private String sportName;
		Sport(int aID, String aName)
		{
			sportID = aID;
			sportName = aName;
		}
		public int getSportID()
		{
			return sportID;
		}
		public String getSportName()
		{
			return sportName;
		}
	}

	//--------------------------------------------------------------------medal

	public static class Medal
	{
		private int medalTypeID;
		private String medalName;
		Medal(int aID, String aName)
		{
			medalTypeID = aID;
			medalName = aName;
		}
		/*public void setMedalID(int mID)
		{
			medalTypeID = mID;
		}
		public void setMedalName(String insert)
		{
			medalName = insert;
		}																*/
		public int getMedalTypeID()
		{
			return medalTypeID;
		}
		public String getMedalName()
		{
			return medalName;
		}
	}

	//--------------------------------------------------------------------result

	public static class Result
	{
		private int eventID;
		private int sportID;
		private int countryID;
		private int medalTypeID;
		Result(int anEventID, int aSportID,
			   int aCountryID, int aMedalTypeID)
		{
			eventID = anEventID;
			sportID = aSportID;
			countryID = aCountryID;
			medalTypeID = aMedalTypeID;
		}
		public int getEventID()
		{
			return eventID;
		}
		public int getSportID()
		{
			return sportID;
		}
		public int getCountryID()
		{
			return countryID;
		}
		public int getMedalTypeID()
		{
			return medalTypeID;
		}
	}

	//--------------------------------------------------------------------tally

	public static class Tally
	{
		private String countryName;
		private int goldNo;
		private int silverNo;
		private int bronzeNo;
		Tally(String aCountryName, int aGoldNo,
			  int aSilverNo, int aBronzeNo)
		{
			countryName = aCountryName;
			goldNo = aGoldNo;
			silverNo = aSilverNo;
			bronzeNo = aBronzeNo;
		}
		public void setCountryName(String aCountryName)
		{
			countryName = aCountryName;
		}
		public String getCountryName()
		{
			return countryName;
		}
		public void setGoldNo(int aGoldNo)
		{
			goldNo = aGoldNo;
		}
		public int getGoldNo()
		{
			return goldNo;
		}
		public void setSilverNo(int aSilverNo)
		{
			goldNo = aSilverNo;
		}
		public int getSilverNo()
		{
			return silverNo;
		}
		public void setBronzeNo(int aBronzeNo)
		{
			goldNo = aBronzeNo;
		}
		public int getBronzeNo()
		{
			return bronzeNo;
		}
		public int getTotal()
		{
			return (goldNo + silverNo + bronzeNo);
		}
	}
}
