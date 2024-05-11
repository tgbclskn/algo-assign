package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	/*
	*	Total time complexity:
	*	
	*	m = judge count
	*	n = type count
	*
	*	Type array -> m * n^2
	*	Single type -> m * n
	*/
	public static void main(String[] argv)
	{
		Judge.create(3);
		Type[] tarr = null;
		
		/*tarr = {Type.add(7), Type.add(1), Type.add(7), 
						Type.add(6), Type.add(3), Type.add(2), 
						Type.add(1), Type.add(3), Type.add(6), 
						Type.add(5), Type.add(7), Type.add(6), };*/
		
		/*tarr = {Type.add(1), Type.add(3), Type.add(4), 
				Type.add(2), Type.add(1), Type.add(2), 
				Type.add(3), Type.add(4), Type.add(1), };*/
		
		/*tarr = {Type.add(2), Type.add(1), Type.add(3), 
				Type.add(2), Type.add(1),};*/
		
		/*tarr = {Type.add(1), Type.add(1), Type.add(1), 
				Type.add(1), Type.add(1),};*/
		
		
	  /*Judge.place(Type.add(7));
		Judge.place(Type.add(1));
		Judge.place(Type.add(7));
		Judge.place(Type.add(6));
		Judge.place(Type.add(3));
		Judge.place(Type.add(2));
		Judge.place(Type.add(1));
		Judge.place(Type.add(3));
		Judge.place(Type.add(6));
		Judge.place(Type.add(5));
		Judge.place(Type.add(7));
		Judge.place(Type.add(6));*/
		
		
		tarr = getFromFile("input.txt");
		
		int cost = Judge.place(tarr);
		
		System.out.print("All Types:\n");
		for(Type t : Type.getTypeArr())
		{
			System.out.print("\"" + t.type_id + "\" | ");
		}
		System.out.print("\n\n");
		
		int i = 0;
		for(Judge j : Judge.getJudgeArr())
		{
			i++;
			System.out.print("Judge " + i + ": \n");
			for(Type t : j.getTypeArr())
			{
				System.out.print('"' + t.type_id + "\" | ");
			}
			System.out.print("\n\n");
		}
		
		System.out.print("Cost: " + cost);
		
		System.exit(0);
	}
	
	
	static Type[] getFromFile(String filename) //Time complexity: 2 * n
	{
		File file = new File(filename);
		Type[] ret = null;
		Scanner sc = null;
		int line_count = 0;
	
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file);
			System.exit(0);
		}
		
		while(sc.hasNextLine())
		{
			String next_line_stripped = sc.nextLine().strip();
			if(!next_line_stripped.startsWith("Type"))
				continue;
			line_count++;
		}
		
		ret = new Type[line_count];
		int i = 0;
		
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {}
		
		while(sc.hasNextLine())
		{
			String next_line_stripped = sc.nextLine().strip();
			if(!next_line_stripped.startsWith("Type"))
				continue;
			ret[i++] = Type.add(next_line_stripped);
		}
		
		return ret;
	}

}


