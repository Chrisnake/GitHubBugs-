

public class JavaClasses 
{
	public String className;
	public int classTotal;
	
	/**
	 * @JavaClasses: Handles all instances of Java Classes that have been gathered from defects log stat text file 
	 * @iterate: Iterate method iterates the total number of times the class has appeared to show the amount of defects it has.
	 * @print: Print method prints the instance. 
	 */
	
	JavaClasses(String c, int t)
	{
		this.className = c;
		this.classTotal = t;
	}
	
	public int getClassTotal()
	{
		return classTotal;
	}
	
	public void iterate()
	{
		classTotal++;
	}
	
	public void print()
	{
		System.out.println(className + ": " + classTotal + " defects");
	}
	
}
