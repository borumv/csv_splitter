# csv_splitter
Сan be useful for those who work with CSV files and need to separate these files into parts. The class implements the functionality of splitting CSV files into parts and creating a file with indexes, which can be useful for processing large amounts of data.
CsvSplitter Class

The **CsvSplitter** class is a Java implementation that splits a large CSV file into smaller CSV files based on a given size limit. This class contains the following methods:
split()

The `split()` method takes three parameters:

- `targetToSplit`: the path to the CSV file to be split.
- `pathSplitFiles`: the directory path where the smaller CSV files will be stored.
- `sizeParts`: the size limit for each smaller CSV file.

The method splits the CSV file into smaller files and stores them in the specified directory. It uses the `getSeekIndexes()` method to calculate the indexes where the CSV file should be split. Then, it reads the CSV file from these indexes and writes the data to smaller CSV files.
`generateIndexes()`

The generateIndexes() method takes two parameters:

- `sourceFile`: the path to the CSV file to be split.
- `targetFile`: the path to the file where the split indexes will be stored.
- `size_buffer`: the size of the buffer used to split the CSV file.

This method generates the split indexes based on the given buffer size and writes them to a file in JSON format. It uses the getSeekIndexes() method to calculate the indexes where the CSV file should be split.
`getSeekIndexes()`
The `getSeekIndexes()`method takes two parameters:
- `path`: the path to the CSV file to be split.
- `size_buffer`: the size of the buffer used to split the CSV file.

This method calculates the indexes where the CSV file should be split based on the given buffer size. It reads the CSV file byte by byte and searches for the newline character (\n) to split the file. It returns a list of indexes where the CSV file should be split.
