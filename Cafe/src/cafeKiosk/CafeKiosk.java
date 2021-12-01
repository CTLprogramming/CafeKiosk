package cafeKiosk;




import java.util.ArrayList;
import general.My;
import general.MyVal;

public class CafeKiosk 
{
//
	public static void main(String[] args) 
	{
		
		String[]menu= { "Void", "White", "Brown", "Granary", "White Roll", "Brown Roll", "White Wrap", "Brown Wrap", "Gluten Free Roll", "Butter", 
				"Mayonnaise", "Grilled Chicken", "Breaded Chicken", "Taco Chicken", "Chicken supreme", "Chicken Tikka", "Ham", "Peperoni", "Turkey", 
				"Steak", "Tuna", "Lettuce", "Tomato", "Onions", "Peppers", "Cucumber", "Sweetcorn", "Cheese", "Pickles", "Rice", "Egg", "Spinach", 
				"Coleslaw", "Mayo", "Taco", "BBQ", "Ketchup", "HP Brown Sauce", "Burger", "Stew", "Lasagne", "Veg Soup", "Chicken Soup", "Sausages", 
				"Sausage Rolls", "Vol au vent", "Chicken Curry", "Chips", "Beans", "Crusty Roll", "Rice", "Peas", "Wedges", "Curly Fries", 
				"Peppered Sauce Dip", "Curry Dip", "Gravy Dip", "", "", ""};
//		int [] stock = new int[59];  //each position corresponds to stock of menu item array.
		int[] stock = {1, 10,10,10,0,10,10,10,10,10,0,10,10,10,10,0,10,10,10,10,10,10,10,10,0,10,10,10,10,10,10,10,10,10,10,0,10,10,10,
						10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,0,0,0};
		//Array stock[1] always has to be >0 or validation will fail in sandwich method.
		//Positions 4, 10, 15, 24 and 35 set to 0 for testing, rest set to 10. 
		
		
//		The following three arrays clear at checkout for each customer
		ArrayList<ArrayList<Integer>> orders = new ArrayList<ArrayList<Integer>>(); //A list of sandwichIngredients and saladIngredients
		ArrayList<Double> orderPrices = new ArrayList<Double>();					//A matching list of prices for orders

//		The following two arrays accumulate all customer orders at checkout 
//		for staff use, printing labels and can be cleared in Admin section (when orders made up). 
		ArrayList<ArrayList<Integer>> ordersKitchen = new ArrayList<ArrayList<Integer>>(); //Order details/ ingredients
		ArrayList<Double> orderPricesKitchen = new ArrayList<Double>();					   //matching list of prices
		ArrayList<String> orderNamesKitchen = new ArrayList<String>();					   //matching list of names added at checkout
		
		double sandwichPrice = 0.0, totalPrice = 0.0;
//		ArrayList<Integer> sandwichIngredients = new ArrayList<Integer>(); 
		ArrayList<Integer> saladIngredients = new ArrayList<Integer>();		
		int menuCheck = 0,  numberItems =0;
		

		do
		{	
			System.out.printf("\n\t%40s","Menu A:  Sandwiches");
			System.out.printf("\n\t%40s","--------------------");
			System.out.printf("\n\t");
			System.out.printf("\n\t£3  (includes one meat and 2 salad options)");
			System.out.printf("\n\t     +60p per additional meat options");
			System.out.printf("\n\t     +35p per additional salad options");
			System.out.printf("\n\t");
			
			System.out.printf("\n\t%40s","Menu B:  Salads");
			System.out.printf("\n\t%40s","--------------------");
			System.out.printf("\n\t");
			System.out.printf("\n\t£2 	for Small (1 meat, 2 salad items) ");
			System.out.printf("\n\t£4 	for Medium (2 meat, 4 salad items)");
			System.out.printf("\n\t£4.75 	for Large (2 meat, 6 salad items). ");
			System.out.printf("\n\t     +60p per additional meat options");
			System.out.printf("\n\t     +20p per additional salad options");
			System.out.printf("\n\t");

			System.out.printf("\n\t");
			System.out.printf("\n\t");
			System.out.printf("\n\t1: Choose Sandwich ");
			System.out.printf("\n\t2: Choose Salad");
			System.out.printf("\n\t   Closed (Hot Food Counter)");
			System.out.printf("\n\t4: Cancel order");
			System.out.printf("\n\t5: Checkout  %35s %10s", "No of items","Total");
			System.out.printf("\n\t   %40d %10s %4.2f", orderPrices.size(),"£", totalPrice);
			System.out.printf("\n\t");
			menuCheck = MyVal.validInt("Please enter 1, 2, 4 or 5: ", 1, 9);
			while (menuCheck == 3)									
			{
				My.p("\n\tHot Food Counter is closed, please choose another option 1, 2, 4 or 5: ");
				menuCheck = (MyVal.validInt("", 1, 6));
			}		
			if (menuCheck ==1)  
			{
				sandwichPrice = sandwich(orders, menu, stock);  			//add bread stock check?
//				orders.add(sandwichIngredients);
				orderPrices.add(sandwichPrice);
				totalPrice = totalPrice+sandwichPrice;
				numberItems += numberItems;
			}
			if (menuCheck ==5) checkout(orders, orderPrices, menu, totalPrice, ordersKitchen, orderPricesKitchen, orderNamesKitchen);

			if (menuCheck ==6) menuStock(menu, stock);

/*			if (menuCheck ==7)  
			{
				sandwichPrice = testLoadOrder1(orders, menu, stock);  			//add bread stock check?
//				orders.add(sandwichIngredients);
				orderPrices.add(sandwichPrice);
				totalPrice = totalPrice+sandwichPrice;
				numberItems += numberItems;
			}	
			if (menuCheck ==8)  
			{
				sandwichPrice = testLoadOrder2(orders, menu, stock);  			//add bread stock check?
//				orders.add(sandwichIngredients);
				orderPrices.add(sandwichPrice);
				totalPrice = totalPrice+sandwichPrice;
				numberItems += numberItems;
			}	
			if (menuCheck ==9)  
			{
				sandwichPrice = testLoadOrder3(orders, menu, stock);  			//add bread stock check?
//				orders.add(sandwichIngredients);
				orderPrices.add(sandwichPrice);
				totalPrice = totalPrice+sandwichPrice;
				numberItems += numberItems;
			}	
			*/
			
		}
		while (menuCheck!=0);		// program continues to loop for next customer, it cannot be terminated (0 cannot be input).
		

		
	}

