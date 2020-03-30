package project_2;

import java.io.IOException;
import java.io.FileWriter;

public class test {
    public static void main(String[] args) {
        try {
            String outputFile = "./output.txt";
            FileWriter outputWriter = new FileWriter(outputFile);
            outputWriter.append("TEST OUTPUT - MSIA 422 PROJECT 2 - DANIEL WANG, SAFIA KHOUJA\n\n\n");
            outputWriter.append("*** TESTING MyPandas *** \n\n");
            MyPandas pd = new MyPandas();
            outputWriter.append("TEST 1: Read in a test CSV file (test.csv) to test the readCSV function \n");
            MyDataFrame testDf = pd.readCSV("./test.csv");
            outputWriter.append("CSV file read successfully \n\n");
            outputWriter.append("Checking the first 5 rows of the csv file: \n\n");
            MyDataFrame headTestDf = testDf.head(5);
            outputWriter.append(headTestDf.toString());
            outputWriter.append("\n\n\n");
            
            outputWriter.append("Checking the last 5 rows of the csv file: \n\n");
            MyDataFrame tailTestDf = testDf.tail(5);
            outputWriter.append(tailTestDf.toString());
            outputWriter.append("\n\n\n");
            
            outputWriter.append("TEST 2: Write 5 rows to a CSV file to test the writeCSV function \n");
            pd.writeCSV(headTestDf, "output.csv");
            outputWriter.append("CSV file (output.csv) successfully written to working directory \n\n");
            
            
            outputWriter.append("TEST 3: Concatenate two MyDataFrame objects along rows to test the concat function \n");
            MyDataFrame concatTestDf = pd.concat(headTestDf, tailTestDf);
            pd.writeCSV(concatTestDf, "concat.csv");
            outputWriter.append("CSV file (concat.csv) contains the head of the test file concatenated with the tail of the file\n\n");
            outputWriter.append("\nResulting DataFrame = \n" + concatTestDf.toString());

            outputWriter.append("\n\nTEST 4: Slicing only the third column \n\n");
            MyDataFrame slicing = testDf.slice(2);
            outputWriter.append(slicing.toString() + "\n\n");
            
            outputWriter.append("\n\nTEST 5: Slicing the third column using column name \n\n");
            MyDataFrame slicing_name = testDf.slice("year");
            outputWriter.append(slicing_name.toString() + "\n\n");
            
            outputWriter.append("\n\nTEST 6: Slicing the first and fourth column using indices \n\n");
            int[] ind = {0, 3};
            MyDataFrame slicing_mult_ind = testDf.slice(ind);
            outputWriter.append(slicing_mult_ind.toString() + "\n\n");
            
            outputWriter.append("\n\nTEST 7: Slicing the first and fourth column using strings \n\n");
            String[] ind_str = {"state", "name"};
            MyDataFrame slicing_mult_str = testDf.slice(ind_str);
            outputWriter.append(slicing_mult_str.toString() + "\n\n");
            
            outputWriter.append("\n\nTEST 8: DType of column 2 by index \n\n");
            outputWriter.append(testDf.dType(1) + "\n\n");
            
            outputWriter.append("\n\nTEST 9: DType of column 2 by label \n\n");
            outputWriter.append(testDf.dType("gender") + "\n\n");
            
            outputWriter.append("\n\nTEST 10: loc 3rd row by index 2 \n\n");
            outputWriter.append(testDf.loc(2) + "\n\n");
            
            outputWriter.append("\n\nTEST 11: loc by label \"IL\" \n\n");
            outputWriter.append(testDf.loc("IL") + "\n\n");
            
            outputWriter.append("\n\nTEST 12: loc 3rd - 10th row by index \n\n");
            outputWriter.append(testDf.loc(2, 9) + "\n\n");
            
            outputWriter.append("\n\nTEST 13: loc row \"IL\" to \"CA\" row by label \n\n");
            outputWriter.append(testDf.loc("IL", "CA") + "\n\n");
            
            outputWriter.append("\n\nTEST 14: sort by year using column index \n\n");
            outputWriter.append(testDf.sort(2) + "\n\n");
            
            outputWriter.append("\n\nTEST 15: sort by year using column label \n\n");
            outputWriter.append(testDf.sort("year") + "\n\n");
            
            outputWriter.append("\n\nTEST 16: filter rows with year equals to 2017 \n\n");
            outputWriter.append(testDf.filter("year", "==", 2017) + "\n\n");
            
            outputWriter.append("\n\nTEST 17: filter rows with state of CA \n\n");
            outputWriter.append(testDf.filter("state", "==", "CA") + "\n\n");

            outputWriter.append("\n\nTEST 18: get maximum year \n\n");
            outputWriter.append(testDf.getMax(2) + "\n\n");
            
            outputWriter.append("\n\nTEST 19: get maximum year using string label \n\n");
            outputWriter.append(testDf.getMax("year") + "\n\n");
            
            outputWriter.append("\n\nTEST 20: get minimum year \n\n");
            outputWriter.append(testDf.getMin(2) + "\n\n");
            
            outputWriter.append("\n\nTEST 21: get minimum year using string label \n\n");
            outputWriter.append(testDf.getMin("year") + "\n\n");
            
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("Could not write to output file, sorry!");
        }
    }
}