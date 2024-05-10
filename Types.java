package hw3;

import java.util.ArrayList;
import java.util.Iterator;

public class Types
{
	public static ArrayList<Types> global_types_arr = new ArrayList<Types>();
	
	String type_id = null;
	int count = 0;
	int tmp_count;
	float prob;
	
	static Types add(Object type_ob)
	{
		Types to_return;
		String t_id = type_ob.toString();
		
		Iterator<Types> itr = global_types_arr.iterator();
		while(itr.hasNext())
		{
			to_return = (Types) itr.next();
			if(to_return.type_id.equals(t_id))
			{
				to_return.count++;
				return to_return;
			}
		}
		
		to_return = new Types(type_ob);
		global_types_arr.add(to_return);
		
		return to_return;
		
	}
	
	static Types get(String type_id)
	{
		Types to_return = null;
		
		Iterator<Types> itr = global_types_arr.iterator();
		while(itr.hasNext())
		{
			to_return = itr.next();
			if(to_return.type_id.equals(type_id))
				break;
		}
		
		return to_return;
	}
	
	
	private Types(Object type_ob)
	{
		this.type_id = type_ob.toString();
		count = 1;
	}

	
}
