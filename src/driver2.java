import java.time.Clock;

public class driver2
{
	static int theFrickRecursionCounter = 0;
	static int n = 25;
	static PCB2[] arrayPCB = new PCB2[n];
	
	public static void create(PCB2 p)
	{
	
		
		int p_Location = -1;
		
		// start free PCB
		int freePCB_Location = -1;
		for(int i = 0; i < arrayPCB.length; i++)
		{
			if(arrayPCB[i] == null)
			{
				freePCB_Location = i;
				break;
			}
			if(arrayPCB[i] == p)
			{
				p_Location = i;
			}
		}
		PCB2 q = new PCB2();
		q.parent = p_Location;
		p.children.add(freePCB_Location);
		arrayPCB[freePCB_Location] = q;
	}
	
	public static void destroy(PCB2 p)
	{	
		int p_Location = -1;
		
		
		for(int i = 0; i < arrayPCB.length; i++)
		{
			if(arrayPCB[i] == p)
			{
				p_Location = i;
				break;
			}
		}
		if(p.children.size() != 0)
		{
			for(int i = p.children.size() - 1; i > -1 ; i--)
			{
				
				destroy(arrayPCB[p.children.get(i)]);
			}
		}
		
		int frickRecursionThingIGaveUpOnNamingVariables = -1;
		int currentChild = -1;
		
		if(arrayPCB[p_Location].parent != -1)
		{
			for(int i = 0; i < arrayPCB[arrayPCB[p_Location].parent].children.size(); i++)
			{
				if(p_Location == arrayPCB[arrayPCB[p_Location].parent].children.get(i))
				{
					frickRecursionThingIGaveUpOnNamingVariables = i;
					currentChild = arrayPCB[arrayPCB[p_Location].parent].children.get(i);
				}
			}
			arrayPCB[arrayPCB[p_Location].parent].children.remove(frickRecursionThingIGaveUpOnNamingVariables);
		}
		
		arrayPCB[p_Location].parent = -1;
		arrayPCB[p_Location].children.clear();
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
				theFrickRecursionCounter = 0;
				arrayPCB[0] = new PCB2();
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