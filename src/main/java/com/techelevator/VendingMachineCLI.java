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
	private static final String[] MAIN_MENU_OPTIONS_LEVEL2 = { MAIN_MENU_OPTION_FEED_MONEY, MAIN_MENU_OPTION_SELECT_PRODUCT, MAIN_MENU_OPTION_FINISH_TRANSACTION, "" };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			VendingMachine restockMachine = new VendingMachine();
			restockMachine.restock();
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				restockMachine.displayProducts();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS_LEVEL2, restockMachine);
				if (choice.equals(MAIN_MENU_OPTION_FEED_MONEY)) {


					// ask user for money
					restockMachine.acceptMoney();
					restockMachine.displayProducts();
					restockMachine.selectProduct();
				}
				/*else if (choice.equals(MAIN_MENU_OPTION_SELECT_PRODUCT)) {
					// show user a list of products and allow them to pick one. Once picked, then process the choice.
					restockMachine.displayProducts();


				}else if(choice.equals(MAIN_MENU_OPTION_FINISH_TRANSACTION)){
					// exit
				}

				 */
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
