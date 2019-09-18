import java.util.Scanner;

/**
 *  This class handles the command line interface
 */
public class VendingMachineCLI
{
    /**
     *
     * @param in ( Scanner object)
     * @param coins (array of Coin objects)
     * @param machine ( VendingMachine object)
     */
	public void CLI(Scanner in, Coin[] coins, VendingMachine machine)
    {
        GetChoice choice = new GetChoice();

		boolean more = true;

		while (more)
		{
			System.out.println("S)how products   I)nsert coin   B)uy    Q)uit");
			String command = in.nextLine().toUpperCase();
			String password;

			if (command.equals("S"))
			{
				for (Product p : machine.getProductTypes())
					System.out.println(p.toString());
			}
			else if (command.equals("I"))
				System.out.println(machine.addCoin((Coin) choice.getChoice(coins, in)));

			else if (command.equals("B"))
			{
				try
				{
					Product p = (Product) choice.getChoice(machine.getProductTypes(), in);
                    System.out.println(machine.buyProduct(p));
				}
				catch (VendingException ex) {
					System.out.println(ex.getMessage());
				}
			}
			else if (command.equals("Q"))
			{
			    machine.closeDown();
                System.exit(0);
            }

			else if (command.equals("#*#"))
			{
			    System.out.print("Input passcode:");
			    password = in.nextLine();
			    if(!machine.isValidOperatorCode(password))
                    System.out.println("Incorrect code");
			    else
			        {
			        System.out.println("You have logged in");
			        boolean m1 = true;
			        while (m1)
			        {
			            System.out.println("S)how products  A)dd products  R)emove products/coins  B)ack");
			            command = in.nextLine().toUpperCase();
			            if (command.equals("S"))
			            {
			                for (Product p : machine.getProductTypes())
									System.out.println(p.toString());
			            }
			            else if (command.equals("A"))
			            {
			                System.out.println("Description:");
			                String description = in.nextLine();
			                System.out.println("Price:");
			                double price = in.nextDouble();
			                System.out.println("Quantity:");
			                int quantity = in.nextInt();
			                in.nextLine();
                            machine.addProduct(new Product(description, price), quantity);
			            }
			            else if (command.equals("R"))
			            {
			                boolean m2 = true;
			                while (m2)
			                {
			                    System.out.println("Remove P)roducts or C)oins ?  B)ack");
			                    command = in.nextLine().toUpperCase();
			                    if (command.equals("P"))
			                    {
			                        if(!(machine.getProductTypes().length == 0))
			                        {
			                            System.out.println("Product to remove: ");
			                            Product p = (Product) choice.getChoice(machine.getProductTypes(), in);
			                            System.out.println("Quantity to remove: ");
			                            int qty = in.nextInt();
                                        System.out.println(machine.removeProduct(p, qty));
			                        }
			                        else
			                            System.out.println("No products in stock!");
			                    }
			                    else if (command.equals("C"))
			                    {
			                        if(!(machine.currentCoins.getCoins().size() == 0))
			                            System.out.println("Removed: " + machine.removeMoney());
			                        else
			                            System.out.println("No coins in machine!");
			                    }
			                    else if (command.equals("B"))
			                        m2 = false;
			                }
			            }
			            else if (command.equals("B"))
			                m1 = false;
			        }
			    }
			}
		}
	}
}
