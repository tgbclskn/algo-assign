package hw3;

public class Main {
	
	public static void main(String[] argv)
	{
		
		Judges.create(3);
		
		/*Types[] tarr = {Types.add(7), Types.add(1), Types.add(7), 
						Types.add(6), Types.add(3), Types.add(2), 
						Types.add(1), Types.add(3), Types.add(6), 
						Types.add(5), Types.add(7), Types.add(6), };*/
		
		/*Types[] tarr = {Types.add(1), Types.add(3), Types.add(4), 
				Types.add(2), Types.add(1), Types.add(2), 
				Types.add(3), Types.add(4), Types.add(1), };*/
		
		/*Types[] tarr = {Types.add(2), Types.add(1), Types.add(3), 
				Types.add(2), Types.add(1),};*/
		
		Types[] tarr = {Types.add(1), Types.add(1), Types.add(1), 
				Types.add(1), Types.add(1),};
		int c = Judges.selBestAdd(tarr);
		
		
		System.out.print("All Types:\n");
		for(Types t : Types.global_types_arr)
		{
			System.out.print("Type(" + t.type_id + ") | ");
		}
		System.out.print("\n\n\n");
		int i = 0;
		for(Judges j : Judges.global_judges_arr)
		{
			i++;
			System.out.print("Judge " + i + ": \n");
			for(Types t : j.type_arr)
			{
				System.out.print("Type(" + t.type_id + ") | ");
			}
			System.out.print("\n");
		}
		
		System.out.print("\nCost: " + c);
		
		System.exit(0);
		
		
	}


	
	
	

}