		public static double sandwich (ArrayList<ArrayList<Integer>> orders ,String[] menu, int[] stock)
		{
			ArrayList<Integer> sandwichIngredients = new ArrayList<Integer>();

			int choice = 0;
			double sandwichPrice = 3.0;
			
			menuA(menu);  //Prints full sandwich menu
			
			for (int i = 1; i<=8; i++)
			{

				if (stock[i] ==0) 
				{
					My.p("\n\t     Out of stock ("+menu[i]+")");
				}
				else My.p("\n\t"+i+":   "+menu[i]);
			}			
			System.out.print("\n");
			
			choice = (MyVal.validInt("Please choose bread 1-8: ", 1, 8));

			stockCheck(""+menu[choice]+" is out of stock, \n\tplease choose another Bread 1-8: ", stock, choice, 1, 8);
			
			sandwichIngredients.add(choice);
			stock[choice] -= stock[choice];
	
			My.p("\n\t0:   No Spread");
			for (int i = 9; i<=10; i++)
			{
				if (stock[i] ==0) 
				{
					My.p("\n\t     Out of stock ("+menu[i]+")");
				}
				else My.p("\n\t"+i+":   "+menu[i]);
			}			
			choice = (MyVal.validInt("\n\tPlease choose spread 9 or 10: \n\tFor none choose 0: ", 0, 9, 10));

			stockCheck(""+menu[choice]+" is out of stock, \n\tplease choose another Spread or 0 for none: ", stock, choice, 0, 9, 10);
						
			if (choice !=0) 
				{
				sandwichIngredients.add(choice);
				stock[choice] -= stock[choice];
				}

			
			My.p("\n\t0:    No Meat");
			for (int i = 11; i<=20; i++)
			{
				if (stock[i] ==0) 
				{
					My.p("\n\t      Out of stock ("+menu[i]+")");
				}
				else My.p("\n\t"+i+":   "+menu[i]);
			}			
			My.p("\n");
			choice = (MyVal.validInt("Please choose Meat 11-20: \n\tFor none choose 0: ", 0, 11, 20));

			stockCheck(""+menu[choice]+" is out of stock, \n\tplease choose another Meat 11-20: \n\tFor none choose 0: ", stock, choice, 0, 11, 20);
		
			
			if (choice !=0) 
			{
				sandwichIngredients.add(choice);
				stock[choice] -= stock[choice];
			}


			while (choice!=0) 			//does not carry out loop if no meat is chosen
			{
				
				choice = (MyVal.validInt("Would you like additional meat (+60p) "
						+ "\n\tPlease choose 11-20 "
						+ "\n\tOr 0 for No: ", 0, 11, 20));

				stockCheck(""+menu[choice]+" is out of stock, \n\tplease choose another Meat 11-20: \n\tFor none choose 0: ", stock, choice, 0, 11, 20);
				
				if (choice !=0) 
				{
					sandwichIngredients.add(choice);
					stock[choice] -= stock[choice];
					sandwichPrice = (sandwichPrice + 0.60);
				}
				
			}
			

			
			My.p("\n\t0:    No Salad");
			for (int i = 21; i<=32; i++)
			{
				if (stock[i] ==0) 
				{
					My.p("\n\t      Out of stock ("+menu[i]+")");
				}
				else My.p("\n\t"+i+":   "+menu[i]);
			}			
			choice = (MyVal.validInt("\nPlease choose Salad 21-32: \n\tFor none, choose 0: ", 0, 21, 32));

			stockCheck(""+menu[choice]+" is out of stock, \n\tplease choose another Salad 21-32: \n\tFor none choose 0: ", stock, choice, 0, 21, 32);
			
			if (choice !=0) 
			{
				sandwichIngredients.add(choice);
				stock[choice] -= stock[choice];
			}
		
			if (choice !=0) 
			{
				choice = (MyVal.validInt("Please choose second Salad 21-32: \n\tFor none, choose 0: ", 0, 21, 32));

				stockCheck(""+menu[choice]+" is out of stock, \n\tplease choose another Salad 21-32: \n\tFor none choose 0: ", stock, choice, 0, 21, 32);
			
			}
			if (choice !=0) 
			{
				sandwichIngredients.add(choice);
				stock[choice] -= stock[choice];
			}
			
			while (choice !=0)
			{
				choice = (MyVal.validInt("Would you like additional Salad (+35p) "
						+ "\n\tPlease choose 21-32 "
						+ "\n\tOr 0 for No: ", 0, 21, 32));

				stockCheck(""+menu[choice]+" is out of stock, \n\tplease choose another Salad 21-32: \n\tFor none choose 0: ", stock, choice, 0, 21, 32);
			
				if (choice !=0) 
				{
					sandwichIngredients.add(choice);
					stock[choice] -= stock[choice];
					sandwichPrice = (sandwichPrice + 0.35);

				}
			}

			My.p("\n\t0:    No Sauce");
			for (int i = 33; i<=37; i++)
			{
				if (stock[i] ==0) 
				{
					My.p("\n\t      Out of stock ("+menu[i]+")");
				}
				else My.p("\n\t"+i+":   "+menu[i]);
			}			
			System.out.print("\n");
			choice = (MyVal.validInt("Please choose Sauce 33-37: \n\tFor none, choose 0: ", 0, 33, 37));

			stockCheck(""+menu[choice]+" is out of stock, \n\tplease choose another Sauce 33-37: \n\tFor none choose 0: ", stock, choice, 0, 33, 37);
							
			
			if (choice !=0) 
			{
				sandwichIngredients.add(choice);
				stock[choice] -= stock[choice];
			}
			orders.add(sandwichIngredients);
			return sandwichPrice;
		}

		

		
		
		
		
	
		public static void checkout (ArrayList<ArrayList<Integer>> orders, ArrayList<Double> orderPrices, String[] menu, double totalPrice,
				ArrayList<ArrayList<Integer>> ordersKitchen, ArrayList<Double> orderPricesKitchen, ArrayList<String> orderNamesKitchen)	
		{
			int pay=0;
					
			System.out.printf("\n\t%-10s %10s", "price", "order");
			
			for (int i =0;  i < orders.size(); i++)
			{
			
				System.out.printf("\n\t£ %-10.2f",orderPrices.get(i));
				
				for (int j = 0; j< orders.get(i).size(); j++)
				{

					System.out.printf("%-1s %-1s",menu[orders.get(i).get(j)],", ");
				}
			
			
			
			}		
			System.out.printf("\n\t");		
			pay = MyVal.validInt("\n\tDo you want to Pay? 1: \n\t or cancel 0: ", 0, 1);

		
		}
		
		
		
		
		
		
		
