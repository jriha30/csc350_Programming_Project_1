import java.time.Clock;

public class driver
{
	// IMPORTANT NOTE!!!!!!
	// PROCESSES CAN ONLY HAVE A MAXIMUM OF 3 CHILD PROCESSES
	
	
	
	static int n = 25;
	static PCB[] arrayPCB = new PCB[n];
	static int recursionCounter = -1;
	
	public static void create(PCB p)
	{
		int p_Loc = -1;
		int q_Loc = -1;
		
		// start get parent location
		for(int i = 0; i < arrayPCB.length; i++)
		{
			if(arrayPCB[i] == p)
			{
				p_Loc = i;
			}
		}
		// end get parent location
		
		// start get first empty index
		for(int i = 0; i < arrayPCB.length; i++)
		{
			if(arrayPCB[i] == null)
			{
				q_Loc = i;
				break;
			}
		}
		// end get first empty index
		
		PCB q = new PCB();
		
		// start set parent
		q.parent = p_Loc;
		// end set parent
		
		// start set first child
		if(p.first_child == -1)
		{
			p.first_child = q_Loc;
		}
		
		else if(arrayPCB[p.first_child].younger_sibling == -1)
		{
			arrayPCB[p.first_child].younger_sibling = q_Loc;
			q.older_sibling = p.first_child;
		}
		
		else if(arrayPCB[arrayPCB[p.first_child].younger_sibling].younger_sibling == -1)
		{
			arrayPCB[arrayPCB[p.first_child].younger_sibling].younger_sibling = q_Loc;
			q.older_sibling = arrayPCB[p.first_child].younger_sibling;
		}
		// end set first child and siblings

		arrayPCB[q_Loc] = q;
	}
	
	public static void destroy(PCB p)
	{
		recursionCounter++;
		int p_Location = -1;
		for(int i = 0; i < arrayPCB.length; i++)
		{
			if(arrayPCB[i] == p)
			{
				p_Location = i;
				break;
			}
		}
		if(p.first_child != -1 || p.younger_sibling != -1)
		{

			if(recursionCounter != 0)
			{
				if(arrayPCB[p_Location].younger_sibling != -1)
				{
					destroy(arrayPCB[p.younger_sibling]);
				}
				if(arrayPCB[p_Location].first_child != -1)
				{
					destroy(arrayPCB[p.first_child]);
				}
			}
			else
			{
				if(arrayPCB[p_Location].first_child != -1)
				{
					destroy(arrayPCB[p.first_child]);
				}
			}
		}
			
		if(p.younger_sibling != -1 && arrayPCB[p.younger_sibling] != null)
		{
			arrayPCB[p.younger_sibling].older_sibling = -1;
			
		}
		if(arrayPCB[p_Location].parent != -1)
		{
			if(arrayPCB[p.parent].first_child == p_Location)
			{
				arrayPCB[p.parent].first_child = p.younger_sibling;
			}
		}
		arrayPCB[p_Location].parent = -1;
		arrayPCB[p_Location].first_child = -1;
		arrayPCB[p_Location].younger_sibling = -1;
		arrayPCB[p_Location].older_sibling = -1;
		arrayPCB[p_Location] = null;
	}
	
	public static void main(String[] args)
	{
		int[] timeArray = new int[100];
		int totalPreAverage = 0;
		
		for(int i = 0; i < timeArray.length; i++)
		{
			Clock clock = Clock.systemDefaultZone();
			long timeBegin = clock.millis();
			for(int j = 0; j < 10000000; j++)
			{
				recursionCounter = -1;
				arrayPCB[0] = new PCB();
				create(arrayPCB[0]);
				create(arrayPCB[0]);
				create(arrayPCB[1]);
				create(arrayPCB[1]);
				create(arrayPCB[2]);
				create(arrayPCB[0]);
				create(arrayPCB[3]);
				destroy(arrayPCB[1]);
				create(arrayPCB[0]);
				create(arrayPCB[1]);
				destroy(arrayPCB[3]);
				destroy(arrayPCB[0]);
			}
			long timeEnd = clock.millis();
			int totalTime = (int) (timeEnd - timeBegin);
			timeArray[i] = totalTime;
			totalPreAverage += totalTime;
		}
		System.out.println(totalPreAverage / timeArray.length);
	}
}