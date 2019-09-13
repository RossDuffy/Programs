public class FizzBuzz
{
	public static void main(String args[])
	{
		int start = 0, end = 0;
		String output = "";
		
		//tests if input was made and made correctly and assignes it
		try
		{
			if(args.length == 0)
			{
				start = 1;
				end = 100;
			}	
			else if(args.length == 1)
			{
				end = Integer.parseInt(args[0]);	
			}
			else
			{
				start = Integer.parseInt(args[0]);
				end = Integer.parseInt(args[1]);
			}
		}
		catch(NumberFormatException e)
		{
			System.out.print("An input was non-integer, commencing 1-100 instead.\n");
			start = 1;
			end = 100;
		}
		
		//tests if start is less than end
		if(start >= end)
		{
			System.out.print("Start must be lower than End");
		}
		else
		{
			//performs game
			for(int i = start; i <= end; i++)
			{
				int m3 = i%3, m5 = i%5;
			
				if(m3 == 0)
				{
					output +="Fizz";
				}
				if(m5 == 0)
				{
					output += "Buzz";
				}
				if(m3 != 0 && m5 != 0)
				{
					output += Integer.toString(i);
				}
				System.out.print(output + "\n");
				output = "";
			}
		}
	}
}