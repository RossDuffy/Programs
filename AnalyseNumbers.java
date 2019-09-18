import java.util.*; // Scanner, ArrayList, Collections
import java.io.*;   // File, IOException
import java.time.LocalDate;
public class AnalyseNumbers
{
  public static void main(String [] args) throws IOException
  {
	ArrayList<Integer> numbers     = new ArrayList<Integer>();
	ArrayList<Integer> frequencies = new ArrayList<Integer>();
	ArrayList<Integer> tempList    = new ArrayList<Integer>();
    String errorMessage1 = "usage: java CheckLottoNumbers date drawType";
    String errorMessage2 = "Invalid draw type.";
    String pattern = "[0-9]{1}", filename = "LotteryDraws.txt", result = "";
	LocalDate dateEntered, systemDate, fileDate;
	String elements[];
	File aFile;
	Scanner in;
	boolean continueReading = true;
	int aNumber, position, nextRecordIndex;
    if      (args.length != 2)             result = errorMessage1;
    else if (!(args[1]).matches(pattern))  result = errorMessage2;
    else
    {
	  dateEntered = LocalDate.parse(args[0]);
      systemDate = LocalDate.now();
	  if ((systemDate.compareTo(dateEntered) < 0))
	    result = "Date supplied is in the future.";
	  else
	  {
	    aFile = new File(filename);
		if (aFile.exists())
		{
	      in = new Scanner(aFile);
	      while(in.hasNext() && continueReading)
	      {
		    elements = (in.nextLine()).split(",");
	        fileDate = LocalDate.parse(elements[0]);	    
		    if (dateEntered.compareTo(fileDate) >= 0)
		    {
			  if (elements[elements.length - 1].equals(args[1]))
			  {  
		        result += elements[0] + ",";
		        tempList.clear();
		        for (int i = 0; i < elements.length - 2; i++)
		        {
			      aNumber = Integer.parseInt(elements[i + 1]);
				  tempList.add(aNumber);
				  if (!(numbers.contains(aNumber)))
			      {
	                nextRecordIndex = numbers.size();	 
	                numbers.add(nextRecordIndex, aNumber);
	                frequencies.add(nextRecordIndex, 1);
	              }
	              else
	              {
	                position = numbers.indexOf(aNumber);   
                    frequencies.set(position, frequencies.get(position) + 1); 	   
	              } 	 
	            }
		        Collections.sort(tempList);
		        for (int i = 0; i < (tempList.size() - 1); i++)
		          result += tempList.get(i) + ",";
		        result += tempList.get(tempList.size() - 1) + "\n";
	          }
	        }
			else
				continueReading = false;
	      }
	      in.close();  
	      if (result.equals(""))
	        result = "No draw results found on or before the date supplied.";  
	      else
	      {
		    result += "\n\n     Number\t\tFrequency\n";
		    sortLists(numbers,frequencies);
		    for (int i = 0; i < frequencies.size(); i++)
			  result += "     " + numbers.get(i) + "\t\t\t" + frequencies.get(i) + "\n";  
	        result = "Draws on which analysis is based:\n" + result;
	      }    
	    }
		else
		   result = filename + " was not located.";
      }
	}  
    System.out.print(result);
  }
  
  public static void sortLists(ArrayList<Integer> n, ArrayList<Integer> f)
  {
    int pass, comparison, temp1, temp2;
	for (pass = 1; pass <= n.size() - 1; pass++)
	{
	  for (comparison = 1; comparison <= n.size() - pass; comparison++)	
	  {	  
		if (n.get(comparison) < n.get(comparison - 1))
		{
		  temp1 = n.get(comparison - 1);
          temp2 = f.get(comparison - 1);
          n.set(comparison - 1, n.get(comparison));
		  f.set(comparison - 1, f.get(comparison));
		  n.set(comparison, temp1);
		  f.set(comparison, temp2);
		}
	  }	
	}	
  }	
}

