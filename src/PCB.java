public class PCB
{
	public int parent;
	public int first_child;
	public int younger_sibling;
	public int older_sibling;
	
	public PCB()
	{
		this.parent = -1;
		this.first_child = -1;
		this.younger_sibling = -1;
		this.older_sibling = -1;
	}
	
	
	public void display()
	{
		String displayParent = "";
		String displayFirstChild = "";
		String displayYoungerSibling = "";
		String displayOlderSibling = "";
		
		if(this.parent == -1)
		{
			displayParent = "None";
		}
		else
		{
			displayParent += this.parent;
		}
		if(this.first_child == -1)
		{
			displayFirstChild = "None";
		}
		else
		{
			displayFirstChild += this.first_child;
		}
		if(this.younger_sibling == -1)
		{
			displayYoungerSibling = "None";
		}
		else
		{
			displayYoungerSibling += this.younger_sibling;
		}
		if(this.older_sibling == -1)
		{
			displayOlderSibling = "None";
		}
		else
		{
			displayOlderSibling += this.older_sibling;
		}
		System.out.println("Parent: " + displayParent);
		System.out.println("First Child: " + displayFirstChild);
		System.out.println("Younger Sibling: " + displayYoungerSibling);
		System.out.println("Older Sibling: " + displayOlderSibling + "\n");
	}
}