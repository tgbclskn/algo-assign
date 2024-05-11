package hw3;

import java.util.ArrayList;
import java.util.Iterator;

public class Type
{
	//Static field
	
	private static ArrayList<Type> global_type_arr = new ArrayList<Type>();
	
	static ArrayList<Type> getTypeArr()
	{
		return global_type_arr;
	}
	
	static Type add(Object type_ob) //Time complexity: n^2 in worst case
	{
		Type ret;
		
		String type_id;
		if(!(type_ob instanceof String))
			type_id = type_ob.toString();
		else
			type_id = (String) type_ob;
		
		Iterator<Type> itr = global_type_arr.iterator();
		while(itr.hasNext())
		{
			ret = (Type) itr.next();
			if(ret.type_id.equals(type_id))
			{
				ret.count_tmp++;
				return ret;
			}
		}
		
		ret = new Type(type_id);
		global_type_arr.add(ret);
		
		return ret;
	}
	
	
	//Dynamic object
	
	String type_id = null;
	int count_tmp = 0;
	float probability;
	
	private Type(String type_id)
	{
		this.type_id = type_id;
		count_tmp = 1;
	}
	
}
