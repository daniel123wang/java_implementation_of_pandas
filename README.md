# Implementing Pandas in Java

## Overview

Implemented a MyPandas class and a MyDataFrame class. 

## Implementation

The MyPandas class offers the following interfaces:

1. MyDataFrame readCSV(String path)
Read a file and store it into a MyDataFrame object. Possible data types: Integer and String. 

2. void writeCSV(MyDataFrame data, String path)
Write a MyDataFrame object to file specified by path.

3. MyDataFrame concat(MyDataFrame df1, MyDataFrame df2)
Concatenate two MyDataFrame object along rows. Returns the concatenated MyDataFrame.

MyDataFrame class offers the following interfaces: 

1. Head and Tail.
2. dType
3. Slicing
4. Filtering
5. Indexing
6. Sorting
7. Aggregation
