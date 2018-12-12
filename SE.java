import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SE 
{
	protected static ArrayList<String> DefectiveClasses = new ArrayList<String>();
	protected static ArrayList<String> CleanClasses = new ArrayList<String>();
	public static void main(String[] args) throws IOException 
	{
		DefectTotal("//Users//christianvillegas//Documents//Year 3//CS3003//GitResults//Defects - One Line Updated.txt");
		Defects("//Users//christianvillegas//Documents//Year 3//CS3003//GitResults//Defects - Stat File Updated.txt");
		NoDefects("//Users//christianvillegas//Documents//Year 3//CS3003//GitResults//NoDefects - Stat File Updated.txt");
	}
	
	/**
	 * @DefectTotal: Method that iterates everytime there is a new line. The txt file is in oneline format.
	 * @popularDefects: Method that reads in log stat file of defects and prints out the java file that it corresponds with 
	 * @Figure2: Calculating the defect rate from the txt file gathered from git log --oneline --stat --grep "fix".
	 */

	protected static int DefectTotal(String filename) throws IOException
	{
		int Defects = 0;
		try(BufferedReader breader = new BufferedReader(new FileReader(filename))) 
		{
		    for(String line; (line = breader.readLine()) != null; ) 
		    {
		        Defects++;
		    }
		}
		System.out.println("Number of Defects: " + Defects);
		System.out.println();
		return Defects;
	}
	
	protected static void Defects(String filename) throws IOException
	{
		System.out.println("Gathering Java Class names from the defect text file");
		String java = ".java";
		Pattern p = Pattern.compile("(\\w+)([.!?])java"); //Regex pattern that finds any word before .java
		ArrayList<JavaClasses> javaclasses = new ArrayList<JavaClasses>();
		
		try(BufferedReader breader = new BufferedReader(new FileReader(filename))) 
		{
		    for(String line; (line = breader.readLine()) != null; ) 
		    {
		       if(line.toLowerCase().contains(java.toLowerCase())) //If the line contains a java file.
		       {
		    	   Matcher match = p.matcher(line); //Find a match in the line. 
		    	   if (match.find()) //If a match is found, check if it is already in the arraylist, if not add it. 
		   		   {
		    		   String checkClass = (match.group(1));
		    		   if(classContains(javaclasses, checkClass) != -1) //If it is not -1, then the index is where the occurance in the arraylist is. 
		    		   {
		    			   int index = classContains(javaclasses, checkClass); //Returns the index of the instance where the string is. 
		    			   javaclasses.get(index).iterate(); //Iterate the number of occurences of that Java class
		    		   }
		    		   else //If it is not currently in the arraylist, then add it to the arraylist.
		    		   {
		    			   JavaClasses newClass = new JavaClasses(checkClass, 1); 
		    			   DefectiveClasses.add(checkClass);
		    			   javaclasses.add(newClass);
		    		   }
		    		   //System.out.printf("'%s'", match.group(1));
		   		   }
		    	   //System.out.println(line);
		       }
		    }
		    printArray(javaclasses);
		    totalDefects(javaclasses);
		}
	}
	
	protected static void NoDefects(String filename) throws IOException
	{
		System.out.println("Reviewing all the files with no defects");
		String java = ".java";
		Pattern p = Pattern.compile("(\\w+)([.!?])java"); //Regex pattern that finds any word before .java
		
		try(BufferedReader breader = new BufferedReader(new FileReader(filename))) 
		{
		    for(String line; (line = breader.readLine()) != null; ) 
		    {
		       if(line.toLowerCase().contains(java.toLowerCase())) //If the line contains a java file.
		       {
		    	   Matcher match = p.matcher(line); //Find a match in the line. 
		    	   if (match.find()) //If a match is found, check if it is already in the arraylist, if not add it. 
		   		   {
		    		   String checkClass = (match.group(1));
		    		 
		    		   if(!DefectiveClasses.contains(checkClass) && !CleanClasses.contains(checkClass)) //If the file is not in the defective files array, then add it to the cleanclasses arraylist.
		    		   {
		    			   CleanClasses.add(checkClass);
		    		   }
		   		   }
		       }
		    }
		    //print(CleanClasses);
		}
	}
	protected static void printArray(ArrayList<JavaClasses> array) //Prints all the values in the custom arraylist.
	{
		for(int i = 0; i < array.size(); i++)
		{
			array.get(i).print();
		}	
	}
	
	protected static void print(ArrayList<String> array) //Prints all the values in the array.
	{
		for(int i = 0; i < array.size(); i++)
		{
			System.out.println(array.get(i).toString());
		}
	}
	
	protected static int classContains(ArrayList<JavaClasses> array, String thisClass) //Very inefficient way to check if an instance is in the JavaClasses arraylist.
	{
		int index = -1;
		for(int i = 0; i < array.size(); i++)
		{
			if(array.get(i).className.equals(thisClass))
			{
				index = i;
				break;
			}
		}
		return index;
	}
	
	protected static int totalDefects(ArrayList<JavaClasses> array)
	{
		int totalDefects = 0; 
		
		for(int i = 0; i < array.size(); i++)
		{
			totalDefects = totalDefects + array.get(i).getClassTotal();
		}
		System.out.println("Number of defective files: " + totalDefects);
		return totalDefects;
	}
}
