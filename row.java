package project_2;

import java.util.*;

public class row {
	// arraylist of objects to store the different entries within a row
	ArrayList<Object> l = new ArrayList<Object>();
	// label of the row, default to the value in the first column
	String label = null;

	public row(String[] s, ArrayList<String> type) {
		for(int i = 0; i < s.length; i++) {
			// for the first entry in the row, set the row label equal to it
			if(i==0) {
			if(type.get(i).equals("Integer")) {
				l.add(Integer.parseInt(s[i]));
				label = s[i];
			}
			else if(type.get(i).equals("String")) {
				l.add(s[i]);
				label = s[i];
			}
		}
			else {
				// parse in the entry for each entry of the row based on its type
				if(type.get(i).equals("Integer")) {
					l.add(Integer.parseInt(s[i]));
				}
				else if(type.get(i).equals("String")) {
					l.add(s[i]);
				}
			}
		}
	}
	
	// constructor of row object which takes in an array of objects
	public row(Object[] s) {
		for(int i = 0; i < s.length; i++) {
			l.add(s[i]);
		}
	}
	
	// get a specific entry in a row
	public Object get_index(int i) {
		return l.get(i);
	}
	
	public void print_row() {
		for(int i = 0; i < l.size(); i++) {
			System.out.print(l.get(i) + " ");
		}
	}
	
	// overiding the print method for row
	public String toString() {
		String res = "";
		for(int i = 0; i < l.size()-1; i++) {
			res += (l.get(i) + ",");
		}
		res += (l.get(l.size()-1));
		return res;
	}
	
	
	
	

}
