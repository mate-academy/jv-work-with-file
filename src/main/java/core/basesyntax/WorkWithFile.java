package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    ///////////////////////////////////////
    ///////////// Read from file //////////
    ///////////////////////////////////////
    public void getStatistic(String fromFileName, String toFileName) {
        // Declare a File object to represent the file
        File fromFile = new File(fromFileName);
        // Initialize a variable to store the total supply and total buy
        int supply = 0;
        int buy = 0;
        try {
            // Create a BufferedReader to read the file
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            // Create a StringBuilder to store the contents of the file
            StringBuilder stringBuilder = new StringBuilder();
            // Read the first line of the file
            String value = reader.readLine();
            // Create a Temp String array with size 2(buy,2)
            String[] temp = new String[2];
            // Continue reading lines until there are no more lines to read
            while (value != null) {
                // Append the current line to the StringBuilder
                stringBuilder.append(value);
                //Checking if line contains buy, if TRUE store to temp array value of line
                // Example = Line: (buy,4) = buy[0]arr, 4[1]arr
                if (value.contains("buy")) {
                    temp = value.split(",");
                    // addition every line with "buy" to var - buy
                    buy += Integer.parseInt(temp[1]);
                }
                //Checking if line contains supply, if TRUE store to temp array value of line
                if (value.contains("supply")) {
                    temp = value.split(",");
                    // addition every line with "supply" to var - supply
                    supply += Integer.parseInt(temp[1]);
                }
                // Read the next line
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        //////////////////////////////////////
        ///////////// Write to file //////////
        //////////////////////////////////////
        // Find the index of the "dot" in the fromFile name
        int indexOfCutFileName = fromFile.getName().indexOf('.');
        // Create the name for the toFile by taking the
        // fromFile name up to (but not including) the dot
        // and appending "Result.csv" to it
        File toFile = new File(fromFile.getName().substring(0, indexOfCutFileName) + "Result.csv");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            // Write the supply, buy, and result values to the file, each on a separate line
            // with the label and value separated by a comma
            bufferedWriter.write("supply," + supply
                    + System.lineSeparator()
                    + "buy," + buy
                    + System.lineSeparator()
                    + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }

    }
}
