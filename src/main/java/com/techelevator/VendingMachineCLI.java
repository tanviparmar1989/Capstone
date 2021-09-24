package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT };
	private static final String MAIN_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String MAIN_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String MAIN_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private String currentMoneyProvided = "Current Money Provided: $";
	private static final String[] MAIN_MENU_OPTIONS_LEVEL2 = { MAIN_MENU_OPTION_FEED_MONEY, MAIN_MENU_OPTION_SELECT_PRODUCT, MAIN_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			VendingMachine restockMachine = new VendingMachine();
			restockMachine.restock();
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				restockMachine.displayProducts();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS_LEVEL2);
				if (choice.equals(MAIN_MENU_OPTION_FEED_MONEY)) {

					System.out.println("Enter the feed Money, VM only accepts $1, $2, $5, $10");

					Scanner scanner = new Scanner(System.in);
					String userFeedMoney = scanner.nextLine();

				// feed money in valid whole dollar amounts
					if (userFeedMoney.equals("1") || userFeedMoney.equals("2") || userFeedMoney.equals("5") || userFeedMoney.equals("10")){
					// the amount should display in Purchase window
						choice
						System.out.println(currentMoneyProvided + userFeedMoney);

					}else {
						System.out.println("invalid feed money, please enter the valid money");
					}


					// ask user for money


				} else if (choice.equals(MAIN_MENU_OPTION_SELECT_PRODUCT)) {
					// show user a list of products and allow them to pick one. Once picked, then process the choice.


				}else if(choice.equals(MAIN_MENU_OPTION_FINISH_TRANSACTION)){
					// exit
				}
			}else if(choice.equals(MAIN_MENU_OPTION_EXIT)){
				// exit
			}else if(choice.equals(MAIN_MENU_OPTION_SALES_REPORT)){
				//make it hidden and it generates a report
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
