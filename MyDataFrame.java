package project_2;

import java.util.*;

public class MyDataFrame {
	// arraylists to store the rows, column names and the data types for each column
	ArrayList<row> l;
	ArrayList<String> colnames;
	ArrayList<String> type;
	int ncol = 0;
	
	/**
	 * constructor of MyDataFrame object
	 * @param colname
	 */
	public MyDataFrame(ArrayList<String> colname) {
		l = new ArrayList<row>();
		type = new ArrayList<String>();
		colnames = colname;
		ncol = colname.size();
		// for each column, determine its type based on the name of the column
		for(int i = 0; i < colname.size(); i++) {
			if(colname.get(i).equals("count") || colname.get(i).equals("year")) {
				type.add("Integer");
			}
			else if(colname.get(i).equals("state") || colname.get(i).equals("gender") || colname.get(i).equals("name")) {
				type.add("String");
			}
		}
	}
	
	// method to add a row to the dataframe with input as a string array
	public void add_row(String[] s) {
		l.add(new row(s, type));
	}
	
	// alternative method to add a row with a row input
	public void add_row(row s) {
		l.add(s);
	}
	
	public MyDataFrame head(int n) {
		MyDataFrame res = new MyDataFrame(colnames);
		for(int i = 0; i < n; i++) {
			res.add_row(l.get(i));
		}
		return res;
	}
	
	public MyDataFrame tail(int n) {
		MyDataFrame res = new MyDataFrame(colnames);
		for(int i = l.size()-1; i > l.size()-n-1; i--) {
			res.add_row(l.get(i));
		}
		return res;
	}
	

	//directly extract the dtype of a column from the "type" arraylist
	public String dType(int index) {
		return type.get(index);
	}
	
	public String dType(String name) {
		return type.get(colnames.indexOf(name));
	}
	
	/**
	 * slicing a dataframe by index number
	 * @param index
	 * @return MyDataFrame df
	 */
	public MyDataFrame slice(int index) {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add(colnames.get(index));
		MyDataFrame res = new MyDataFrame(tmp);
		for(int i = 0; i < l.size(); i++) {
			// calling the get_index method in row class to get the specific value in column i
			Object temp = l.get(i).get_index(index);
			Object[] tmp_arr = new Object[] {temp};
			res.add_row(new row(tmp_arr));
		}
		return res;
	}
	
	public MyDataFrame slice(String name) {
		int index = colnames.indexOf(name);
		return this.slice(index);
	}
	
	/**
	 * slicing a dataframe by multiple index number
	 * @param indexArr
	 * @return
	 */
	public MyDataFrame slice(int[] indexArr){
		ArrayList<String> tmp = new ArrayList<String>();
		// extracting the colnames of the columns
		for(int i = 0 ; i < indexArr.length; i++) {
			tmp.add(colnames.get(indexArr[i]));
		}
		MyDataFrame res = new MyDataFrame(tmp);
		
		for(int i = 0; i < l.size(); i++) {
			Object[] temp = new Object[indexArr.length];
			for(int j = 0; j < indexArr.length; j++) {
				temp[j] = l.get(i).get_index(indexArr[j]);
			}
			res.add_row(new row(temp));
		}
		
		return res;
	}
	
	public MyDataFrame slice(String[] nameArr) {
		int[] indexArr = new int[nameArr.length];
		for(int i = 0; i < nameArr.length; i++) {
			indexArr[i] = colnames.indexOf(nameArr[i]);
		}
		return this.slice(indexArr);
	}
	
	public MyDataFrame loc(int index) {
		MyDataFrame res = new MyDataFrame(colnames);
		res.add_row(l.get(index));
		return res;
	}
	
	public MyDataFrame loc(int from, int to) {
		MyDataFrame res = new MyDataFrame(colnames);
		for(int i = from; i <= to; i++) {
			res.add_row(l.get(i));
		}
		return res;
	}
	
	public MyDataFrame loc(String label) {
		MyDataFrame res = new MyDataFrame(colnames);
		for(int i = 0; i < l.size(); i++) {
			if(l.get(i).label.equals(label)) {
				res.add_row(l.get(i));
			}
		}
		return res;
	}
	
	public MyDataFrame loc(String from, String to) {
		// dataframe to be returned
		MyDataFrame res = new MyDataFrame(colnames);
		int start = 0;
		int end = 0;
		// searching the first instance of the label "from"
		for(int i = 0; i < l.size(); i++) {
			if(l.get(i).label.equals(from)) {
				start = i;
				break;
			}
		}
		// searching the last instance of the label "to"
		for(int i = l.size()-1; i >= 0; i--) {
			if(l.get(i).label.equals(to)) {
				end = i;
				break;
			}
		}
		
		// get all the rows between from and to
		for(int i = start; i <= end; i++) {
			res.add_row(l.get(i));
		}
		
		return res;
	}
	
	
	public MyDataFrame sort(int index) {
		MyDataFrame res = new MyDataFrame(colnames);
		ArrayList<row> copy = new ArrayList<row>();
		for(int i = 0; i < l.size(); i++) {
			copy.add(l.get(i));
		}
		// differentiating sorting strategy based on the type of the column
		if(type.get(index).equals("Integer")) {
		// overriding the sorting function to sort based on the specific index of the row
		Collections.sort(copy, new Comparator<row>(){
			@Override
			public int compare(row t1, row t2) {
			 return ((Integer) t1.l.get(index)).compareTo((Integer) t2.l.get(index));
			}
			});
		}
		else if(type.get(index).equals("String")) {
			Collections.sort(copy, new Comparator<row>(){
				@Override
				public int compare(row t1, row t2) {
				 return ((String) t1.l.get(index)).compareTo((String) t2.l.get(index));
				}
				});
		}
		for(int i = 0; i < copy.size(); i++) {
			res.add_row(copy.get(i));
		}
		return res;
		
	}
	

