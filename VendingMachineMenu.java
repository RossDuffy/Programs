import java.util.Scanner;
/**
   A start up menu for the vending machine.
*/

public class VendingMachineMenu {
	private VendingMachineCLI cli;
	private VendingMachineGUI gui;
	private Scanner in;
	private static Coin[] coinTypes;

	/**
	 * Constructs a VendingMachineMenu object
	 */
	public VendingMachineMenu()
    {
		in = new Scanner(System.in);
		cli = new VendingMachineCLI();
		gui = new VendingMachineGUI();
		coinTypes = FileOutputService.readFileToCoinTypesArray();
	}

	/**
	 * Runs the vending machine system.
	 *
	 * @param machine the vending machine
	 */
	public void run(VendingMachine machine)
    {
		boolean more = true;

		while (more)
		{
			System.out.println("C)LI or G)UI ? Q)uit");
			String command = in.nextLine().toUpperCase();

			if (command.equals("C"))
				cli.CLI(in, coinTypes, machine);
			else if (command.equals("G"))
				gui.GUI(coinTypes, machine);
			else if (command.equals("Q"))
			    System.exit(1);
		}
    }
}
