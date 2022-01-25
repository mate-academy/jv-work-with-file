# jv-work-with-file

Implement method `getStatistic(String fromFileName, String toFileName)` 
which will return some data from market after working day. 

This method has two parameters:
- `String fromFileName` - you should read data from this file
- `String toFileName` - you should write result to this file

Input file has `.csv` format. CSV is a simple file format used to store tabular data.
This type of files is very popular to storing information. So we will start working with it. 
CSV stands for "comma-separated values". Its data fields are most often separated, or delimited by a comma. 

For example, let's say you had a spreadsheet containing the following data:

| operation type | ammount  | 
| :------------: | :-------:|
| supply         | 30       | 
| buy            | 10       | 
| buy            | 13       | 
| supply         | 17       | 
| buy            | 10       | 

The above data could be represented in a CSV-formatted file as follows:
```csv
supply,30
buy,10
buy,13
supply,17
buy,10
```

__Your task is to read all data from input csv file, 
create a report and write it to newFile (the name of this file is the second parameter in the method).__

Example of the report:
```csv
supply,47
buy,33
result,14
```

Explanation:
- `supply = 30 + 17 = 47`
- `buy = 10 + 13 + 10 = 33`
- `result = supply - buy = 47 - 33 = 14`

#### [Try to avoid these common mistakes, while solving task](https://mate-academy.github.io/jv-program-common-mistakes/java-core/builder-file/work-with-file)
