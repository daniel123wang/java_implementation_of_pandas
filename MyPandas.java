package project_2;

import java.io.*;
import java.util.*;

public class MyPandas {
	
	public MyPandas() {};
	/**
	 * read csv file to create MyDataFrame object without indexing
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public MyDataFrame readCSV(String path) throws IOException {
		String line;
		BufferedReader csv_read = new BufferedReader(new FileReader(path));
		String header = csv_read.readLine();
		String[] headers = header.split(",");
		ArrayList<String> col = new ArrayList<String>();
		// read the header of the csv to get the columns included in the dataframe
		for(int i = 0; i < headers.length; i++) {
			col.add(headers[i]);
		}
		MyDataFrame res = new MyDataFrame(col);
		
		// adding in data one row at a time 
		while ((line = csv_read.readLine()) != null) {
		    String[] data = line.split(",");
		    res.add_row(data);
		}
		
		csv_read.close();
		return res;
	}
	
	/**
	 * write the MyDataFrame object into a csv file
	 * @param data
	 * @param path
	 * @throws IOException
	 */
	public void writeCSV(MyDataFrame data, String path) throws IOException {
		FileWriter csvWriter = new FileWriter(path);
		ArrayList<String> colname = data.colnames;
		csvWriter.append(String.join(",", colname));
		csvWriter.append("\n");
		for(int i = 0; i < data.l.size(); i++) {
			csvWriter.append(data.l.get(i).toString());
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();
	}
	
	/**
	 * concatenate two MyDataFrame objects, returning a new dataframe
	 * @param df1
	 * @param df2
	 * @return MyDataFrame
	 */
	public MyDataFrame concat(MyDataFrame df1, MyDataFrame df2) {
		MyDataFrame res = new MyDataFrame(df1.colnames);
		for(int i = 0; i < df1.l.size(); i++) {
			res.add_row(df1.l.get(i));
		}
		for(int i = 0; i < df2.l.size(); i++) {
			res.add_row(df2.l.get(i));
		}
		
		return res;
	}
	
}