		public static void menuA (String[]menu)
		{
			System.out.printf("\n\t%40s","Menu A:  Sandwiches");
			System.out.printf("\n\t%40s","--------------------");
			System.out.printf("\n\t");
			System.out.printf("\n\t£3  (includes one meat and 2 salad options)");
			System.out.printf("\n\t     +35p per additional salad options");
			System.out.printf("\n\t     +60p per additional meat options");
			System.out.printf("\n\t");

			System.out.printf("\n\t%-20s %-20s %-20s %-20s %-20s", "Breads", "Spreads", "Meats", "Salads", "Sauces");
			System.out.printf("\n\t-----------------------------------------------------------------------------------------------");
			System.out.printf("\n\t%-20s %-20s %-20s %-20s %-20s", menu[1], menu[9], menu[11], menu[21], menu[33]);
			System.out.printf("\n\t%-20s %-20s %-20s %-20s %-20s", menu[2], menu[10], menu[12], menu[22], menu[33]);
			System.out.printf("\n\t%-20s %-20s %-20s %-20s %-20s", menu[3], ""      , menu[13], menu[23], menu[34]);
			System.out.printf("\n\t%-20s %-20s %-20s %-20s %-20s", menu[4], ""      , menu[14], menu[24], menu[35]);
			System.out.printf("\n\t%-20s %-20s %-20s %-20s %-20s", menu[5], ""      , menu[15], menu[25], menu[36]);
			System.out.printf("\n\t%-20s %-20s %-20s %-20s", 	   menu[6], ""      , menu[16], menu[26], menu[37]);
			System.out.printf("\n\t%-20s %-20s %-20s %-20s", 	   menu[7], ""      , menu[17], menu[27], ""      );
			System.out.printf("\n\t%-20s %-20s %-20s %-20s", 	   menu[8], ""      , menu[18], menu[28], ""      );
			System.out.printf("\n\t%-20s %-20s %-20s %-20s", 	   ""     , ""      , menu[19], menu[29], ""      );
			System.out.printf("\n\t%-20s %-20s %-20s %-20s", 	   ""     , ""      , menu[20], menu[30], ""      );
			System.out.printf("\n\t%-20s %-20s %-20s %-20s",  	   ""     , ""      , ""      , menu[31], ""      );
			System.out.printf("\n\t%-20s %-20s %-20s %-20s", 	   ""     , ""      , ""      , menu[32], ""      );
			System.out.printf("\n\t");
			System.out.printf("\n\t");
			System.out.printf("\n\t");
		}

