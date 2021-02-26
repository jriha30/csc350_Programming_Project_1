import java.util.ArrayList;

public class PCB2
{
	public int parent;
	public ArrayList<Integer> children = new ArrayList<Integer>();
	
	public PCB2()
	{
		this.parent = -1;
	}
	
	
	
	
	
	public void display()
	{
		String displayParent = "";
		
		
		if(this.parent == -1)
		{
			displayParent = "None";
		}
		else
		{
			displayParent += this.parent;
		}
		
		System.out.println("Parent: " + displayParent);
		System.out.println("Children: " + this.children + "\n");
		
		
	}
	
	
	
	
	
	
	
}
