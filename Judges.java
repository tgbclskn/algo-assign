package hw3;

import java.util.ArrayList;
import java.util.Iterator;

public class Judges {
	
	public static ArrayList<Judges> global_judges_arr = new ArrayList<Judges>();
	
	public ArrayList<Types> type_arr;
	
	static void create(int count)
	{
		for(int i = 0; i < count; i++)
			global_judges_arr.add(new Judges());
	}
	
	static int selBestAdd(Types[] type)
	{
		if(type.length == 1)
			if(selBestAdd(type[0]))
				return 1;
		
		int cost = 0;
		
		for(int i = 0; i < type.length; i++)
		{
			type[i].tmp_count = type[i].count;
		}
		
		for(int i = 0; i < type.length; i++)
		{			
			//System.out.println("i: " + i);
			Types t = type[i];
			//System.out.println("Type: " + t.type_id);
			int j = i+1;
			//System.out.println("j: " + j);
		//	System.out.println("Type length left: " + (type.length-i));
		//	System.out.println("While: ");
			while(j < type.length)
			{
			//	System.out.print("j: " + j + " | ");
			//	System.out.print("type_id:" + t.type_id + " == type_id:" + type[j].type_id + "?  @  ");
				if(t.type_id == type[j].type_id)
				{
					float mul = (float)((type.length-i)-(j-i-1))/(float)(type.length - i);
				//	System.out.println("\nt.count: " + t.tmp_count);
				//	System.out.println("mul: " + mul);
					t.prob = (t.tmp_count--) * mul;
				//	System.out.println("t.prob: " + t.prob);
					break;
				}
				j++;
			}
			if(j == type.length)
			{
				t.prob = 1 / (float)(type.length - i);
			//	System.out.println("\nt.prob: " + t.prob);
			}
				//System.out.print("\n\n");
			
			if(selBestAdd(t))
				cost++;
		}
		
		/*System.out.print("All Types:\n");
		for(Types t : Types.global_types_arr)
		{
			System.out.print("Type(" + t.type_id + ")(count:" + t.count + ")(prob: " + t.prob + ")  |  ");
		}*/
		return cost;
	}
	
	static boolean selBestAdd(Types type)
	{
		
		boolean cost = false;
		//int lowest_count = Integer.MAX_VALUE;
		float lowest_prob = Float.MAX_VALUE;
		Judges lowest_prio_judge = null;
		
		Iterator<Judges> itr = global_judges_arr.iterator();
		while(itr.hasNext())
		{
			Judges judge = itr.next();
			if(judge.getTypeId() == null)
			{
				lowest_prio_judge = judge;
				lowest_prob = 0;
			}
			else if(judge.getTypeId().equals(type.type_id))
			{
				type.count--;
				judge.type_arr.add(type);
				return false;
			}
			else
			{
				//int count = judge.getLastCount();
				float jprob = judge.getLastProb();
				if(jprob < lowest_prob)
				{
					lowest_prob = jprob;
					lowest_prio_judge = judge;
				}
			}
		}
		
		lowest_prio_judge.type_arr.add(type);
		type.count--;
		//System.out.println("Judge " + lowest_prio_judge.hashCode() + " add " + type.type_id);
		return true;
	}
	
	private String getTypeId()
	{
		Iterator<Types> itr = type_arr.iterator();
		if(itr.hasNext())
		{
			return type_arr.get(type_arr.size()-1).type_id;
		}
		else
			return null;
	}
	
	private int getLastCount()
	{
		Iterator<Types> itr = type_arr.iterator();
		if(itr.hasNext())
		{
			return type_arr.get(type_arr.size()-1).count;
		}
		else
			return -1;
	}
	
	private float getLastProb()
	{
		Iterator<Types> itr = type_arr.iterator();
		if(itr.hasNext())
		{
			return type_arr.get(type_arr.size()-1).prob;
		}
		else
			return -1;
	}
	
	
	private Judges()
	{
		type_arr = new ArrayList<Types>();
	}

}