	public MyDataFrame sort(String name) {
		int index = colnames.indexOf(name);
		return this.sort(index);
	}

	public void print() {
		for(int i = 0; i < l.size(); i++) {
			l.get(i).print_row();
			System.out.println();
		}
	}
	
	public MyDataFrame filter(String col, String op, Object o){
	    MyDataFrame res = new MyDataFrame(colnames); // keepers
	    ArrayList<row> copy = new ArrayList<row>(); // temp copy
	    for(int i = 0; i < l.size(); i++){
	        copy.add(l.get(i));
	    }
	    int column_index = colnames.indexOf(col);
	    // differentiating the filtering based on the type of the column being filtered
	    if(type.get(column_index).equals("Integer")){
	        int filter_number = (int) o;
	        if(op.equals("==")){
	            for(int i = 0; i < l.size(); i++){
	                if((int)copy.get(i).get_index(column_index) == filter_number){
	                    res.add_row(copy.get(i));
	                }
	            }
	        }else if(op.equals("!=")){
	            for(int i = 0; i < l.size(); i++){
	                if((int)copy.get(i).get_index(column_index) != filter_number){
	                    res.add_row(copy.get(i));
	                }
	            }
	        }else if(op.equals(">")){
	            for(int i = 0; i < l.size(); i++){
	                if((int)copy.get(i).get_index(column_index) > filter_number){
	                    res.add_row(copy.get(i));
	                }
	            }
	        }else if(op.equals("<")){
	            for(int i = 0; i < l.size(); i++){
	                if((int)copy.get(i).get_index(column_index) < filter_number){
	                    res.add_row(copy.get(i));
	                }
	            }
	        }else if(op.equals(">=")){
	            for(int i = 0; i < l.size(); i++){
	                if((int)copy.get(i).get_index(column_index) >= filter_number){
	                    res.add_row(copy.get(i));
	                }
	            }
	        }else if(op.equals("<=")){
	            for(int i = 0; i < l.size(); i++){
	                if((int)copy.get(i).get_index(column_index) <= filter_number){
	                    res.add_row(copy.get(i));
	                }
	            }
	        }
	    }else if(type.get(column_index).equals("String")){
	    	// only two comparison methods available for string type
	        String filter_string = (String) o;
	        if(op.equals("==")){
	            for(int i = 0; i < l.size(); i++){
	                if(copy.get(i).get_index(column_index).equals(filter_string)){
	                    res.add_row(copy.get(i));
	                }
	            }
	        }else if(op.equals("!=")){
	            for(int i = 0; i < l.size(); i++){
	                if(!copy.get(i).get_index(column_index).equals(filter_string)){
	                    res.add_row(copy.get(i));
	                }
	            }
	        }
	    }
	    return(res);
	}
	
	
	public Object getMax(int index){
		Object maximumValue = "";
		// based on the type of the column being aggregated, cast the proper type to the variable for aggregation
	    if(type.get(index).equals("Integer")){
	        List<Integer> allValues = new ArrayList<>();
	        for(int i = 0; i<l.size(); i++){
	        	// casting the value to an int
	            int temp = (int)l.get(i).get_index(index);
	            allValues.add(temp);
	        }
	        maximumValue = Collections.max(allValues);
	    }
	    else if(type.get(index).equals("String")){
	        List<String> allValues = new ArrayList<>();
	        for(int i = 0; i<l.size(); i++){
	        	// casting the value to a string
	            String temp = (String)l.get(i).get_index(index);
	            allValues.add(temp);
	        }
	        maximumValue = Collections.max(allValues);
	    }
	    return maximumValue;
	}

	public Object getMin(int index){
		Object minimumValue = "";
	    if(type.get(index).equals("Integer")){
	        List<Integer> allValues = new ArrayList<>();
	        for(int i = 0; i<l.size(); i++){
	            int temp = (int)l.get(i).get_index(index);
	            allValues.add(temp);
	        }
	        minimumValue = Collections.min(allValues);
	    }
	    else if(type.get(index).equals("String")){
	        List<String> allValues = new ArrayList<>();
	        for(int i = 0; i<l.size(); i++){
	            String temp = (String)l.get(i).get_index(index);
	            allValues.add(temp);
	        }
	        minimumValue = Collections.min(allValues);
	    }
	    return minimumValue;
	}
	
	public Object getMax(String label){
		int index = colnames.indexOf(label);
		return getMax(index);
	}
	
	public Object getMin(String label){
		int index = colnames.indexOf(label);
		return getMin(index);
	}
	
	public String toString() {
		String res = "";
		for(int i = 0; i < colnames.size()-1; i++) {
			res += colnames.get(i);
			res += ",";
		}
		res += colnames.get(colnames.size()-1);
		res += "\n";
		for(int i = 0; i < l.size(); i++) {
			res += l.get(i).toString();
			res += "\n";
		}
		return res;
	}
	
}