		public static void menuStock (String[]menu, int[]stock)
		{
			int choice = 1, amount = 0, item = 0;
			do
			{

				System.out.printf("\n\t%-4s %-20s %-6s %-4s %-20s %-6s %-4s %-20s %-6s","No", "stock name", "amount",  
									"No", "stock name", "amount",  "No", "stock name", "amount");
				
				for (int i=1; i<20; i++)
				{
					System.out.printf("\n\t%-4s %-20s %-6s %-4s %-20s %-6s %-4s %-20s %-6s", i, menu[i], stock[i],  i+20, 
										menu[i+20], stock[i+20],  i+40, menu[i+40], stock[i+40]); 
				}
				My.p("\n\n\tPlease choose from the options below");
				My.p("\n\t");
				My.p("\n\t0   Return to Main Page");
				My.p("\n\t1:  Restock individual items");
				My.p("\n\t2:  Restock Breads and Spread  (items 1-10)");
				My.p("\n\t3:  Restock Meats              (items 11-19)");
				My.p("\n\t4:  Restock Salads and Sauces  (items 20-37)");
				My.p("\n\t5:  Restock Hot Food           (items 38-56)");
				My.p("\n\t");

				choice = MyVal.validInt("Enter options 0-5: ", 0, 5);		
				
				if (choice ==1)
				{
					item = MyVal.validInt("Which item do you want to restock (1-56: )", 1, 56);
					amount = MyVal.validInt("Enter new stock amount (0-200): ", 0, 200);	
					stock[item] = amount;
					
				}
				if (choice ==2)
				{
					amount = MyVal.validInt("Enter amount of stock you want to add to existing (0-100): ", 0, 100);	
					for (int i =1; i<11; i++)
					{
						stock[i] = stock[i]+amount;
					}				
				}
				if (choice ==3)
				{
					amount = MyVal.validInt("Enter amount of stock you want to add to existing (0-100): ", 0, 100);	
					for (int i =11; i<19; i++)
					{
						stock[i] = stock[i]+amount;
					}				
				}
				if (choice ==4)
				{
					amount = MyVal.validInt("Enter amount of stock you want to add to existing (0-100): ", 0, 100);	
					for (int i =20; i<37; i++)
					{
						stock[i] = stock[i]+amount;
					}				
				}
				if (choice ==5)
				{
					amount = MyVal.validInt("Enter amount of stock you want to add to existing (0-100): ", 0, 100);	
					for (int i =38; i<56; i++)
					{
						stock[i] = stock[i]+amount;
					}				
				}
			}
			while (choice !=0);
					
		}
		public static int stockCheck (String msg, int[]stock, int check, int min, int max) 
		{
			while (stock[check] == 0)									//Stock check
			{
				My.p("\n\t"+msg);
				check = (MyVal.validInt("", min, max));
			}	
			return check;
			
		}
		public static int stockCheck (String msg, int[]stock, int check, int zero, int min, int max) 
		{
			while (stock[check] == 0)									//Stock check
			{
				My.p("\n\t"+msg);
				check = (MyVal.validInt("", zero, min, max));
			}	
			return check;
			
		}
/*		public static double testLoadOrder1 (ArrayList<ArrayList<Integer>> orders ,String[] menu, int[] stock)
		{
			sandwich.add(1);
			sandwich.add(9);
			sandwich.add(11);
			
			
			return 3.00;
		}
		public static double testLoadOrder2 (ArrayList<ArrayList<Integer>> orders ,String[] menu, int[] stock)
		{
			sandwich.add(2);
			sandwich.add(10);
			sandwich.add(12);
			sandwich.add(21);
			
			return 3.10;
		}
		public static double testLoadOrder3 (ArrayList<ArrayList<Integer>> orders ,String[] menu, int[] stock)
		{
			sandwich.add(3);
			sandwich.add(11);
			sandwich.add(14);
			sandwich.add(22);
			sandwich.add(23);
			
			return 2.35;
		}	
		*/	
}


