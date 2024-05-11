package hw3;

import java.util.ArrayList;
import java.util.Iterator;

public class Judge 
{
	//Static field
	
	private static ArrayList<Judge> global_judge_arr = new ArrayList<Judge>();
	
	static void create(int count) //Time complexity: m
	{
		for(int i = 0; i < count; i++)
			global_judge_arr.add(new Judge());
	}
	
	//Place an array of types
	//return total amount of cost change
	static int place(Type[] type) //Time complexity: m * n^2
	{
		if(type.length == 1)
			if(place(type[0]))
				return 1;
		
		int cost = 0;
		
		//Iterate user provided array and assign probability score to each of them
		for(int i = 0; i < type.length; i++)
		{			
			Type t = type[i];
			
			/*
			 * Find distance to the next same type and calculate probability score
			 * 
			 * */
			for(int j = i+1; j < type.length; j++)
			{	
				Type t2 = type[j];
				
				//System.out.print("\ni: " + i + "\nlength left: " + (type.length-i) + "\nj: " + j + "\ntype: " + t.type_id + "\ntype2: " + t2.type_id + "\n\n");
				
					if(t.type_id == t2.type_id)
					{
						float mul = (float)((type.length-i)-(j-i-1))/(float)(type.length - i);
						t.probability = t.count_tmp * mul;
						//System.out.print("Same\n@\n@mul: " + mul + " | probability: " + t.probability + "\n@\n\n");
						break;
					}
					
					if(j == type.length-1)
					{
						float mul = 1 / (float)(type.length - i);
						t.probability = mul;
						//System.out.print("\n@\n@mul: " + mul + " | probability: " + t.probability + "\n@\n\n");
					}
			}
			
			//increase cost if no same getLastTypeId() found
			if(place(t))
				cost++;
		}
		
		return cost;
	}
	
	//Place single type, no probability if called directly
	//return true if cost is incremented
	static boolean place(Type type) //Time complexity: m
	{	
		float lowest_probability = Float.MAX_VALUE;
		Judge lowest_prio_judge = null;
		
		Iterator<Judge> itr = global_judge_arr.iterator();
		while(itr.hasNext())
		{
			Judge judge = itr.next();
			if(judge.getLastTypeId() == null)
			{
				lowest_prio_judge = judge;
				lowest_probability = 0;
			}
			else if(judge.getLastTypeId().equals(type.type_id))
			{
				type.count_tmp--;
				judge.type_arr.add(type);
				return false;
			}
			else
			{
				float probability_of_last_type = judge.getLastProbability();
				if(probability_of_last_type < lowest_probability)
				{
					lowest_probability = probability_of_last_type;
					lowest_prio_judge = judge;
				}
			}
		}
		
		lowest_prio_judge.type_arr.add(type);
		type.count_tmp--;
		return true;
	}
	
	static ArrayList<Judge> getJudgeArr()
	{
		return global_judge_arr;
	}
	
	
	//Dynamic object
	
	private ArrayList<Type> type_arr;
	
	private Judge()
	{
		type_arr = new ArrayList<Type>();
	}
	
	private String getLastTypeId()
	{
		Iterator<Type> itr = type_arr.iterator();
		if(itr.hasNext())
		{
			return type_arr.get(type_arr.size()-1).type_id;
		}
		else
			return null;
	}
	
	private float getLastProbability()
	{
		Iterator<Type> itr = type_arr.iterator();
		if(itr.hasNext())
		{
			return type_arr.get(type_arr.size()-1).probability;
		}
		else
			return -1;
	}
	
	ArrayList<Type> getTypeArr()
	{
		return type_arr;
	}
	
}
