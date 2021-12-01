package general;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyVal
{
	static Scanner key = new Scanner(System.in);

	public static int validInt(String msg, int min, int max) //Limits for int are -2147483648 to 2147483647, otherwise InputMismatchException
	{
		int num = 0;
		boolean ok;
		do 
		{
			try
			{
				ok = true;
				My.p("\n\t"+msg);
				num = key.nextInt();
				key.nextLine();
				
				if(num<min || num>max)
				{
					ok=false;
					My.p(String.format("\n\tError: Range is %2d-%2d.  Please re-enter.", min, max));	
				}
						
			}
			catch (InputMismatchException e)
			{
				My.p("\n\tError: Illegal character(s) in input - Please re-enter.");
				if (min==-2147483648 || max== 2147483647)
				My.p("\n\tValid range is "+min+" to" +max);
				key.nextLine();
				ok = false;
			}
			
			
		}while(!ok);
		return num;
	}
	public static int validInt(String msg, int zero, int min, int max) //Limits for int are -2147483648 to 2147483647, otherwise InputMismatchException
	{
		int num = 0;
		boolean ok;
		do 
		{
			try
			{
				ok = true;
				My.p("\n\t"+msg);
				num = key.nextInt();
				key.nextLine();
				
				if(num!=0 && num<min || num>max)
				{
					ok=false;
					My.p(String.format("\n\tError: Options are %2d or range %2d-%2d.  Please re-enter.",zero, min, max));	
				}
						
			}
			catch (InputMismatchException e)
			{
				My.p("\n\tError: Illegal character(s) in input - Please re-enter.");
				if (min==-2147483648 || max== 2147483647)
				My.p("\n\tValid range is "+min+" to" +max+" or "+zero);
				key.nextLine();
				ok = false;
			}
			
			
		}while(!ok);
		return num;
	}
	
	
	
	public static double validDouble(String msg, double min, double max)
	{
		double num = 0;
		boolean ok;
		do 
		{
			try
			{
				ok = true;
				My.p("\n\t"+msg);
				num = key.nextDouble();
				key.nextLine();
				
				if(num<min || num>max)
				{
					ok=false;
					My.p(String.format("\n\tError: Range is %2d-%2d.  Please re-enter.", min, max));	
				}
						
			}
			catch (InputMismatchException e)
			{
				My.p("\n\tError: Illegal character(s) in input - Please re-enter.");
				key.nextLine();
				ok = false;
			}
			
			
		}while(!ok);
		return num;
	}
			

}
